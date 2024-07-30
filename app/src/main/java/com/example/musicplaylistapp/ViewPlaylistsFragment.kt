package com.example.musicplaylistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewPlaylistsFragment : Fragment() {

    private val playlistViewModel: PlaylistViewModel by activityViewModels()
    private lateinit var playlistsRecyclerView: RecyclerView
    private lateinit var playlistsAdapter: PlaylistsAdapter
    private lateinit var emptyTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_playlists, container, false)

        // Initialize RecyclerView and TextView
        playlistsRecyclerView = view.findViewById(R.id.recycler_view_playlists)
        emptyTextView = view.findViewById(R.id.empty_text_view)
        playlistsRecyclerView.layoutManager = LinearLayoutManager(context)
        playlistsAdapter = PlaylistsAdapter(mutableListOf()) { playlist ->
            playlistViewModel.deletePlaylist(playlist)
        }
        playlistsRecyclerView.adapter = playlistsAdapter

        // Observe the playlists from ViewModel
        playlistViewModel.playlists.observe(viewLifecycleOwner, { playlists ->
            if (playlists.isEmpty()) {
                playlistsRecyclerView.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE
            } else {
                playlistsRecyclerView.visibility = View.VISIBLE
                emptyTextView.visibility = View.GONE
                playlistsAdapter.updatePlaylists(playlists)
            }
        })

        return view
    }
}
