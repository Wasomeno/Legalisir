package com.example.legalisir

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    lateinit  var loginEmail: EditText
    lateinit var loginPassword : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        val db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }
    fun signinexecution(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Selamat Datang",
                        Toast.LENGTH_SHORT).show()
                    updateUI(user)

                } else {
                    Toast.makeText(baseContext, "Password Atau Email Salah",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {

        if(user != null) {
            val intent = Intent(this, input_file::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun buttonSignIn(view: View) {
        var email :String = loginEmail.text.toString()
        var pass :String = loginPassword.text.toString()

        if(email.length > 6) {
            if(pass.length >= 6) {
                signinexecution(email,pass)
            }
        }
    }

    fun toRegister(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}
