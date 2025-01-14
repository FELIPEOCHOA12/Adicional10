package com.example.bookshelf.data

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("items") val books: List<Book>
)
//Felipe Hebert Ochoa Patiño
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String
)
