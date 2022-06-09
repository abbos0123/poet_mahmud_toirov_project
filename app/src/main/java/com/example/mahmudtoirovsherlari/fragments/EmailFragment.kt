package com.example.mahmudtoirovsherlari.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mahmudtoirovsherlari.R

class EmailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onResume() {
        super.onResume()

        val errorMessageBuilder = AlertDialog.Builder(activity)
        errorMessageBuilder.setMessage("Email profilga o'tishni iloji qo'q. Noma'lum xatolik yuz berdi!")
            .setPositiveButton("Ok") { dialogInterface, _ ->
                dialogInterface.cancel()
                findNavController().popBackStack()
            }
            .setCancelable(false)

        val errorDialog = errorMessageBuilder.create()
        errorDialog.setTitle("Aniqlanmagan xatolik...")


        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Email profilga o'tishni hohlasizmi ?")
            .setPositiveButton("Ha") { dialogInterface, _ ->
                dialogInterface.cancel()
                errorDialog.show()
            }
            .setCancelable(false)
            .setNegativeButton(
                "Yo'q"
            ) { dialogInterface, i ->
                dialogInterface.cancel()
                findNavController().popBackStack()
            }

        val dialog = builder.create()
        dialog.setTitle("Email orqali ulanish...")

        dialog.show()

    }
}