package com.example.mahmudtoirovsherlari.activitties

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClicks()

        val sharedPref = this.getSharedPreferences(
            getString(R.string.isFirst), Context.MODE_PRIVATE
        )

        val edit = sharedPref.edit()
        val isF = sharedPref.getBoolean("is_first", true)

        val handler = Handler()
        val delay: Long = 3000
        handler.postDelayed({
            if (isF) {
                edit.putBoolean("is_first", false)
                edit.apply()
                startActivity(Intent(this@SplashActivity, InsertingActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }, delay)
    }


    private fun setClicks() {
        binding.buttonNext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


        binding.save.setOnClickListener {
            startActivity(Intent(this, InsertingActivity::class.java))
        }

    }


}