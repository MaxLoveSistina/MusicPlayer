package com.musicplayer.di;

import android.content.Context;
import com.musicplayer.core.data.local.LocalMusicDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataSourceModule_ProvideLocalMusicDataSourceFactory implements Factory<LocalMusicDataSource> {
  private final Provider<Context> contextProvider;

  public DataSourceModule_ProvideLocalMusicDataSourceFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalMusicDataSource get() {
    return provideLocalMusicDataSource(contextProvider.get());
  }

  public static DataSourceModule_ProvideLocalMusicDataSourceFactory create(
      Provider<Context> contextProvider) {
    return new DataSourceModule_ProvideLocalMusicDataSourceFactory(contextProvider);
  }

  public static LocalMusicDataSource provideLocalMusicDataSource(Context context) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideLocalMusicDataSource(context));
  }
}
