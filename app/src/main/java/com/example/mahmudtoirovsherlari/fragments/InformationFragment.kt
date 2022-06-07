package com.example.mahmudtoirovsherlari.fragments

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.databinding.FragmentInformationBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InformationFragment : Fragment() {
    private lateinit var binding: FragmentInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && false) {
            binding.textInfo.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setClick()
    }


    private fun setClick() {
        binding.imagePoet.setOnClickListener {
            findNavController().navigate(R.id.action_about_poet_to_picturesFragment)
        }
    }

}