package com.example.mahmudtoirovsherlari.activitties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.adapter.AudioImageAdapter
import com.example.mahmudtoirovsherlari.adapter.ImageAdapter
import com.example.mahmudtoirovsherlari.databinding.ActivityAudioPlayerBinding
import kotlin.math.abs

class AudioPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAudioPlayerBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: AudioImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager2 = binding.viewPager

        setClicks()
        setAdapter()
    }


    private fun setClicks() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
            viewPager2 = binding.viewPager
            adapter = AudioImageAdapter()
            viewPager2.adapter = adapter
            viewPager2.offscreenPageLimit  = 3
            viewPager2.clipChildren = false
            viewPager2.clipToPadding = false

            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            var transformer = CompositePageTransformer();
            transformer.addTransformer(MarginPageTransformer(50))
            transformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.80f + r * 0.19f
            }

            viewPager2.setPageTransformer(transformer)

    }


}