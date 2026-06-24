package com.musicplayer.presentation.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.musicplayer.R
import com.musicplayer.core.domain.model.RepeatMode
import com.musicplayer.databinding.FragmentPlayerBinding
import com.musicplayer.presentation.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlayerViewModel by activityViewModels()

    private var isUserSeeking = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupControls()
        observeState()
    }

    private fun setupControls() {
        binding.btnPlayPause.setOnClickListener { viewModel.togglePlayPause() }
        binding.btnNext.setOnClickListener { viewModel.skipToNext() }
        binding.btnPrevious.setOnClickListener { viewModel.skipToPrevious() }
        binding.btnRepeat.setOnClickListener { viewModel.cycleRepeatMode() }
        binding.btnShuffle.setOnClickListener { viewModel.toggleShuffle() }
        binding.btnFavorite.setOnClickListener {
            viewModel.playerState.value?.currentSong?.let {
                viewModel.toggleFavorite(it.id)
            }
        }

        // SeekBar swipe / drag
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val duration = viewModel.playerState.value?.duration ?: 0L
                    val pos = (progress / 1000f * duration).toLong()
                    binding.tvCurrentTime.text = formatTime(pos)
                }
            }

            override fun onStartTrackingTouch(sb: SeekBar) {
                isUserSeeking = true
            }

            override fun onStopTrackingTouch(sb: SeekBar) {
                isUserSeeking = false
                val duration = viewModel.playerState.value?.duration ?: 0L
                val pos = (sb.progress / 1000f * duration).toLong()
                viewModel.seekTo(pos)
            }
        })
    }

    private fun observeState() {
        viewModel.playerState.observe(viewLifecycleOwner) { state ->
            val song = state.currentSong

            if (song == null) {
                binding.tvSongTitle.text = getString(R.string.no_song_playing)
                binding.tvArtist.text = ""
                binding.tvAlbum.text = ""
                binding.ivAlbumArt.setImageResource(R.drawable.ic_music_note_large)
                return@observe
            }

            // Song info
            binding.tvSongTitle.text = song.title
            binding.tvArtist.text = song.artist
            binding.tvAlbum.text = song.album

            // Album art
            Glide.with(this)
                .load(song.albumArtUri)
                .placeholder(R.drawable.ic_music_note_large)
                .error(R.drawable.ic_music_note_large)
                .centerCrop()
                .into(binding.ivAlbumArt)

            // Play/pause icon
            binding.btnPlayPause.setImageResource(
                if (state.isPlaying) R.drawable.ic_pause_circle else R.drawable.ic_play_circle
            )

            // SeekBar
            if (!isUserSeeking && state.duration > 0) {
                val progress = ((state.currentPosition.toFloat() / state.duration) * 1000).toInt()
                binding.seekBar.progress = progress
            }

            binding.tvCurrentTime.text = formatTime(state.currentPosition)
            binding.tvTotalTime.text = formatTime(state.duration)

            // Repeat mode
            val repeatIcon = when (state.repeatMode) {
                RepeatMode.NONE -> R.drawable.ic_repeat
                RepeatMode.ALL -> R.drawable.ic_repeat_on
                RepeatMode.ONE -> R.drawable.ic_repeat_one
            }
            binding.btnRepeat.setImageResource(repeatIcon)
            binding.btnRepeat.alpha = if (state.repeatMode == RepeatMode.NONE) 0.4f else 1f

            // Shuffle
            binding.btnShuffle.alpha = if (state.shuffleEnabled) 1f else 0.4f
        }

        viewModel.favoriteSongs.observe(viewLifecycleOwner) { favorites ->
            val currentId = viewModel.playerState.value?.currentSong?.id
            val isFav = favorites.any { it.id == currentId }
            binding.btnFavorite.setImageResource(
                if (isFav) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            )
        }
    }

    private fun formatTime(ms: Long): String {
        val totalSeconds = ms / 1000
        val min = totalSeconds / 60
        val sec = totalSeconds % 60
        return "%d:%02d".format(min, sec)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
