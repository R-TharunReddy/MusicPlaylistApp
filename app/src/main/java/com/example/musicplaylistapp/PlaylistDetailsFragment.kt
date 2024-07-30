package com.example.musicplaylistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels // Ensure this import is present

class PlaylistDetailsFragment : DialogFragment() {

    private val playlistViewModel: PlaylistViewModel by activityViewModels()

    private lateinit var playlistNameEditText: EditText
    private lateinit var genreSpinner: Spinner
    private lateinit var descriptionEditText: EditText
    private lateinit var coverImageEditText: EditText
    private lateinit var sortRadioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist_details, container, false)

        // Initialize UI components
        playlistNameEditText = view.findViewById(R.id.edit_text_playlist_name)
        genreSpinner = view.findViewById(R.id.spinner_genre)
        descriptionEditText = view.findViewById(R.id.edit_text_description)
        coverImageEditText = view.findViewById(R.id.edit_text_cover_image)
        sortRadioGroup = view.findViewById(R.id.radio_group_sort)

        val addButton: Button = view.findViewById(R.id.button_add_playlist)
        addButton.setOnClickListener {
            addPlaylist()
        }

        // Populate spinner with genres
        val genres = arrayOf("Pop", "Rock", "Jazz", "Classical", "Hip-hop")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genreSpinner.adapter = adapter

        return view
    }

    private fun addPlaylist() {
        val playlistName = playlistNameEditText.text.toString()
        val genre = genreSpinner.selectedItem.toString()
        val description = descriptionEditText.text.toString()
        val coverImageUrl = coverImageEditText.text.toString()
        val sortBy = when (sortRadioGroup.checkedRadioButtonId) {
            R.id.radio_sort_alphabetical -> "Alphabetical"
            R.id.radio_sort_most_recent -> "Most Recent"
            else -> "Unknown"
        }

        // Validate input
        if (playlistName.isBlank()) {
            Toast.makeText(requireContext(), "Playlist name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        // Save the playlist in ViewModel
        val playlist = Playlist(playlistName, genre, sortBy, description, coverImageUrl)
        playlistViewModel.addPlaylist(playlist)
        Toast.makeText(requireContext(), "Playlist added", Toast.LENGTH_SHORT).show()
    }
}
