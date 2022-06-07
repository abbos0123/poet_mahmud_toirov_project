package com.example.mahmudtoirovsherlari.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.mahmudtoirovsherlari.adapter.PagerAdapter
import com.example.mahmudtoirovsherlari.databinding.FragmentLikedPoemBinding


class LikedPoemFragment : Fragment() {
    private lateinit var binding: FragmentLikedPoemBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikedPoemBinding.inflate(layoutInflater)

        setView()
        return binding.root
    }

    private fun setView() {
        val adapter = PagerAdapter(activity as FragmentActivity)
        binding.viewPager2.adapter = adapter
    }

}