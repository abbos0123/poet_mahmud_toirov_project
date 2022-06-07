package com.example.mahmudtoirovsherlari.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mahmudtoirovsherlari.activitties.PoemContextActivity
import com.example.mahmudtoirovsherlari.adapter.TextPoemAdapter
import com.example.mahmudtoirovsherlari.databinding.FragmentTextPoemsBinding
import com.example.mahmudtoirovsherlari.models.Poem


class TextPoemsFragment : Fragment() {
    private lateinit var binding: FragmentTextPoemsBinding
    private lateinit var adapter: TextPoemAdapter
    private lateinit var list: ArrayList<Poem>
    var isLikedFragment: Boolean = false
    var isSavedFragment: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        for (i in 0..20) {
            var poem = Poem()
            when {
                isLikedFragment -> {
                    poem.name = "(Liked) Name of poem " + i
                }
                isSavedFragment -> {

                    poem.name = "(Saved) Name of poem " + i
                }
                else -> {

                    poem.name = "(__) name of poem " + i
                }
            }
            list.add(poem)
        }
    }
}