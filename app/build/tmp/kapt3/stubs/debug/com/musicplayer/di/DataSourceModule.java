package com.musicplayer.di;

import android.content.Context;
import androidx.room.Room;
import com.musicplayer.core.data.local.FavoriteDao;
import com.musicplayer.core.data.local.LocalMusicDataSource;
import com.musicplayer.core.data.local.MusicDatabase;
import com.musicplayer.core.data.repository.MusicRepositoryImpl;
import com.musicplayer.core.domain.repository.MusicRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lcom/musicplayer/di/DataSourceModule;", "", "()V", "provideLocalMusicDataSource", "Lcom/musicplayer/core/data/local/LocalMusicDataSource;", "context", "Landroid/content/Context;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DataSourceModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.musicplayer.di.DataSourceModule INSTANCE = null;
    
    private DataSourceModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.musicplayer.core.data.local.LocalMusicDataSource provideLocalMusicDataSource(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}