package com.example.mahmudtoirovsherlari.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.net.UrlQuerySanitizer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mahmudtoirovsherlari.R


class TelegramFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_telegram, container, false)
    }

    override fun onResume() {
        super.onResume()
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Telegram profilga o'tishni hohlasizmi ?")
            .setPositiveButton("Ha", DialogInterface.OnClickListener { dialogInterface, i ->
                val link = "https://t.me/uzMOXIRbek"
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intent)

                dialogInterface.cancel()
                findNavController().popBackStack()
            })
            .setCancelable(false)
            .setNegativeButton(
                "Yo'q"
            ) { dialogInterface, i ->
                dialogInterface.cancel()
                findNavController().popBackStack()
            }

        val dialog = builder.create()
        dialog.setTitle("Telegram orqali ulanish...")
        dialog.show()
    }
}