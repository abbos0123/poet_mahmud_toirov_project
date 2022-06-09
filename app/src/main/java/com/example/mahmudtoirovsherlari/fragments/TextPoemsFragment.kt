package com.example.mahmudtoirovsherlari.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mahmudtoirovsherlari.activitties.PoemContextActivity
import com.example.mahmudtoirovsherlari.adapter.TextPoemAdapter
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.databinding.FragmentTextPoemsBinding
import com.example.mahmudtoirovsherlari.models.Poem
import com.example.mahmudtoirovsherlari.repository.PoemRepository


class TextPoemsFragment : Fragment() {
    private lateinit var binding: FragmentTextPoemsBinding
    private lateinit var adapter: TextPoemAdapter
    private lateinit var list: List<Poem>
    private lateinit var poemRepository: PoemRepository
    var isLikedFragment: Boolean = false
    var isSavedFragment: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        poemRepository = PoemRepository(AppDatabase.getInstance(activity?.applicationContext!!))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextPoemsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadData()
        adapter = TextPoemAdapter(list, object : TextPoemAdapter.OnClick {
            override fun onItemClick(poem: Poem, position: Int) {
                val intent = Intent(activity, PoemContextActivity::class.java)
                intent.putExtra("key", position)
                intent.putExtra("id", poem.id)
                startActivity(intent)

            }

            override fun onLikeClick(poem: Poem, position: Int) {


            }

            override fun onSaveClick(poem: Poem, position: Int) {


            }

        })
        binding.rv.adapter = adapter

    }

    private fun loadData() {
        list = ArrayList()
        list = poemRepository.getAllPoem()
        if (isLikedFragment) {
            val listTem = ArrayList<Poem>()
            list.forEach {
                if (it.isLiked)
                    listTem.add(it)
            }
            list = listTem

        } else if (isSavedFragment) {
            val listTem = ArrayList<Poem>()
            list.forEach {
                if (it.isSaved)
                    listTem.add(it)
            }
            list = listTem
        }
    }
}