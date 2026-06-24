package com.musicplayer.core.data.repository;

import com.musicplayer.core.data.local.FavoriteDao;
import com.musicplayer.core.data.local.LocalMusicDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class MusicRepositoryImpl_Factory implements Factory<MusicRepositoryImpl> {
  private final Provider<LocalMusicDataSource> localDataSourceProvider;

  private final Provider<FavoriteDao> favoriteDaoProvider;

  public MusicRepositoryImpl_Factory(Provider<LocalMusicDataSource> localDataSourceProvider,
      Provider<FavoriteDao> favoriteDaoProvider) {
    this.localDataSourceProvider = localDataSourceProvider;
    this.favoriteDaoProvider = favoriteDaoProvider;
  }

  @Override
  public MusicRepositoryImpl get() {
    return newInstance(localDataSourceProvider.get(), favoriteDaoProvider.get());
  }

  public static MusicRepositoryImpl_Factory create(
      Provider<LocalMusicDataSource> localDataSourceProvider,
      Provider<FavoriteDao> favoriteDaoProvider) {
    return new MusicRepositoryImpl_Factory(localDataSourceProvider, favoriteDaoProvider);
  }

  public static MusicRepositoryImpl newInstance(LocalMusicDataSource localDataSource,
      FavoriteDao favoriteDao) {
    return new MusicRepositoryImpl(localDataSource, favoriteDao);
  }
}
