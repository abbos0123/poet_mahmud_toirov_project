package com.example.mahmudtoirovsherlari.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.activitties.AudioPlayerActivity
import com.example.mahmudtoirovsherlari.adapter.AudioPoemAdapter
import com.example.mahmudtoirovsherlari.databinding.FragmentAudioPoemsBinding
import com.example.mahmudtoirovsherlari.models.AudioPoem


class AudioPoemsFragment : Fragment() {

    private lateinit var binding: FragmentAudioPoemsBinding
    private lateinit var adapter: AudioPoemAdapter
    private lateinit var list: ArrayList<AudioPoem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAudioPoemsBinding.inflate(layoutInflater)

        getData()
        setData()
        setClicks()

        return binding.root
    }

    private fun setClicks() {

    }

    private fun setData() {
        adapter = AudioPoemAdapter(list, onClick = object : AudioPoemAdapter.OnClick {
            override fun onItemClick(position: Int) {
                val intent = Intent(activity, AudioPlayerActivity::class.java)
                intent.putExtra("key", position)
                startActivity(intent)
            }

        })

        binding.rv.adapter = adapter
    }

    private fun getData() {
        list = ArrayList()

        for (i in 1..20) {
            val audio = AudioPoem()
            list.add(audio)
        }
    }


    override fun onResume() {
        super.onResume()

    }
}