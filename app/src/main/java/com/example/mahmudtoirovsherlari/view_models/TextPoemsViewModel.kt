package com.example.mahmudtoirovsherlari.view_models


import androidx.lifecycle.ViewModel
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.models.Poem
import com.example.mahmudtoirovsherlari.repository.PoemRepository

class TextPoemsViewModel : ViewModel() {

    private val poemRepository: PoemRepository = PoemRepository(appDatabase!!)

    fun savePoem(poem: Poem) = poemRepository.savePoem(poem)

    fun getPoem(id: Long) = poemRepository.getPoem(id)

    fun getAllPoems() = poemRepository.getAllPoem()

    fun deletePoem(poem: Poem) = poemRepository.deletePoem(poem)



    companion object {
        var appDatabase: AppDatabase? = null
    }
}