package com.example.musicplaylistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel : ViewModel() {
    private val _playlists = MutableLiveData<MutableList<Playlist>>(mutableListOf())
    val playlists: LiveData<MutableList<Playlist>> get() = _playlists

    fun addPlaylist(playlist: Playlist) {
        _playlists.value?.add(playlist)
        _playlists.value = _playlists.value // Trigger LiveData update
    }

    fun deletePlaylist(playlist: Playlist) {
        _playlists.value?.remove(playlist)
        _playlists.value = _playlists.value // Trigger LiveData update
    }
}
