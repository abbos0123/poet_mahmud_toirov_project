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
import com.example.mahmudtoirovsherlari.databinding.ActivityAudioPlayerBinding
import com.example.mahmudtoirovsherlari.fragments.AudioPoemsFragment
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Poem
import java.io.File
import kotlin.math.abs

class AudioPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAudioPlayerBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: AudioImageAdapter
    private var mySongs: ArrayList<File>? = null
    private var postion: Int = 0
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
        mySongs = AudioPoemsFragment.findSong(Environment.getExternalStorageDirectory())
        var songName = (myIntent.getSerializableExtra("poem") as AudioPoem?)?.name
        postion = bundle?.getInt("position", 0)!!

        var uri: Uri = Uri.parse(mySongs?.get(postion).toString())

        sName = mySongs?.get(postion)?.name
        binding.nameAudioPoet.text = sName

        mediaPlayer = MediaPlayer.create(applicationContext, uri)
        mediaPlayer?.start()

        setClicks()
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
            postion = ((postion + 1) % mySongs?.size!!)

            val uri: Uri = Uri.parse(mySongs?.get(postion)?.toString())
            mediaPlayer = MediaPlayer.create(applicationContext, uri)
            binding.nameAudioPoet.text = mySongs?.get(postion)?.name
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
                postion = mySongs?.size?.minus(1)!!
            } else {
                postion -= 1
            }

            val uri: Uri = Uri.parse(mySongs?.get(postion)?.toString())
            mediaPlayer = MediaPlayer.create(applicationContext, uri)
            binding.nameAudioPoet.text = mySongs?.get(postion)?.name
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

        mediaPlayer?.setOnCompletionListener {
            Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show()
            binding.seekBar.progress = 0
            binding.imagePlay.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
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
}