package com.musicplayer.core.data.repository;

import com.musicplayer.core.data.local.FavoriteDao;
import com.musicplayer.core.data.local.FavoriteEntity;
import com.musicplayer.core.data.local.LocalMusicDataSource;
import com.musicplayer.core.domain.model.Song;
import com.musicplayer.core.domain.repository.MusicRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rH\u0016J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rH\u0016J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/musicplayer/core/data/repository/MusicRepositoryImpl;", "Lcom/musicplayer/core/domain/repository/MusicRepository;", "localDataSource", "Lcom/musicplayer/core/data/local/LocalMusicDataSource;", "favoriteDao", "Lcom/musicplayer/core/data/local/FavoriteDao;", "(Lcom/musicplayer/core/data/local/LocalMusicDataSource;Lcom/musicplayer/core/data/local/FavoriteDao;)V", "addToFavorites", "", "songId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSongs", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/musicplayer/core/domain/model/Song;", "getFavoriteSongs", "getSongById", "id", "isFavorite", "", "removeFromFavorites", "searchSongs", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class MusicRepositoryImpl implements com.musicplayer.core.domain.repository.MusicRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.data.local.LocalMusicDataSource localDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.data.local.FavoriteDao favoriteDao = null;
    
    @javax.inject.Inject()
    public MusicRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.musicplayer.core.data.local.LocalMusicDataSource localDataSource, @org.jetbrains.annotations.NotNull()
    com.musicplayer.core.data.local.FavoriteDao favoriteDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.musicplayer.core.domain.model.Song>> getAllSongs() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSongById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.musicplayer.core.domain.model.Song> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object searchSongs(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.musicplayer.core.domain.model.Song>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.musicplayer.core.domain.model.Song>> getFavoriteSongs() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addToFavorites(long songId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeFromFavorites(long songId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isFavorite(long songId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}