package com.example.mahmudtoirovsherlari.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.databinding.ImageContainerBinding
import com.example.mahmudtoirovsherlari.models.Image

class AudioImageAdapter() : RecyclerView.Adapter<AudioImageAdapter.ImageViewHolder>() {


    class ImageViewHolder(private var binding: ImageContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            when(position){
                1 ->{
                    binding.imageView.setImageResource(R.drawable.pic_1)
                }
                2 ->{
                    binding.imageView.setImageResource(R.drawable.pic_2)
                }
                3 ->{
                    binding.imageView.setImageResource(R.drawable.pic_3)
                }
                4 ->{
                    binding.imageView.setImageResource(R.drawable.pic_4_1)
                }
                5 ->{
                    binding.imageView.setImageResource(R.drawable.pic_5_1)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return 6
    }
}