package com.musicplayer.di

import android.content.Context
import androidx.room.Room
import com.musicplayer.core.data.local.FavoriteDao
import com.musicplayer.core.data.local.LocalMusicDataSource
import com.musicplayer.core.data.local.MusicDatabase
import com.musicplayer.core.data.repository.MusicRepositoryImpl
import com.musicplayer.core.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// ─── Database Module ────────────────────────────────────────────────────────
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMusicDatabase(@ApplicationContext context: Context): MusicDatabase =
        Room.databaseBuilder(
            context,
            MusicDatabase::class.java,
            "music_player_db"
        ).build()

    @Provides
    @Singleton
    fun provideFavoriteDao(db: MusicDatabase): FavoriteDao = db.favoriteDao()
}

// ─── Data Source Module ─────────────────────────────────────────────────────
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalMusicDataSource(
        @ApplicationContext context: Context
    ): LocalMusicDataSource = LocalMusicDataSource(context)
}

// ─── Repository Module ──────────────────────────────────────────────────────
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMusicRepository(
        impl: MusicRepositoryImpl
    ): MusicRepository
}
