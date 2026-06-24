package com.musicplayer.core.data.repository

import com.musicplayer.core.data.local.FavoriteDao
import com.musicplayer.core.data.local.FavoriteEntity
import com.musicplayer.core.data.local.LocalMusicDataSource
import com.musicplayer.core.domain.model.Song
import com.musicplayer.core.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMusicDataSource,
    private val favoriteDao: FavoriteDao
) : MusicRepository {

    override fun getAllSongs(): Flow<List<Song>> = localDataSource.observeSongs()

    override suspend fun getSongById(id: Long): Song? {
        return localDataSource.querySongs().find { it.id == id }
    }

    override suspend fun searchSongs(query: String): List<Song> {
        return localDataSource.searchSongs(query)
    }

    override fun getFavoriteSongs(): Flow<List<Song>> {
        return combine(
            localDataSource.observeSongs(),
            favoriteDao.getAllFavorites()
        ) { songs, favorites ->
            val favoriteIds = favorites.map { it.songId }.toSet()
            songs.filter { it.id in favoriteIds }
        }
    }

    override suspend fun addToFavorites(songId: Long) {
        favoriteDao.addFavorite(FavoriteEntity(songId))
    }

    override suspend fun removeFromFavorites(songId: Long) {
        favoriteDao.removeFavorite(songId)
    }

    override suspend fun isFavorite(songId: Long): Boolean {
        return favoriteDao.isFavorite(songId)
    }
}
