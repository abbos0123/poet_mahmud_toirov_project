package com.example.mahmudtoirovsherlari.daos

import androidx.room.*
import com.example.mahmudtoirovsherlari.models.Book
import com.example.mahmudtoirovsherlari.models.Poem

@Dao
interface BookDao {

    @Query("Select * from book")
    fun getAllBooks(): List<Book>

    @Query("Select * from book where id=:id")
    fun getBook(id: Long) : Book


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)


    @Delete
    fun deleteBook(book: Book)

}