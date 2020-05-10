package com.example.legalisir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail__pengambilan.*
import kotlinx.android.synthetic.main.activity_pengiriman__pembayaran.*

class detail_Pengambilan : AppCompatActivity() {
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail__pengambilan)
        ref = FirebaseDatabase.getInstance().getReference("Pengambilan")
        button_ambilBayar.setOnClickListener {
            next()
        }
    }
    private fun next() {
        val bayarInfo = Bundle()
        val biaya = "Rp. 3000,00"
        val jumlah = intent.getStringExtra("Jumlah")
        val nama = text_namaLengkap.text.toString()
        val radio: RadioButton = findViewById(radio_ambilBayar.checkedRadioButtonId)
        val pilihBayar = radio.text.toString()
        val detail = detail_Ambil(nama,"test",jumlah)
        ref.child(nama).setValue(detail).addOnCompleteListener {
            Toast.makeText(this, "Data Berhasil Di Input", Toast.LENGTH_SHORT).show()
            bayarInfo.putString("Bank",pilihBayar)
            bayarInfo.putString("Biaya",biaya)
            val intent = Intent(this, pembayaran_Tujuan::class.java)
            intent.putExtras(bayarInfo)
            startActivity(intent)
        }
    }
}
