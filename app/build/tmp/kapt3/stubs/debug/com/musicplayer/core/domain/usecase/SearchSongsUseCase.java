package com.musicplayer.core.domain.usecase;

import com.musicplayer.core.domain.model.Song;
import com.musicplayer.core.domain.repository.MusicRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086B\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/musicplayer/core/domain/usecase/SearchSongsUseCase;", "", "repository", "Lcom/musicplayer/core/domain/repository/MusicRepository;", "(Lcom/musicplayer/core/domain/repository/MusicRepository;)V", "invoke", "", "Lcom/musicplayer/core/domain/model/Song;", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SearchSongsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.musicplayer.core.domain.repository.MusicRepository repository = null;
    
    @javax.inject.Inject()
    public SearchSongsUseCase(@org.jetbrains.annotations.NotNull()
    com.musicplayer.core.domain.repository.MusicRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.musicplayer.core.domain.model.Song>> $completion) {
        return null;
    }
}