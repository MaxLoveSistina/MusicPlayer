package com.musicplayer.presentation.viewmodel;

import android.app.Application;
import com.musicplayer.core.domain.usecase.GetAllSongsUseCase;
import com.musicplayer.core.domain.usecase.GetFavoriteSongsUseCase;
import com.musicplayer.core.domain.usecase.SearchSongsUseCase;
import com.musicplayer.core.domain.usecase.ToggleFavoriteUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class PlayerViewModel_Factory implements Factory<PlayerViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<GetAllSongsUseCase> getAllSongsUseCaseProvider;

  private final Provider<SearchSongsUseCase> searchSongsUseCaseProvider;

  private final Provider<GetFavoriteSongsUseCase> getFavoriteSongsUseCaseProvider;

  private final Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider;

  public PlayerViewModel_Factory(Provider<Application> applicationProvider,
      Provider<GetAllSongsUseCase> getAllSongsUseCaseProvider,
      Provider<SearchSongsUseCase> searchSongsUseCaseProvider,
      Provider<GetFavoriteSongsUseCase> getFavoriteSongsUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    this.applicationProvider = applicationProvider;
    this.getAllSongsUseCaseProvider = getAllSongsUseCaseProvider;
    this.searchSongsUseCaseProvider = searchSongsUseCaseProvider;
    this.getFavoriteSongsUseCaseProvider = getFavoriteSongsUseCaseProvider;
    this.toggleFavoriteUseCaseProvider = toggleFavoriteUseCaseProvider;
  }

  @Override
  public PlayerViewModel get() {
    return newInstance(applicationProvider.get(), getAllSongsUseCaseProvider.get(), searchSongsUseCaseProvider.get(), getFavoriteSongsUseCaseProvider.get(), toggleFavoriteUseCaseProvider.get());
  }

  public static PlayerViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<GetAllSongsUseCase> getAllSongsUseCaseProvider,
      Provider<SearchSongsUseCase> searchSongsUseCaseProvider,
      Provider<GetFavoriteSongsUseCase> getFavoriteSongsUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    return new PlayerViewModel_Factory(applicationProvider, getAllSongsUseCaseProvider, searchSongsUseCaseProvider, getFavoriteSongsUseCaseProvider, toggleFavoriteUseCaseProvider);
  }

  public static PlayerViewModel newInstance(Application application,
      GetAllSongsUseCase getAllSongsUseCase, SearchSongsUseCase searchSongsUseCase,
      GetFavoriteSongsUseCase getFavoriteSongsUseCase,
      ToggleFavoriteUseCase toggleFavoriteUseCase) {
    return new PlayerViewModel(application, getAllSongsUseCase, searchSongsUseCase, getFavoriteSongsUseCase, toggleFavoriteUseCase);
  }
}
