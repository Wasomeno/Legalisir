package com.example.legalisir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    lateinit  var registerEmail: EditText
    lateinit var registerPassword : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        registerEmail = findViewById(R.id.registerEmail)
        registerPassword = findViewById(R.id.registerPassword)
    }
    private fun updateUI(user: FirebaseUser?) {

        if(user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
    fun signupexecution(email: String,password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = auth.getCurrentUser()
                        Toast.makeText(baseContext, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                        updateUI(user)
                    } else {
                        Toast.makeText(baseContext, "Registrasi Gagal",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                })
    }
    fun buttonSignUp(view: View) {
        var email :String = registerEmail.text.toString()
        var pass :String = registerPassword.text.toString()

        if(email.length > 6) {
            if(pass.length >= 6) {
                signupexecution(email,pass)
            }
        }
    }
}
