package com.example.mahmudtoirovsherlari.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File
import java.io.Serializable

@Entity
class AudioPoem : Serializable{

    @PrimaryKey
    var id: Long? =null
    var name: String? = null
    var duration: Int = 0
    var audioID: Int? = null
    var book: String? = null
    var isSaved : Boolean = false
    var isLiked : Boolean = false

    constructor()

    override fun toString(): String {
        return "AudioPoem(id=$id, name=$name, duration=$duration, audioID=$audioID, book=$book, isSaved=$isSaved, isLiked=$isLiked)"
    }


}