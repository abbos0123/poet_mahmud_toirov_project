package com.example.mahmudtoirovsherlari.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.adapter.PagerAdapter
import com.example.mahmudtoirovsherlari.adapter.SavedPagerAdapter
import com.example.mahmudtoirovsherlari.databinding.FragmentSavedPoemsBinding


class SavedPoemsFragment : Fragment() {
    private lateinit var binding: FragmentSavedPoemsBinding
    private lateinit var adapter: SavedPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedPoemsBinding.inflate(layoutInflater)

        setView()
        return binding.root
    }

    private fun setView() {
        adapter = SavedPagerAdapter(activity as FragmentActivity)
        binding.viewPager2.adapter = adapter
    }

}