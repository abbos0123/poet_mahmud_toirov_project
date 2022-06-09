package com.example.mahmudtoirovsherlari.repository

import com.example.mahmudtoirovsherlari.daos.PoemDao
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.models.Poem

class PoemRepository(private val appDatabase: AppDatabase) {

    private val poemDao: PoemDao = appDatabase.poemDao()

     fun savePoem(poem: Poem) = poemDao.insertPoem(poem)

     fun getPoem(id: Long) = poemDao.getPoem(id)

     fun getAllPoem() = poemDao.getAllPoems()

     fun deletePoem(poem: Poem) = poemDao.deletePoem(poem)
}