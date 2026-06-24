package com.musicplayer.presentation.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.musicplayer.R
import com.musicplayer.core.domain.model.Song
import com.musicplayer.databinding.ItemSongBinding

class SongAdapter(
    private val onSongClick: (Song) -> Unit,
    private val onFavoriteClick: (Song) -> Unit
) : ListAdapter<Song, SongAdapter.SongViewHolder>(DIFF_CALLBACK) {

    private var favoriteIds: Set<Long> = emptySet()
    private var playingSongId: Long? = null

    fun setFavoriteIds(ids: Set<Long>) {
        favoriteIds = ids
        notifyDataSetChanged()
    }

    fun setPlayingSongId(id: Long?) {
        playingSongId = id
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SongViewHolder(
        private val binding: ItemSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song) {
            binding.tvTitle.text = song.title
            binding.tvArtist.text = song.artist
            binding.tvDuration.text = song.durationFormatted

            // Album art
            Glide.with(binding.root)
                .load(song.albumArtUri)
                .placeholder(R.drawable.ic_music_note)
                .error(R.drawable.ic_music_note)
                .centerCrop()
                .into(binding.ivAlbumArt)

            // Playing indicator
            val isPlaying = song.id == playingSongId
            binding.ivPlayingIndicator.visibility =
                if (isPlaying) android.view.View.VISIBLE else android.view.View.GONE
            binding.root.alpha = if (isPlaying) 1f else 0.85f

            // Favorite icon
            val isFav = song.id in favoriteIds
            binding.btnFavorite.setImageResource(
                if (isFav) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            )

            // Highlight background for playing
            binding.root.setBackgroundResource(
                if (isPlaying) R.drawable.bg_song_item_playing else R.drawable.bg_song_item
            )

            binding.root.setOnClickListener { onSongClick(song) }
            binding.btnFavorite.setOnClickListener { onFavoriteClick(song) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Song, newItem: Song) = oldItem == newItem
        }
    }
}
