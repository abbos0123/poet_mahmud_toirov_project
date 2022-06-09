package com.example.mahmudtoirovsherlari.adapter

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mahmudtoirovsherlari.databinding.ItemAudioPoemBinding
import com.example.mahmudtoirovsherlari.databinding.ItemPoemTextBinding
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Poem

class AudioPoemAdapter(var activity: FragmentActivity, var list: List<AudioPoem>, var onClick: OnClick) :
    RecyclerView.Adapter<AudioPoemAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemAudioPoemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(audioPoem: AudioPoem, position: Int) {

            binding.textName.text = audioPoem.name
            val time = MediaPlayer.create(activity.applicationContext, audioPoem.audioID!!).duration/1000
            binding.durationTime.text = getTimeString(time)

            binding.itemPoem.setOnClickListener {
                onClick.onItemClick(audioPoem, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAudioPoemBinding.inflate(
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
        fun onItemClick(audioPoem: AudioPoem, position: Int)
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