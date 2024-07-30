package com.example.musicplaylistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlaylistsAdapter(
    private var playlists: List<Playlist>,
    private val onDeleteClick: (Playlist) -> Unit
) : RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.nameTextView.text = playlist.name
        holder.genreTextView.text = playlist.genre
        holder.descriptionTextView.text = playlist.description
        holder.sortByTextView.text = playlist.sortBy
        Glide.with(holder.itemView.context)
            .load(playlist.coverImageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.coverImageView)

        holder.deleteButton.setOnClickListener {
            onDeleteClick(playlist)
        }
    }

    override fun getItemCount() = playlists.size

    fun updatePlaylists(newPlaylists: List<Playlist>) {
        playlists = newPlaylists
        notifyDataSetChanged()
    }

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_view_playlist_name)
        val genreTextView: TextView = itemView.findViewById(R.id.text_view_genre)
        val descriptionTextView: TextView = itemView.findViewById(R.id.text_view_description)
        val sortByTextView: TextView = itemView.findViewById(R.id.text_view_sort_by)
        val coverImageView: ImageView = itemView.findViewById(R.id.image_view_cover)
        val deleteButton: Button = itemView.findViewById(R.id.button_delete)
    }
}
