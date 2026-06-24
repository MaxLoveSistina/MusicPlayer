package com.musicplayer.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.musicplayer.core.domain.model.PlayerState
import com.musicplayer.core.domain.model.RepeatMode
import com.musicplayer.core.domain.model.Song
import com.musicplayer.core.domain.usecase.GetAllSongsUseCase
import com.musicplayer.core.domain.usecase.GetFavoriteSongsUseCase
import com.musicplayer.core.domain.usecase.SearchSongsUseCase
import com.musicplayer.core.domain.usecase.ToggleFavoriteUseCase
import com.musicplayer.core.service.MusicService
import com.musicplayer.core.service.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    application: Application,
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val searchSongsUseCase: SearchSongsUseCase,
    private val getFavoriteSongsUseCase: GetFavoriteSongsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : AndroidViewModel(application) {

    val serviceConnection = MusicServiceConnection(application)

    // ─── All songs ──────────────────────────────────────────────────────────
    val allSongs: LiveData<List<Song>> = getAllSongsUseCase().asLiveData()

    // ─── Favorites ──────────────────────────────────────────────────────────
    val favoriteSongs: LiveData<List<Song>> = getFavoriteSongsUseCase().asLiveData()

    // ─── Search ─────────────────────────────────────────────────────────────
    private val _searchResults = MutableLiveData<List<Song>>(emptyList())
    val searchResults: LiveData<List<Song>> get() = _searchResults

    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> get() = _searchQuery

    // ─── Player state forwarded from service ─────────────────────────────────
    val playerState: LiveData<PlayerState> = serviceConnection.service.switchMap { svc ->
        svc?.playerState ?: MutableLiveData(PlayerState())
    }

    // ─── UI events ───────────────────────────────────────────────────────────
    private val _uiEvent = MutableLiveData<UiEvent>()
    val uiEvent: LiveData<UiEvent> get() = _uiEvent

    // ─── Tab selection ───────────────────────────────────────────────────────
    private val _selectedTab = MutableLiveData(0)
    val selectedTab: LiveData<Int> get() = _selectedTab

    fun selectTab(index: Int) { _selectedTab.value = index }

    // ─── Service binding ─────────────────────────────────────────────────────
    fun bindService() = serviceConnection.bind()
    fun unbindService() = serviceConnection.unbind()

    // ─── Playback controls ───────────────────────────────────────────────────
    fun playSong(song: Song, playlist: List<Song> = allSongs.value ?: listOf(song)) {
        val idx = playlist.indexOfFirst { it.id == song.id }.coerceAtLeast(0)
        serviceConnection.service.value?.playSongs(playlist, idx)
        _uiEvent.value = UiEvent.NavigateToPlayer
    }

    fun togglePlayPause() {
        serviceConnection.service.value?.togglePlayPause()
    }

    fun skipToNext() {
        serviceConnection.service.value?.skipToNext()
    }

    fun skipToPrevious() {
        serviceConnection.service.value?.skipToPrevious()
    }

    fun seekTo(positionMs: Long) {
        serviceConnection.service.value?.seekTo(positionMs)
    }

    fun cycleRepeatMode() {
        val current = playerState.value?.repeatMode ?: RepeatMode.NONE
        val next = when (current) {
            RepeatMode.NONE -> RepeatMode.ALL
            RepeatMode.ALL -> RepeatMode.ONE
            RepeatMode.ONE -> RepeatMode.NONE
        }
        serviceConnection.service.value?.setRepeatMode(next)
    }

    fun toggleShuffle() {
        val current = playerState.value?.shuffleEnabled ?: false
        serviceConnection.service.value?.setShuffleEnabled(!current)
    }

    fun toggleFavorite(songId: Long) = viewModelScope.launch {
        toggleFavoriteUseCase(songId)
    }

    // ─── Search ──────────────────────────────────────────────────────────────
    fun search(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _searchResults.value = if (query.isBlank()) emptyList()
            else searchSongsUseCase(query)
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _searchResults.value = emptyList()
    }

    // ─── Events ──────────────────────────────────────────────────────────────
    sealed class UiEvent {
        object NavigateToPlayer : UiEvent()
        data class ShowError(val message: String) : UiEvent()
    }
}
