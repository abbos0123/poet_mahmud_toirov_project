package com.example.mahmudtoirovsherlari.repository

import com.example.mahmudtoirovsherlari.daos.BookDao
import com.example.mahmudtoirovsherlari.daos.PoemDao
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.models.Book
import com.example.mahmudtoirovsherlari.models.Poem

class BookRepository(private val appDatabase: AppDatabase) {

    private val bookDao: BookDao = appDatabase.bookDao()

    fun saveBook(book: Book) = bookDao.insertBook(book)

    fun getBook(id: Long) = bookDao.getBook(id)

    fun getAllBooks() = bookDao.getAllBooks()

    fun deleteBook(book: Book) = bookDao.deleteBook(book)
}