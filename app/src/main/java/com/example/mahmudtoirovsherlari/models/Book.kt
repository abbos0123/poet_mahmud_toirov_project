package com.example.mahmudtoirovsherlari.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book {
    @PrimaryKey
    var id: Long? = null
    var name: String? = null
    var description: String? = null
    var imageId: Int = -1
    var year: String? = null
    var pages: Int? = null
}