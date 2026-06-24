package com.musicplayer.core.domain.usecase;

import com.musicplayer.core.domain.repository.MusicRepository;
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
public final class GetFavoriteSongsUseCase_Factory implements Factory<GetFavoriteSongsUseCase> {
  private final Provider<MusicRepository> repositoryProvider;

  public GetFavoriteSongsUseCase_Factory(Provider<MusicRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetFavoriteSongsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetFavoriteSongsUseCase_Factory create(
      Provider<MusicRepository> repositoryProvider) {
    return new GetFavoriteSongsUseCase_Factory(repositoryProvider);
  }

  public static GetFavoriteSongsUseCase newInstance(MusicRepository repository) {
    return new GetFavoriteSongsUseCase(repository);
  }
}
