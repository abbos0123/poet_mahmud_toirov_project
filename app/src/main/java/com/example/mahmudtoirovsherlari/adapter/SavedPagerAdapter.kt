package com.example.mahmudtoirovsherlari.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mahmudtoirovsherlari.fragments.AudioPoemsFragment
import com.example.mahmudtoirovsherlari.fragments.TextPoemsFragment

class SavedPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            val fragment = TextPoemsFragment()
            fragment.isSavedFragment = true
            return fragment

        } else {
            val fragment = AudioPoemsFragment()
            fragment.isSavedFragment = true
            return fragment
        }

    }


}