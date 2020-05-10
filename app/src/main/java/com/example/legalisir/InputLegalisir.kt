package com.example.legalisir

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_input_legalisir.*

class InputLegalisir : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_legalisir)
        ref = FirebaseDatabase.getInstance().getReference("Legalisir")

        input_detail.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val jumlah = detail_Jumlah.text.toString()
        val radio: RadioButton = findViewById(radio_Pengambilan.checkedRadioButtonId)
        val pengambilan = radio.text as String

        if (pengambilan == "Jasa Kurir") {
            val intent = Intent(this, detail_Pengiriman::class.java)
            intent.putExtra("Jumlah", jumlah)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, detail_Pengambilan::class.java)
            intent.putExtra("Jumlah", jumlah)
            startActivity(intent)
        }
        }
    }

