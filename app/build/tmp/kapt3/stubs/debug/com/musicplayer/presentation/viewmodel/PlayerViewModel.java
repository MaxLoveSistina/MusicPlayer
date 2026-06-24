package com.musicplayer.presentation.viewmodel;

import android.app.Application;
import androidx.lifecycle.*;
import com.musicplayer.core.domain.model.PlayerState;
import com.musicplayer.core.domain.model.RepeatMode;
import com.musicplayer.core.domain.model.Song;
import com.musicplayer.core.domain.usecase.GetAllSongsUseCase;
import com.musicplayer.core.domain.usecase.GetFavoriteSongsUseCase;
import com.musicplayer.core.domain.usecase.SearchSongsUseCase;
import com.musicplayer.core.domain.usecase.ToggleFavoriteUseCase;
import com.musicplayer.core.service.MusicService;
import com.musicplayer.core.service.MusicServiceConnection;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001CB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020.J\u0006\u00100\u001a\u00020.J\u001e\u00101\u001a\u00020.2\u0006\u00102\u001a\u00020\u00132\u000e\b\u0002\u00103\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J\u000e\u00104\u001a\u00020.2\u0006\u00105\u001a\u00020\u000fJ\u000e\u00106\u001a\u00020.2\u0006\u00107\u001a\u000208J\u000e\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020\u0015J\u0006\u0010;\u001a\u00020.J\u0006\u0010<\u001a\u00020.J\u000e\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u000208J\u0006\u0010@\u001a\u00020.J\u0006\u0010A\u001a\u00020.J\u0006\u0010B\u001a\u00020.R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00150\u00150\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00198F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001bR\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00198F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u00198F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u001bR\u0011\u0010\'\u001a\u00020(\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00170\u00198F\u00a2\u0006\u0006\u001a\u0004\b,\u0010\u001b\u00a8\u0006D"}, d2 = {"Lcom/musicplayer/presentation/viewmodel/PlayerViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "getAllSongsUseCase", "Lcom/musicplayer/core/domain/usecase/GetAllSongsUseCase;", "searchSongsUseCase", "Lcom/musicplayer/core/domain/usecase/SearchSongsUseCase;", "getFavoriteSongsUseCase", "Lcom/musicplayer/core/domain/usecase/GetFavoriteSongsUseCase;", "toggleFavoriteUseCase", "Lcom/musicplayer/core/domain/usecase/ToggleFavoriteUseCase;", "(Landroid/app/Application;Lcom/musicplayer/core/domain/usecase/GetAllSongsUseCase;Lcom/musicplayer/core/domain/usecase/SearchSongsUseCase;Lcom/musicplayer/core/domain/usecase/GetFavoriteSongsUseCase;Lcom/musicplayer/core/domain/usecase/ToggleFavoriteUseCase;)V", "_searchQuery", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_searchResults", "", "Lcom/musicplayer/core/domain/model/Song;", "_selectedTab", "", "_uiEvent", "Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent;", "allSongs", "Landroidx/lifecycle/LiveData;", "getAllSongs", "()Landroidx/lifecycle/LiveData;", "favoriteSongs", "getFavoriteSongs", "playerState", "Lcom/musicplayer/core/domain/model/PlayerState;", "getPlayerState", "searchQuery", "getSearchQuery", "searchResults", "getSearchResults", "selectedTab", "getSelectedTab", "serviceConnection", "Lcom/musicplayer/core/service/MusicServiceConnection;", "getServiceConnection", "()Lcom/musicplayer/core/service/MusicServiceConnection;", "uiEvent", "getUiEvent", "bindService", "", "clearSearch", "cycleRepeatMode", "playSong", "song", "playlist", "search", "query", "seekTo", "positionMs", "", "selectTab", "index", "skipToNext", "skipToPrevious", "toggleFavorite", "Lkotlinx/coroutines/Job;", "songId", "togglePlayPause", "toggleShuffle", "unbindService", "UiEvent", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PlayerViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.domain.usecase.GetAllSongsUseCase getAllSongsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.domain.usecase.SearchSongsUseCase searchSongsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.domain.usecase.GetFavoriteSongsUseCase getFavoriteSongsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.domain.usecase.ToggleFavoriteUseCase toggleFavoriteUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.service.MusicServiceConnection serviceConnection = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.musicplayer.core.domain.model.Song>> allSongs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.musicplayer.core.domain.model.Song>> favoriteSongs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.musicplayer.core.domain.model.Song>> _searchResults = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.musicplayer.core.domain.model.PlayerState> playerState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.musicplayer.presentation.viewmodel.PlayerViewModel.UiEvent> _uiEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _selectedTab = null;
    
    @javax.inject.Inject()
    public PlayerViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.usecase.GetAllSongsUseCase getAllSongsUseCase, @org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.usecase.SearchSongsUseCase searchSongsUseCase, @org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.usecase.GetFavoriteSongsUseCase getFavoriteSongsUseCase, @org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.usecase.ToggleFavoriteUseCase toggleFavoriteUseCase) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.musicplayer.core.service.MusicServiceConnection getServiceConnection() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.musicplayer.core.domain.model.Song>> getAllSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.musicplayer.core.domain.model.Song>> getFavoriteSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.musicplayer.core.domain.model.Song>> getSearchResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.musicplayer.core.domain.model.PlayerState> getPlayerState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.musicplayer.presentation.viewmodel.PlayerViewModel.UiEvent> getUiEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getSelectedTab() {
        return null;
    }
    
    public final void selectTab(int index) {
    }
    
    public final void bindService() {
    }
    
    public final void unbindService() {
    }
    
    public final void playSong(@org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.model.Song song, @org.jetbrains.annotations.NotNull()
    java.util.List<com.musicplayer.core.domain.model.Song> playlist) {
    }
    
    public final void togglePlayPause() {
    }
    
    public final void skipToNext() {
    }
    
    public final void skipToPrevious() {
    }
    
    public final void seekTo(long positionMs) {
    }
    
    public final void cycleRepeatMode() {
    }
    
    public final void toggleShuffle() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job toggleFavorite(long songId) {
        return null;
    }
    
    public final void search(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void clearSearch() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent;", "", "()V", "NavigateToPlayer", "ShowError", "Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent$NavigateToPlayer;", "Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent$ShowError;", "app_debug"})
    public static abstract class UiEvent {
        
        private UiEvent() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent$NavigateToPlayer;", "Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent;", "()V", "app_debug"})
        public static final class NavigateToPlayer extends com.musicplayer.presentation.viewmodel.PlayerViewModel.UiEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.musicplayer.presentation.viewmodel.PlayerViewModel.UiEvent.NavigateToPlayer INSTANCE = null;
            
            private NavigateToPlayer() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent$ShowError;", "Lcom/musicplayer/presentation/viewmodel/PlayerViewModel$UiEvent;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class ShowError extends com.musicplayer.presentation.viewmodel.PlayerViewModel.UiEvent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public ShowError(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.musicplayer.presentation.viewmodel.PlayerViewModel.UiEvent.ShowError copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
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
    }
}