package com.musicplayer.presentation.playlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.musicplayer.R;
import com.musicplayer.core.domain.model.Song;
import com.musicplayer.databinding.ItemSongBinding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001b2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u001b\u001cB-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u000e\u001a\u00020\u00062\n\u0010\u000f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0014\u0010\u0016\u001a\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0015\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\u001aR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\r\u00a8\u0006\u001d"}, d2 = {"Lcom/musicplayer/presentation/playlist/SongAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/musicplayer/core/domain/model/Song;", "Lcom/musicplayer/presentation/playlist/SongAdapter$SongViewHolder;", "onSongClick", "Lkotlin/Function1;", "", "onFavoriteClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "favoriteIds", "", "", "playingSongId", "Ljava/lang/Long;", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setFavoriteIds", "ids", "setPlayingSongId", "id", "(Ljava/lang/Long;)V", "Companion", "SongViewHolder", "app_debug"})
public final class SongAdapter extends androidx.recyclerview.widget.ListAdapter<com.musicplayer.core.domain.model.Song, com.musicplayer.presentation.playlist.SongAdapter.SongViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.musicplayer.core.domain.model.Song, kotlin.Unit> onSongClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.musicplayer.core.domain.model.Song, kotlin.Unit> onFavoriteClick = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.Set<java.lang.Long> favoriteIds;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long playingSongId;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.musicplayer.core.domain.model.Song> DIFF_CALLBACK = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.musicplayer.presentation.playlist.SongAdapter.Companion Companion = null;
    
    public SongAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.musicplayer.core.domain.model.Song, kotlin.Unit> onSongClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.musicplayer.core.domain.model.Song, kotlin.Unit> onFavoriteClick) {
        super(null);
    }
    
    public final void setFavoriteIds(@org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Long> ids) {
    }
    
    public final void setPlayingSongId(@org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.musicplayer.presentation.playlist.SongAdapter.SongViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.musicplayer.presentation.playlist.SongAdapter.SongViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/musicplayer/presentation/playlist/SongAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/musicplayer/core/domain/model/Song;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/musicplayer/presentation/playlist/SongAdapter$SongViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/musicplayer/databinding/ItemSongBinding;", "(Lcom/musicplayer/presentation/playlist/SongAdapter;Lcom/musicplayer/databinding/ItemSongBinding;)V", "bind", "", "song", "Lcom/musicplayer/core/domain/model/Song;", "app_debug"})
    public final class SongViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.musicplayer.databinding.ItemSongBinding binding = null;
        
        public SongViewHolder(@org.jetbrains.annotations.NotNull()
        com.musicplayer.databinding.ItemSongBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.musicplayer.core.domain.model.Song song) {
        }
    }
}