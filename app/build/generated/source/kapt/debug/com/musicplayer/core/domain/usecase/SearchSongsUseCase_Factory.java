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
public final class SearchSongsUseCase_Factory implements Factory<SearchSongsUseCase> {
  private final Provider<MusicRepository> repositoryProvider;

  public SearchSongsUseCase_Factory(Provider<MusicRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SearchSongsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SearchSongsUseCase_Factory create(Provider<MusicRepository> repositoryProvider) {
    return new SearchSongsUseCase_Factory(repositoryProvider);
  }

  public static SearchSongsUseCase newInstance(MusicRepository repository) {
    return new SearchSongsUseCase(repository);
  }
}
