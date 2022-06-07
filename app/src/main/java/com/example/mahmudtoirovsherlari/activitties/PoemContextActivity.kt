package com.example.mahmudtoirovsherlari.activitties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.databinding.ActivityPoemContextBinding
import com.example.mahmudtoirovsherlari.models.Poem
import com.example.mahmudtoirovsherlari.models.Resource

class PoemContextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPoemContextBinding
    private var poemContext = ""
    private lateinit var poem: Poem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoemContextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        setDataToView()

        setClicks()
    }

    private fun setDataToView() {
        binding.poemText.text = poemContext

        if (poem.isLiked) {
            binding.imageLike.setImageResource(R.drawable.heart_full)
        } else {
            binding.imageLike.setImageResource(R.drawable.heart_blank)
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
            } else {
                binding.imageLike.setImageResource(R.drawable.heart_blank)
            }

            if (poem.isSaved) {
                binding.imageSave.setImageResource(R.drawable.save_full)
            } else {
                binding.imageSave.setImageResource(R.drawable.save_blank)
            }
        }

        binding.saveButton.setOnClickListener {
            poem.isSaved = !poem.isSaved

            if (poem.isSaved) {
                binding.imageSave.setImageResource(R.drawable.save_full)
            } else {
                binding.imageSave.setImageResource(R.drawable.save_blank)
            }
        }

        binding.poemTitleText.text = " Title " + poem.id

    }

    private fun getData() {
        poemContext = Resource.poem1
        val n = intent.getIntExtra("key", -1)

        poem = Poem()
        poem.poemContext = poemContext
        poem.id = n.toLong()
        poem.isLiked = true

    }
}