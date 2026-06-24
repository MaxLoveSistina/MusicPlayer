package com.musicplayer.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.musicplayer.R
import com.musicplayer.databinding.ActivityMainBinding
import com.musicplayer.presentation.player.PlayerFragment
import com.musicplayer.presentation.playlist.PlaylistFragment
import com.musicplayer.presentation.playlist.FavoritesFragment
import com.musicplayer.presentation.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PlayerViewModel by viewModels()

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.values.all { it }
        if (!granted) {
            Toast.makeText(this, "Storage permission is required", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissions()
        setupNavigation()
        observeEvents()
        viewModel.bindService()

        if (savedInstanceState == null) {
            showFragment(PlaylistFragment(), "playlist")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unbindService()
    }

    private fun requestPermissions() {
        val permissions = buildList {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.READ_MEDIA_AUDIO)
                add(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        val notGranted = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (notGranted.isNotEmpty()) permissionLauncher.launch(notGranted.toTypedArray())
    }

    private fun setupNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_songs -> { showFragment(PlaylistFragment(), "playlist"); true }
                R.id.nav_favorites -> { showFragment(FavoritesFragment(), "favorites"); true }
                R.id.nav_player -> { showFragment(PlayerFragment(), "player"); true }
                else -> false
            }
        }
    }

    private fun observeEvents() {
        viewModel.uiEvent.observe(this) { event ->
            when (event) {
                is PlayerViewModel.UiEvent.NavigateToPlayer -> {
                    binding.bottomNav.selectedItemId = R.id.nav_player
                    showFragment(PlayerFragment(), "player")
                }
                is PlayerViewModel.UiEvent.ShowError -> {
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, tag)
            .commit()
    }
}
