package com.musicplayer.core.domain.repository

import com.musicplayer.core.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getAllSongs(): Flow<List<Song>>
    suspend fun getSongById(id: Long): Song?
    suspend fun searchSongs(query: String): List<Song>
    fun getFavoriteSongs(): Flow<List<Song>>
    suspend fun addToFavorites(songId: Long)
    suspend fun removeFromFavorites(songId: Long)
    suspend fun isFavorite(songId: Long): Boolean
}
