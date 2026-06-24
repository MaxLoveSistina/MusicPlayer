package com.musicplayer.core.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.musicplayer.R
import com.musicplayer.core.domain.model.PlayerState
import com.musicplayer.core.domain.model.RepeatMode
import com.musicplayer.core.domain.model.Song
import com.musicplayer.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : LifecycleService() {

    companion object {
        const val CHANNEL_ID = "music_player_channel"
        const val NOTIFICATION_ID = 1001
        const val ACTION_PLAY_PAUSE = "ACTION_PLAY_PAUSE"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_PREV = "ACTION_PREV"
        const val ACTION_STOP = "ACTION_STOP"
    }

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private val binder = MusicBinder()

    private lateinit var exoPlayer: ExoPlayer
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _playerState = MutableLiveData(PlayerState())
    val playerState: LiveData<PlayerState> get() = _playerState

    private var progressJob: Job? = null

    // ─── Lifecycle ───────────────────────────────────────────────────────────
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        initExoPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_PLAY_PAUSE -> togglePlayPause()
            ACTION_NEXT -> skipToNext()
            ACTION_PREV -> skipToPrevious()
            ACTION_STOP -> stopSelf()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    override fun onDestroy() {
        progressJob?.cancel()
        serviceScope.cancel()
        exoPlayer.release()
        super.onDestroy()
    }

    // ─── ExoPlayer Setup ─────────────────────────────────────────────────────
    private fun initExoPlayer() {
        exoPlayer = ExoPlayer.Builder(this).build().apply {
            addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    updateState { copy(isPlaying = isPlaying) }
                    if (isPlaying) startProgressTracking() else stopProgressTracking()
                    updateNotification()
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_ENDED) {
                        handleSongEnd()
                    }
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    val state = _playerState.value ?: return
                    val idx = currentMediaItemIndex
                    if (idx in state.playlist.indices) {
                        updateState { copy(currentSong = state.playlist[idx], currentIndex = idx) }
                        updateNotification()
                    }
                }
            })
        }
    }

    // ─── Public API ──────────────────────────────────────────────────────────
    fun playSongs(songs: List<Song>, startIndex: Int = 0) {
        val items = songs.map { MediaItem.fromUri(it.uri) }
        exoPlayer.setMediaItems(items, startIndex, 0L)
        exoPlayer.prepare()
        exoPlayer.play()
        updateState {
            copy(
                playlist = songs,
                currentIndex = startIndex,
                currentSong = songs.getOrNull(startIndex),
                isPlaying = true
            )
        }
        startForeground(NOTIFICATION_ID, buildNotification())
    }

    fun togglePlayPause() {
        if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
    }

    fun skipToNext() {
        if (exoPlayer.hasNextMediaItem()) {
            exoPlayer.seekToNextMediaItem()
        } else {
            // wrap around
            exoPlayer.seekToDefaultPosition(0)
        }
        exoPlayer.play()
    }

    fun skipToPrevious() {
        val pos = exoPlayer.currentPosition
        if (pos > 3000) {
            exoPlayer.seekTo(0)
        } else if (exoPlayer.hasPreviousMediaItem()) {
            exoPlayer.seekToPreviousMediaItem()
        }
        exoPlayer.play()
    }

    fun seekTo(positionMs: Long) {
        exoPlayer.seekTo(positionMs)
        updateState { copy(currentPosition = positionMs) }
    }

    fun setRepeatMode(mode: RepeatMode) {
        exoPlayer.repeatMode = when (mode) {
            RepeatMode.NONE -> Player.REPEAT_MODE_OFF
            RepeatMode.ONE -> Player.REPEAT_MODE_ONE
            RepeatMode.ALL -> Player.REPEAT_MODE_ALL
        }
        updateState { copy(repeatMode = mode) }
    }

    fun setShuffleEnabled(enabled: Boolean) {
        exoPlayer.shuffleModeEnabled = enabled
        updateState { copy(shuffleEnabled = enabled) }
    }

    // ─── Private helpers ─────────────────────────────────────────────────────
    private fun handleSongEnd() {
        val state = _playerState.value ?: return
        when (state.repeatMode) {
            RepeatMode.ONE -> {
                exoPlayer.seekTo(0)
                exoPlayer.play()
            }
            RepeatMode.ALL -> skipToNext()
            RepeatMode.NONE -> {
                if (state.currentIndex < state.playlist.size - 1) {
                    skipToNext()
                } else {
                    updateState { copy(isPlaying = false, currentPosition = 0) }
                }
            }
        }
    }

    private fun startProgressTracking() {
        progressJob?.cancel()
        progressJob = serviceScope.launch {
            while (isActive) {
                val position = exoPlayer.currentPosition
                val duration = exoPlayer.duration.coerceAtLeast(0L)
                updateState { copy(currentPosition = position, duration = duration) }
                delay(500)
            }
        }
    }

    private fun stopProgressTracking() {
        progressJob?.cancel()
    }

    private fun updateState(block: PlayerState.() -> PlayerState) {
        _playerState.value = (_playerState.value ?: PlayerState()).block()
    }

    // ─── Notification ─────────────────────────────────────────────────────────
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Music Playback",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "Shows music playback controls"
            setShowBadge(false)
        }
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val state = _playerState.value
        val song = state?.currentSong

        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this, 0, activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val playPauseIntent = PendingIntent.getService(
            this, 1,
            Intent(this, MusicService::class.java).apply { action = ACTION_PLAY_PAUSE },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val nextIntent = PendingIntent.getService(
            this, 2,
            Intent(this, MusicService::class.java).apply { action = ACTION_NEXT },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val prevIntent = PendingIntent.getService(
            this, 3,
            Intent(this, MusicService::class.java).apply { action = ACTION_PREV },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val playPauseIcon = if (state?.isPlaying == true)
            R.drawable.ic_pause else R.drawable.ic_play

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(song?.title ?: "Music Player")
            .setContentText(song?.artist ?: "")
            .setSmallIcon(R.drawable.ic_music_note)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_music_note))
            .setContentIntent(contentIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_skip_previous, "Previous", prevIntent)
            .addAction(playPauseIcon, "Play/Pause", playPauseIntent)
            .addAction(R.drawable.ic_skip_next, "Next", nextIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .setOngoing(state?.isPlaying == true)
            .build()
    }

    private fun updateNotification() {
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, buildNotification())
    }
}
