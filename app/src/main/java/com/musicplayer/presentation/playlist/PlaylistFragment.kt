package com.musicplayer.presentation.playlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.musicplayer.R
import com.musicplayer.core.domain.model.Song
import com.musicplayer.databinding.FragmentPlaylistBinding
import com.musicplayer.presentation.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private var _binding: FragmentPlaylistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlayerViewModel by activityViewModels()
    private lateinit var adapter: SongAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = SongAdapter(
            onSongClick = { song ->
                viewModel.playSong(song, viewModel.allSongs.value ?: listOf(song))
            },
            onFavoriteClick = { song -> viewModel.toggleFavorite(song.id) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s?.toString() ?: ""
                viewModel.search(query)
            }
        })

        binding.btnClearSearch.setOnClickListener {
            binding.etSearch.setText("")
            viewModel.clearSearch()
        }
    }

    private fun observeData() {
        viewModel.searchQuery.observe(viewLifecycleOwner) { query ->
            binding.btnClearSearch.visibility = if (query.isNotEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            val query = viewModel.searchQuery.value ?: ""
            if (query.isNotEmpty()) {
                adapter.submitList(results)
                binding.tvEmpty.visibility = if (results.isEmpty()) View.VISIBLE else View.GONE
                binding.tvEmpty.text = getString(R.string.no_results)
            }
        }

        viewModel.allSongs.observe(viewLifecycleOwner) { songs ->
            val query = viewModel.searchQuery.value ?: ""
            if (query.isEmpty()) {
                adapter.submitList(songs)
                binding.tvEmpty.visibility = if (songs.isEmpty()) View.VISIBLE else View.GONE
                binding.tvEmpty.text = getString(R.string.no_songs_found)
            }
        }

        viewModel.favoriteSongs.observe(viewLifecycleOwner) { favs ->
            val ids = favs.map { it.id }.toSet()
            adapter.setFavoriteIds(ids)
        }

        viewModel.playerState.observe(viewLifecycleOwner) { state ->
            adapter.setPlayingSongId(state.currentSong?.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// ─── Favorites Fragment ──────────────────────────────────────────────────────
@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentPlaylistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlayerViewModel by activityViewModels()
    private lateinit var adapter: SongAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etSearch.visibility = View.GONE
        binding.btnClearSearch.visibility = View.GONE
        binding.tvScreenTitle.text = getString(R.string.favorites)

        adapter = SongAdapter(
            onSongClick = { song ->
                val favs = viewModel.favoriteSongs.value ?: listOf(song)
                viewModel.playSong(song, favs)
            },
            onFavoriteClick = { song -> viewModel.toggleFavorite(song.id) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.favoriteSongs.observe(viewLifecycleOwner) { songs ->
            adapter.submitList(songs)
            adapter.setFavoriteIds(songs.map { it.id }.toSet())
            binding.tvEmpty.visibility = if (songs.isEmpty()) View.VISIBLE else View.GONE
            binding.tvEmpty.text = getString(R.string.no_favorites)
        }

        viewModel.playerState.observe(viewLifecycleOwner) { state ->
            adapter.setPlayingSongId(state.currentSong?.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
