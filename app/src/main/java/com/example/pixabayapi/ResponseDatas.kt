package com.example.pixabayapi

data class Image(
    val id: Long,
    val tags: String,
    val largeImageURL: String,
    val likes: Long
)

data class ImageResponse(
    val hits: List<Image>
)