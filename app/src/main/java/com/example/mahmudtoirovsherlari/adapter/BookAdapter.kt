package com.example.mahmudtoirovsherlari.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.databinding.ImageContainerBinding
import com.example.mahmudtoirovsherlari.databinding.ItemBookBinding
import com.example.mahmudtoirovsherlari.models.Book
import com.example.mahmudtoirovsherlari.models.Image

class BookAdapter(var list: List<Book>) : RecyclerView.Adapter<BookAdapter.ImageViewHolder>() {


    class ImageViewHolder(private var binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(book: Book, position: Int) {
            binding.tvNameBook.text = book.name

            var imageId = book.imageId
            if (imageId == R.drawable.book_default) {
                binding.imageBlank.setImageResource(imageId)
                binding.image.visibility = View.INVISIBLE
                binding.imageBlank.visibility = View.VISIBLE
            }else{
                binding.image.setImageResource(imageId)
                binding.image.visibility = View.VISIBLE
                binding.imageBlank.visibility = View.INVISIBLE
            }

            val page = book.pages
            if (page != -1) {
                val text = "Yil: ${book.year} ($page bet)"
                binding.tvYear.text = text
            } else {
                val text = "Yil: ${book.year}"
                binding.tvYear.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}