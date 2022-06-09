package com.example.mahmudtoirovsherlari.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.activitties.AudioPlayerActivity
import com.example.mahmudtoirovsherlari.adapter.AudioPoemAdapter
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.databinding.FragmentAudioPoemsBinding
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.repository.AudioPoemRepository
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File
import java.util.jar.Manifest


class AudioPoemsFragment : Fragment() {

    private lateinit var binding: FragmentAudioPoemsBinding
    private lateinit var adapter: AudioPoemAdapter
    private lateinit var list: ArrayList<AudioPoem>
    private lateinit var listLiked: ArrayList<AudioPoem>
    private lateinit var listSaved: ArrayList<AudioPoem>
    private lateinit var database: AppDatabase
    private lateinit var repository: AudioPoemRepository
    var isLikedFragment: Boolean = false
    var isSavedFragment: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.getInstance(activity?.applicationContext!!)
        repository = AudioPoemRepository(database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAudioPoemsBinding.inflate(layoutInflater)

        setClicks()
        runtimePermission()

        return binding.root
    }

    private fun setClicks() {

    }

    private fun setData(list: ArrayList<AudioPoem>) {
        adapter = AudioPoemAdapter(requireActivity(), list, onClick = object : AudioPoemAdapter.OnClick {
            override fun onItemClick(audioPoem: AudioPoem, position: Int) {
                val intent = Intent(activity, AudioPlayerActivity::class.java)
                intent.putExtra("id", audioPoem.audioID)
                intent.putExtra("position", position)
                intent.putExtra("isSaved", isSavedFragment)
                intent.putExtra("isLiked", isLikedFragment)
                startActivity(intent)
            }

        })

        binding.rv.adapter = adapter
    }


    private fun runtimePermission() {
        Dexter.withContext(activity)
            .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    displaySongs()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }

            }).check()
    }

    override fun onResume() {
        super.onResume()
        displaySongs()
    }

    fun displaySongs() {
        list = ArrayList()
        listLiked = ArrayList()
        listSaved = ArrayList()
        val allSongs = repository.getAllAudioPoem()

        for (i in allSongs.indices) {
            val audio = allSongs[i]

            if (audio.isLiked)
                listLiked.add(audio)

             if (audio.isSaved)
                listSaved.add(audio)

            list.add(audio)

        }

        if (isSavedFragment)
            this.list = listSaved
        else if (isLikedFragment)
            this.list = listLiked
        else
            this.list = list

        setData(list)
    }

}