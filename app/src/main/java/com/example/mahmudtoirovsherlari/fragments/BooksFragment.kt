package com.example.mahmudtoirovsherlari.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.adapter.BookAdapter
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.databinding.FragmentBooksBinding
import com.example.mahmudtoirovsherlari.models.Book
import com.example.mahmudtoirovsherlari.repository.BookRepository

class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var adater: BookAdapter
    private lateinit var list: List<Book>
    private lateinit var database: AppDatabase
    private lateinit var bookRepository: BookRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksBinding.inflate(layoutInflater)

        loadData()
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        adater = BookAdapter(list)
        binding.rv.adapter = adater
    }

    private fun loadData() {
        database = AppDatabase.getInstance(activity?.applicationContext!!)
        bookRepository = BookRepository(database)
        list = bookRepository.getAllBooks()
    }

}