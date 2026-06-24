package com.musicplayer.core.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import com.musicplayer.R;
import com.musicplayer.core.domain.model.PlayerState;
import com.musicplayer.core.domain.model.RepeatMode;
import com.musicplayer.core.domain.model.Song;
import com.musicplayer.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.*;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0002;<B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0016J\b\u0010\u001e\u001a\u00020\u0016H\u0016J\"\u0010\u001f\u001a\u00020 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 H\u0016J\u001e\u0010#\u001a\u00020\u00162\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\b\b\u0002\u0010\'\u001a\u00020 J\u000e\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u00020\u0016J\u0006\u00102\u001a\u00020\u0016J\b\u00103\u001a\u00020\u0016H\u0002J\b\u00104\u001a\u00020\u0016H\u0002J\u0006\u00105\u001a\u00020\u0016J\b\u00106\u001a\u00020\u0016H\u0002J!\u00107\u001a\u00020\u00162\u0017\u00108\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000509\u00a2\u0006\u0002\b:H\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lcom/musicplayer/core/service/MusicService;", "Landroidx/lifecycle/LifecycleService;", "()V", "_playerState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/musicplayer/core/domain/model/PlayerState;", "kotlin.jvm.PlatformType", "binder", "Lcom/musicplayer/core/service/MusicService$MusicBinder;", "exoPlayer", "Landroidx/media3/exoplayer/ExoPlayer;", "playerState", "Landroidx/lifecycle/LiveData;", "getPlayerState", "()Landroidx/lifecycle/LiveData;", "progressJob", "Lkotlinx/coroutines/Job;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "buildNotification", "Landroid/app/Notification;", "createNotificationChannel", "", "handleSongEnd", "initExoPlayer", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "playSongs", "songs", "", "Lcom/musicplayer/core/domain/model/Song;", "startIndex", "seekTo", "positionMs", "", "setRepeatMode", "mode", "Lcom/musicplayer/core/domain/model/RepeatMode;", "setShuffleEnabled", "enabled", "", "skipToNext", "skipToPrevious", "startProgressTracking", "stopProgressTracking", "togglePlayPause", "updateNotification", "updateState", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Companion", "MusicBinder", "app_debug"})
public final class MusicService extends androidx.lifecycle.LifecycleService {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "music_player_channel";
    public static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_PLAY_PAUSE = "ACTION_PLAY_PAUSE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_NEXT = "ACTION_NEXT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_PREV = "ACTION_PREV";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "ACTION_STOP";
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.service.MusicService.MusicBinder binder = null;
    private androidx.media3.exoplayer.ExoPlayer exoPlayer;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.musicplayer.core.domain.model.PlayerState> _playerState = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job progressJob;
    @org.jetbrains.annotations.NotNull()
    public static final com.musicplayer.core.service.MusicService.Companion Companion = null;
    
    public MusicService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.musicplayer.core.domain.model.PlayerState> getPlayerState() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void initExoPlayer() {
    }
    
    public final void playSongs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.musicplayer.core.domain.model.Song> songs, int startIndex) {
    }
    
    public final void togglePlayPause() {
    }
    
    public final void skipToNext() {
    }
    
    public final void skipToPrevious() {
    }
    
    public final void seekTo(long positionMs) {
    }
    
    public final void setRepeatMode(@org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.model.RepeatMode mode) {
    }
    
    public final void setShuffleEnabled(boolean enabled) {
    }
    
    private final void handleSongEnd() {
    }
    
    private final void startProgressTracking() {
    }
    
    private final void stopProgressTracking() {
    }
    
    private final void updateState(kotlin.jvm.functions.Function1<? super com.musicplayer.core.domain.model.PlayerState, com.musicplayer.core.domain.model.PlayerState> block) {
    }
    
    private final void createNotificationChannel() {
    }
    
    private final android.app.Notification buildNotification() {
        return null;
    }
    
    private final void updateNotification() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/musicplayer/core/service/MusicService$Companion;", "", "()V", "ACTION_NEXT", "", "ACTION_PLAY_PAUSE", "ACTION_PREV", "ACTION_STOP", "CHANNEL_ID", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/musicplayer/core/service/MusicService$MusicBinder;", "Landroid/os/Binder;", "(Lcom/musicplayer/core/service/MusicService;)V", "getService", "Lcom/musicplayer/core/service/MusicService;", "app_debug"})
    public final class MusicBinder extends android.os.Binder {
        
        public MusicBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.musicplayer.core.service.MusicService getService() {
            return null;
        }
    }
}