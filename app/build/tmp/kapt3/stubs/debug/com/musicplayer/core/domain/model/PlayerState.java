package com.musicplayer.core.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\u0007H\u00c6\u0003J\t\u0010&\u001a\u00020\nH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u00c6\u0003J\t\u0010)\u001a\u00020\u000fH\u00c6\u0003Ja\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00c6\u0001J\u0013\u0010+\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020\u000fH\u00d6\u0001J\t\u0010.\u001a\u00020/H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0018R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018\u00a8\u00060"}, d2 = {"Lcom/musicplayer/core/domain/model/PlayerState;", "", "currentSong", "Lcom/musicplayer/core/domain/model/Song;", "isPlaying", "", "currentPosition", "", "duration", "repeatMode", "Lcom/musicplayer/core/domain/model/RepeatMode;", "shuffleEnabled", "playlist", "", "currentIndex", "", "(Lcom/musicplayer/core/domain/model/Song;ZJJLcom/musicplayer/core/domain/model/RepeatMode;ZLjava/util/List;I)V", "getCurrentIndex", "()I", "getCurrentPosition", "()J", "getCurrentSong", "()Lcom/musicplayer/core/domain/model/Song;", "getDuration", "()Z", "getPlaylist", "()Ljava/util/List;", "progress", "", "getProgress", "()F", "getRepeatMode", "()Lcom/musicplayer/core/domain/model/RepeatMode;", "getShuffleEnabled", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
public final class PlayerState {
    @org.jetbrains.annotations.Nullable()
    private final com.musicplayer.core.domain.model.Song currentSong = null;
    private final boolean isPlaying = false;
    private final long currentPosition = 0L;
    private final long duration = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.domain.model.RepeatMode repeatMode = null;
    private final boolean shuffleEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.musicplayer.core.domain.model.Song> playlist = null;
    private final int currentIndex = 0;
    
    public PlayerState(@org.jetbrains.annotations.Nullable()
    com.musicplayer.core.domain.model.Song currentSong, boolean isPlaying, long currentPosition, long duration, @org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.model.RepeatMode repeatMode, boolean shuffleEnabled, @org.jetbrains.annotations.NotNull()
    java.util.List<com.musicplayer.core.domain.model.Song> playlist, int currentIndex) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.musicplayer.core.domain.model.Song getCurrentSong() {
        return null;
    }
    
    public final boolean isPlaying() {
        return false;
    }
    
    public final long getCurrentPosition() {
        return 0L;
    }
    
    public final long getDuration() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.musicplayer.core.domain.model.RepeatMode getRepeatMode() {
        return null;
    }
    
    public final boolean getShuffleEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.musicplayer.core.domain.model.Song> getPlaylist() {
        return null;
    }
    
    public final int getCurrentIndex() {
        return 0;
    }
    
    public final float getProgress() {
        return 0.0F;
    }
    
    public PlayerState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.musicplayer.core.domain.model.Song component1() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.musicplayer.core.domain.model.RepeatMode component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.musicplayer.core.domain.model.Song> component7() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.musicplayer.core.domain.model.PlayerState copy(@org.jetbrains.annotations.Nullable()
    com.musicplayer.core.domain.model.Song currentSong, boolean isPlaying, long currentPosition, long duration, @org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.model.RepeatMode repeatMode, boolean shuffleEnabled, @org.jetbrains.annotations.NotNull()
    java.util.List<com.musicplayer.core.domain.model.Song> playlist, int currentIndex) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}