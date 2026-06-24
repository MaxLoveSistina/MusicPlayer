package com.musicplayer.core.domain.usecase

import com.musicplayer.core.domain.model.Song
import com.musicplayer.core.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    operator fun invoke(): Flow<List<Song>> = repository.getAllSongs()
}

class SearchSongsUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(query: String): List<Song> =
        repository.searchSongs(query)
}

class GetFavoriteSongsUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    operator fun invoke(): Flow<List<Song>> = repository.getFavoriteSongs()
}

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(songId: Long) {
        if (repository.isFavorite(songId)) {
            repository.removeFromFavorites(songId)
        } else {
            repository.addToFavorites(songId)
        }
    }
}
