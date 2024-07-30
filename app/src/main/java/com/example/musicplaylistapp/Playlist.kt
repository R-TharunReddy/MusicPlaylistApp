package com.example.musicplaylistapp

data class Playlist(
    val name: String,
    val genre: String,
    val sortBy: String,
    val description: String,
    val coverImageUrl: String,
    val creationTime: Long = System.currentTimeMillis()
)
