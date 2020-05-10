package com.example.legalisir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail__pengiriman.*
import kotlinx.android.synthetic.main.activity_input_legalisir.*
import kotlinx.android.synthetic.main.activity_pengiriman__pembayaran.*

class pengiriman_Pembayaran : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengiriman__pembayaran)
        ref = FirebaseDatabase.getInstance().getReference("Jasa Kurir")
        button_kirimBayar.setOnClickListener {
            next()
        }
    }
    private fun next () {
        val radio: RadioButton = findViewById(radio_kirimBayar.checkedRadioButtonId)
        val pilihBayar = radio.text.toString()
        val passedInfo = intent.extras
        val biayaInfo = Bundle()
        val biaya = "Rp.8000,00"
        val jumlah = passedInfo?.getString("Jumlah").toString()
        val nama = passedInfo?.getString("Nama").toString()
        val alamat = passedInfo?.getString("Alamat").toString()
        val provinsi = passedInfo?.getString("Provinsi").toString()
        val kota = passedInfo?.getString("Kota").toString()
        val kecamatan = passedInfo?.getString("Kecamatan").toString()
        val kodepos = passedInfo?.getString("Kodepos").toString()
        val nomorhp = passedInfo?.getString("Nomorhp").toString()

        val detail = detail_Kirim(nama,"test",jumlah,alamat,provinsi,kota,kecamatan,kodepos,nomorhp)
        ref.child(nama).setValue(detail).addOnCompleteListener {
            Toast.makeText(this, "Data Berhasil Di Input", Toast.LENGTH_SHORT).show()
            biayaInfo.putString("Bank",pilihBayar)
            biayaInfo.putString("Biaya",biaya)
            val intent = Intent(this, pembayaran_Tujuan::class.java)
            intent.putExtras(biayaInfo)
            startActivity(intent)
        }
    }
}
