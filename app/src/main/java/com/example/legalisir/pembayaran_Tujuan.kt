package com.example.legalisir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_pembayaran__tujuan.*

class pembayaran_Tujuan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran__tujuan)
        bayarInfo()
    }
    private fun bayarInfo() {
            val bayarInfo = intent.extras
            val bank = bayarInfo?.getString("Bank")
            val biaya = bayarInfo?.getString("Biaya")
//        biaya.toInt()
        if (bank == "Bank Mandiri"){
            var pict :ImageView = findViewById(R.id.pict_Bank)
            pict.setImageResource(R.drawable.mandiri)
        }
        else if (bank == "Bank BCA"){
            var pict :ImageView = findViewById(R.id.pict_Bank)
            pict.setImageResource(R.drawable.bca)
        }
        else if (bank == "Bank BNI"){
            var pict :ImageView = findViewById(R.id.pict_Bank)
            pict.setImageResource(R.drawable.bni)
        }
        text_Biaya.setText(biaya)
    }
}

