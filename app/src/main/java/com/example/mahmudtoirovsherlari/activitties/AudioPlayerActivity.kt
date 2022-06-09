package com.example.mahmudtoirovsherlari.activitties

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.adapter.AudioImageAdapter
import com.example.mahmudtoirovsherlari.adapter.ImageAdapter
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.databinding.ActivityAudioPlayerBinding
import com.example.mahmudtoirovsherlari.fragments.AudioPoemsFragment
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Poem
import com.example.mahmudtoirovsherlari.repository.AudioPoemRepository
import java.io.File
import kotlin.math.abs

class AudioPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAudioPlayerBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: AudioImageAdapter
    private lateinit var allSongs: ArrayList<AudioPoem>
    private lateinit var listLiked: ArrayList<AudioPoem>
    private lateinit var listSaved: ArrayList<AudioPoem>
    private  var isLikedFragment: Boolean = false
    private  var isSavedFragment: Boolean = false
    private var postion: Int = 0
    private lateinit var database: AppDatabase
    private lateinit var repository: AudioPoemRepository
    private var sName: String? = null

    companion object {
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager2 = binding.viewPager

        setAdapter()
        setMediaPlayer()
        val myIntent = intent
        val bundle: Bundle? = myIntent.extras
        isLikedFragment = bundle?.getBoolean("isLiked", false)!!
        isSavedFragment = bundle.getBoolean("isSaved", false)
        postion = bundle.getInt("position", 0)
        getData()

        val audiPoem = allSongs[postion]

        sName = audiPoem.name
        binding.nameAudioPoet.text = sName
        setImageForLike(audiPoem.isLiked)
        setImageForSave(audiPoem.isSaved)

        mediaPlayer = MediaPlayer.create(applicationContext, audiPoem.audioID!!)
        mediaPlayer?.start()

        setClicks()
    }

    private fun getData() {
        database = AppDatabase.getInstance(applicationContext)
        repository = AudioPoemRepository(database)
        sortList()

    }

    private fun setMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }
    }


    private fun setClicks() {

        val updateSeekBar = object : Thread() {
            override fun run() {
                super.run()
                val totalDuration: Int = mediaPlayer?.duration as Int
                var currentPosition = 0

                while (currentPosition < totalDuration) {
                    try {
                        sleep(1000)
                        currentPosition = mediaPlayer?.currentPosition!!
                        binding.seekBar.progress = currentPosition
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }

                }
            }
        }


        binding.backButton.setOnClickListener {
            finish()
        }


        binding.buttonPause.setOnClickListener {
            if (mediaPlayer?.isPlaying!!) {
                binding.imagePlay.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
                mediaPlayer?.pause()
            } else {
                binding.imagePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
                mediaPlayer?.start()
            }
        }

        binding.buttonNext.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()

            postion = ((postion + 1) % allSongs.size)
            val audioPoem = allSongs[postion]

            mediaPlayer = MediaPlayer.create(applicationContext, audioPoem.audioID!!)
            binding.nameAudioPoet.text = allSongs[postion].name

            setImageForLike(audioPoem.isLiked)
            setImageForSave(audioPoem.isSaved)

            mediaPlayer?.start()
            binding.imagePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
            binding.seekBar.max = mediaPlayer?.duration!!
            binding.seekBar.progress = 0

            val updateSeekBar2 = object : Thread() {
                override fun run() {
                    super.run()
                    val totalDuration: Int = mediaPlayer?.duration as Int
                    var currentPosition = 0

                    while (currentPosition < totalDuration) {
                        try {
                            sleep(1000)
                            currentPosition = mediaPlayer?.currentPosition!!
                            binding.seekBar.progress = currentPosition
                        } catch (exception: Exception) {
                            exception.printStackTrace()
                        }

                    }
                }
            }

            updateSeekBar2.start()
            binding.totalTime.text = getTimeString(mediaPlayer?.duration!! / 1000)
        }

        binding.buttonBack.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            if (postion - 1 < 0) {
                postion = allSongs.size.minus(1)
            } else {
                postion -= 1
            }

            val audioPoem = allSongs[postion]
            mediaPlayer = MediaPlayer.create(applicationContext, audioPoem.audioID!!)
            mediaPlayer?.start()

            setImageForLike(audioPoem.isLiked)
            setImageForSave(audioPoem.isSaved)

            binding.nameAudioPoet.text = allSongs.get(postion).name
            binding.imagePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
            binding.seekBar.max = mediaPlayer?.duration!!
            binding.seekBar.progress = 0

            val updateSeekBar2 = object : Thread() {
                override fun run() {
                    super.run()
                    val totalDuration: Int = mediaPlayer?.duration as Int
                    var currentPosition = 0

                    while (currentPosition < totalDuration) {
                        try {
                            sleep(1000)
                            currentPosition = mediaPlayer?.currentPosition!!
                            binding.seekBar.progress = currentPosition
                        } catch (exception: Exception) {
                            exception.printStackTrace()
                        }

                    }
                }
            }

            updateSeekBar2.start()
            binding.totalTime.text = getTimeString(mediaPlayer?.duration!! / 1000)
        }

        mediaPlayer?.setOnCompletionListener {
            Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show()
            binding.seekBar.progress = 0
            binding.imagePlay.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
        }

        binding.buttonLike.setOnClickListener {
            val audioPoem = allSongs[postion]
            audioPoem.isLiked = !audioPoem.isLiked

            repository.savePoem(audioPoem)
            setImageForLike(audioPoem.isLiked)
        }


        binding.buttonSave.setOnClickListener {
            val audioPoem = allSongs[postion]
            audioPoem.isSaved = !audioPoem.isSaved

            repository.savePoem(audioPoem)
            setImageForSave(audioPoem.isSaved)
        }


        binding.seekBar.max = mediaPlayer?.duration!!
        updateSeekBar.start()
        binding.totalTime.text = getTimeString(mediaPlayer?.duration!! / 1000)


        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mediaPlayer?.seekTo(p0?.progress!!)
            }

        })

        val handler = Handler()
        val delay: Long = 1000

        handler.postDelayed(object : Runnable {
            override fun run() {
                binding.currentTime.text = getTimeString(mediaPlayer?.currentPosition!! / 1000)
                handler.postDelayed(this, delay)
            }

        }, delay)
    }

    private fun setAdapter() {
        viewPager2 = binding.viewPager
        adapter = AudioImageAdapter()
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false

        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        var transformer = CompositePageTransformer();
        transformer.addTransformer(MarginPageTransformer(50))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.80f + r * 0.19f
        }

        viewPager2.setPageTransformer(transformer)

    }

    private fun getTimeString(time: Int): String {

        val min = time / 60
        val sec = time % 60
        var minStr = min.toString()
        if (min < 10) minStr = "0$min"

        var secStr = sec.toString()
        if (sec < 10) secStr = "0$sec"

        return "$minStr:$secStr"
    }

    private fun setImageForLike(boolean: Boolean) {
        if (boolean) {
            binding.imageLike.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imageLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun setImageForSave(boolean: Boolean) {
        if (boolean) {
            binding.imageSave.setImageResource(R.drawable.ic_baseline_bookmark_24)
        } else {
            binding.imageSave.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    private fun sortList(){

        allSongs = ArrayList()
        listLiked = ArrayList()
        listSaved = ArrayList()
        val allSongsList = repository.getAllAudioPoem()

        for (i in allSongsList.indices) {
            val audio = allSongsList[i]

            if (audio.isLiked)
                listLiked.add(audio)

            if (audio.isSaved)
                listSaved.add(audio)

            allSongs.add(audio)

        }

        if (isSavedFragment)
            allSongs = listSaved
        else if (isLikedFragment)
            allSongs = listLiked

    }
}