package com.example.mahmudtoirovsherlari.activitties

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mahmudtoirovsherlari.MainActivity
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.databinding.ActivitySplashBinding
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Poem
import com.example.mahmudtoirovsherlari.models.Resource
import com.example.mahmudtoirovsherlari.repository.AudioPoemRepository
import com.example.mahmudtoirovsherlari.repository.PoemRepository
import com.example.mahmudtoirovsherlari.view_models.TextPoemsViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var database: AppDatabase
    private lateinit var poemRepository: PoemRepository
    private lateinit var audioPoemRepository: AudioPoemRepository
    private var isSaved = false
    private var isSavedFile = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectDatabase()

        setClicks()
    }

    private fun connectDatabase() {
        database = AppDatabase.getInstance(applicationContext)
        poemRepository = PoemRepository(database)
        audioPoemRepository = AudioPoemRepository(database)

    }


    private fun setClicks() {
        binding.buttonNext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.splash.setOnClickListener {
            deleteAll()
        }

        binding.save.setOnClickListener {
            savePoem()
        }
        binding.saveFile.setOnClickListener {
            saveAudioPoem()
        }
    }


    fun savePoem() {
        val poem = Poem()
        poem.name = "Orzu"
        poem.poemContext = Resource.poem9
        poem.book = "FIRST BOOK"

        val poem2 = Poem()
        poem2.name = "PARKENTIM"
        poem2.poemContext = Resource.poem9
        poem2.book = "FIRST BOOK"
        if (!isSaved) {
            poemRepository.savePoem(poem2)
            poemRepository.savePoem(poem)
            isSaved = true
        }

    }

    fun saveAudioPoem(){
        val poem = AudioPoem()
        poem.audioID = R.raw.rasululloh_keladur_nashida
        poem.name = "Rasululloh keladur"
        poem.isLiked = false

        val poem2 = AudioPoem()
        poem2.audioID = R.raw.bahrom_nazarov_ostona_hatlab
        poem2.name = "Onsonang oldida"
        poem2.isLiked = false

        if (!isSavedFile) {
            audioPoemRepository.savePoem(poem)
            audioPoemRepository.savePoem(poem2)
            isSaved = true
        }
    }

   fun deleteAll(){
       val list = ArrayList<Poem>()
       list.add(poemRepository.getPoem(2))
       list.add(poemRepository.getPoem(9))
       list.forEach {
           poemRepository.deletePoem(it)
       }
   }
}