package com.musicplayer.core.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Wraps [MusicService] binding so ViewModels / Activities can
 * observe the service reference via LiveData.
 */
class MusicServiceConnection(private val context: Context) {

    private val _service = MutableLiveData<MusicService?>()
    val service: LiveData<MusicService?> get() = _service

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            _service.value = (binder as? MusicService.MusicBinder)?.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            _service.value = null
        }
    }

    fun bind() {
        val intent = Intent(context, MusicService::class.java)
        context.startService(intent)
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        context.unbindService(connection)
        _service.value = null
    }
}
