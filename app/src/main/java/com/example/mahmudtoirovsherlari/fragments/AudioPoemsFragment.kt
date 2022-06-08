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
import com.example.mahmudtoirovsherlari.databinding.FragmentAudioPoemsBinding
import com.example.mahmudtoirovsherlari.models.AudioPoem
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
    private var fileList: ArrayList<File>? = null
    private var mySongs: ArrayList<File>? = null
    var isLikedFragment: Boolean = false
    var isSavedFragment: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    private fun setData() {
        adapter = AudioPoemAdapter(list, onClick = object : AudioPoemAdapter.OnClick {
            override fun onItemClick(audioPoem: AudioPoem, position: Int) {
                val intent = Intent(activity, AudioPlayerActivity::class.java)
                intent.putExtra("key", position)
                intent.putExtra("poem", audioPoem)
                intent.putExtra("position", position)
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

    fun displaySongs() {
        mySongs = findSong(Environment.getExternalStorageDirectory())
        fileList = mySongs
        list = ArrayList()
        for (i in 0 until fileList?.size!!) {
            val name = mySongs?.get(i)?.name
            val audio = AudioPoem()

            when {
                isLikedFragment -> {
                    audio.name = "(Liked) $name"
                    audio.audioFile = mySongs?.get(i)
                }
                isSavedFragment -> {
                    audio.name = "(Saved) $name"
                    audio.audioFile = mySongs?.get(i)
                }
                else -> {
                    audio.name = "(_) $name"
                    audio.audioFile = mySongs?.get(i)
                }
            }
            list.add(audio)

            setData()
        }
    }

    companion object{
         fun findSong(file: File): ArrayList<File>? {
            var  list = ArrayList<File>()
            val listFiles = file.listFiles()

            if (listFiles != null)
                for (singleFile in listFiles) {
                    if (singleFile.isDirectory && !singleFile.isHidden) {
                        val newList = findSong(singleFile)
                        if (newList != null)
                            list.addAll(newList)
                    } else {
                        if (singleFile.name.endsWith(".mp3") || singleFile.name.endsWith(".wav")) {
                            list.add(singleFile)
                        }
                    }
                }
            return list
        }

    }
}