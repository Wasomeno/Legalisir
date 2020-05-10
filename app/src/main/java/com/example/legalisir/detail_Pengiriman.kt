package com.example.legalisir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail__pengiriman.*
import kotlinx.android.synthetic.main.activity_input_legalisir.*


class detail_Pengiriman : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    var jasaPilih = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail__pengiriman)
        val jasa = resources.getStringArray(R.array.Jasa)
        val jasaSpinner = findViewById<Spinner>(R.id.text_jasaPengiriman)
        if (jasaSpinner != null){
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jasa)
            jasaSpinner.adapter = adapter

        }
        input_detailPengiriman.setOnClickListener{
            saveData()
        }

    }
    private fun saveData(){
        var bayarInfo = Bundle()
        val jumlah = intent.getStringExtra("Jumlah")
        val nama = text_namaLengkap.text.toString()
        val alamat = text_Alamat.text.toString()
        val provinsi = text_Provinsi.text.toString()
        val kota = text_Kota.text.toString()
        val kecamatan = text_Kecamatan.text.toString()
        val kodepos = text_kodePos.text.toString()
        val nomorhp = text_nomorHp.text.toString()

        bayarInfo.putString("Jumlah",jumlah)
        bayarInfo.putString("Nama",nama)
        bayarInfo.putString("Alamat",alamat)
        bayarInfo.putString("Provinsi",provinsi)
        bayarInfo.putString("Kota",kota)
        bayarInfo.putString("Kodepos",kodepos)
        bayarInfo.putString("Nomorhp",nomorhp)
        bayarInfo.putString("Kecamatan",kecamatan)
        val intent = Intent(this, pengiriman_Pembayaran::class.java)
        intent.putExtras(bayarInfo)
        startActivity(intent)
    }
}
