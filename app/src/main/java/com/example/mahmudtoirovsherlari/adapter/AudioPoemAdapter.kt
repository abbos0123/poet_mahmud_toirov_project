package com.example.mahmudtoirovsherlari.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mahmudtoirovsherlari.databinding.ItemAudioPoemBinding
import com.example.mahmudtoirovsherlari.databinding.ItemPoemTextBinding
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Poem

class AudioPoemAdapter(var list: List<AudioPoem>, var onClick: OnClick) :
    RecyclerView.Adapter<AudioPoemAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemAudioPoemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {

            binding.itemPoem.setOnClickListener {
                onClick.onItemClick(position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAudioPoemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnClick {
        fun onItemClick(position: Int)
    }
}