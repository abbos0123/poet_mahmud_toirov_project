package com.example.mahmudtoirovsherlari.models

import java.io.File
import java.io.Serializable

class AudioPoem : Serializable{

    var id: Long = 0
    var name: String? = null
    var duration: Int = 0
    var audioPath: String? = null
    var audioFile: File? = null
    var book: String? = null
    var isSaved : Boolean = false
    var isLiked : Boolean = false

    constructor()

    constructor(
        id: Long,
        name: String?,
        duration: Int,
        audioPath: String?,
        book: String?,
        isSaved: Boolean,
        isLiked: Boolean
    ) {
        this.id = id
        this.name = name
        this.duration = duration
        this.audioPath = audioPath
        this.book = book
        this.isSaved = isSaved
        this.isLiked = isLiked
    }

    override fun toString(): String {
        return "AudioPoem(id=$id, name=$name, duration=$duration, audioPath=$audioPath, book=$book, isSaved=$isSaved, isLiked=$isLiked)"
    }


}