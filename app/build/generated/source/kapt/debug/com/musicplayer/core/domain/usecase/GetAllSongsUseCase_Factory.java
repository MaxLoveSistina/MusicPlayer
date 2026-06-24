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
public final class GetAllSongsUseCase_Factory implements Factory<GetAllSongsUseCase> {
  private final Provider<MusicRepository> repositoryProvider;

  public GetAllSongsUseCase_Factory(Provider<MusicRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAllSongsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAllSongsUseCase_Factory create(Provider<MusicRepository> repositoryProvider) {
    return new GetAllSongsUseCase_Factory(repositoryProvider);
  }

  public static GetAllSongsUseCase newInstance(MusicRepository repository) {
    return new GetAllSongsUseCase(repository);
  }
}
