package com.musicplayer.core.data.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class LocalMusicDataSource_Factory implements Factory<LocalMusicDataSource> {
  private final Provider<Context> contextProvider;

  public LocalMusicDataSource_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalMusicDataSource get() {
    return newInstance(contextProvider.get());
  }

  public static LocalMusicDataSource_Factory create(Provider<Context> contextProvider) {
    return new LocalMusicDataSource_Factory(contextProvider);
  }

  public static LocalMusicDataSource newInstance(Context context) {
    return new LocalMusicDataSource(context);
  }
}
