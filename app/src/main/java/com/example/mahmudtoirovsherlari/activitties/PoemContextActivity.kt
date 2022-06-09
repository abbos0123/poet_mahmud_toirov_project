package com.example.mahmudtoirovsherlari.activitties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.databinding.ActivityPoemContextBinding
import com.example.mahmudtoirovsherlari.models.Poem
import com.example.mahmudtoirovsherlari.models.Resource
import com.example.mahmudtoirovsherlari.repository.PoemRepository
import java.util.*

class PoemContextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPoemContextBinding
    private lateinit var poem: Poem
    private lateinit var repository: PoemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoemContextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = PoemRepository(AppDatabase.getInstance(applicationContext))
        getData()
        setDataToView()

        setClicks()
    }

    private fun setDataToView() {
        binding.poemText.text = poem.poemContext
        binding.poemTitleText.text = poem.name?.uppercase()
        binding.poemTitleText2.text = poem.name?.uppercase()

        if (poem.isLiked) {
            binding.imageLike.setImageResource(R.drawable.heart_full)
        } else {
            binding.imageLike.setImageResource(R.drawable.heart_blank)
        }

        if (poem.isSaved) {
            binding.imageSave.setImageResource(R.drawable.save_full)
        } else {
            binding.imageSave.setImageResource(R.drawable.save_blank)
        }

    }


    private fun setClicks() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.likeButton.setOnClickListener {
            poem.isLiked = !poem.isLiked

            if (poem.isLiked) {
                binding.imageLike.setImageResource(R.drawable.heart_full)
                poem.isLiked = true
            } else {
                binding.imageLike.setImageResource(R.drawable.heart_blank)
                poem.isLiked = false
            }

            repository.savePoem(poem)
            poem = repository.getPoem(poem.id!!)
        }

        binding.saveButton.setOnClickListener {
            poem.isSaved = !poem.isSaved

            if (poem.isSaved) {
                binding.imageSave.setImageResource(R.drawable.save_full)
                poem.isSaved = true
            } else {
                binding.imageSave.setImageResource(R.drawable.save_blank)
                poem.isSaved = false
            }

            repository.savePoem(poem)
            poem = repository.getPoem(poem.id!!)
        }

    }

    private fun getData() {
      poem = repository.getPoem(intent.getLongExtra("id", 1))
    }
}