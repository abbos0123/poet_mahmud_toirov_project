package com.example.mahmudtoirovsherlari.repository

import com.example.mahmudtoirovsherlari.daos.AudioPoemDao
import com.example.mahmudtoirovsherlari.daos.PoemDao
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Poem

class AudioPoemRepository(private val appDatabase: AppDatabase) {

    private val audioPoemDao: AudioPoemDao = appDatabase.audioPoemDao()

     fun savePoem(audioPoem: AudioPoem) = audioPoemDao.insertPoem(audioPoem)

     fun getPoem(id: Long) = audioPoemDao.getPoem(id)

     fun getAllAudioPoem() = audioPoemDao.getAllPoems()

     fun deletePoem(audioPoem: AudioPoem) = audioPoemDao.deletePoem(audioPoem)
}