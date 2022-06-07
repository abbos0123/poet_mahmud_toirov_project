package com.example.mahmudtoirovsherlari.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mahmudtoirovsherlari.databinding.ItemPoemTextBinding
import com.example.mahmudtoirovsherlari.models.Poem

class TextPoemAdapter(var list: List<Poem>, var onClick: OnClick) :
    RecyclerView.Adapter<TextPoemAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemPoemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(poem: Poem, position: Int) {

            binding.itemPoem.setOnClickListener {
                onClick.onItemClick(poem, position)
            }

            binding.textName.text = poem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPoemTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnClick {
        fun onItemClick(poem: Poem, position: Int)
        fun onLikeClick(poem: Poem, position: Int)
        fun onSaveClick(poem: Poem, position: Int)
    }
}