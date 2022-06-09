package com.example.mahmudtoirovsherlari.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Poem {
    @PrimaryKey
    var id: Long? = null

    var name: String?= null
    var poemContext: String?= null
    var book: String? = null
    var time: String? = null
    var isSaved: Boolean = false
    var isLiked: Boolean = false

    constructor(id: Long, name: String?, poem: String?, book: String?, time: String?) {
        this.id = id
        this.name = name
        this.poemContext = poem
        this.book = book
        this.time = time
    }

    constructor()

    override fun toString(): String {
        return "Poem(id=$id, name=$name, poem=$poemContext, book=$book, time=$time)"
    }


}