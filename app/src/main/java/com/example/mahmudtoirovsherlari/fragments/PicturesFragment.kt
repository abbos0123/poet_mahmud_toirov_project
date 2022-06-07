package com.example.mahmudtoirovsherlari.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mahmudtoirovsherlari.adapter.ImageAdapter
import com.example.mahmudtoirovsherlari.databinding.FragmentPicutresBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PicturesFragment : Fragment() {
    private lateinit var binding: FragmentPicutresBinding
    private lateinit var adapter: ImageAdapter
    private lateinit var viewPager2: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPicutresBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setAdapter()
    }

    private fun setAdapter() {
        viewPager2 = binding.viewPager
        adapter = ImageAdapter(null, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit  = 3
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false

        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        var transformer: CompositePageTransformer = CompositePageTransformer();
        transformer.addTransformer(MarginPageTransformer(50))
        transformer.addTransformer(object : ViewPager2.PageTransformer{
            override fun transformPage(page: View, position: Float) {
                val r = 1 - Math.abs(position)
                page.scaleY = 0.80f + r * 0.19f
            }
        })

        viewPager2.setPageTransformer(transformer)
    }
}