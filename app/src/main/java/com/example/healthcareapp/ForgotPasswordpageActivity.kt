package com.example.healthcareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordpageActivity : AppCompatActivity() {
    private lateinit var repassword: EditText
    private lateinit var rebutton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_passwordpage)

        repassword = findViewById(R.id.editTextTextEmailAddress2)
        rebutton = findViewById(R.id.button3)

        auth = FirebaseAuth.getInstance()

        rebutton.setOnClickListener{
            val spassword = repassword.text.toString()
            auth.sendPasswordResetEmail(spassword)
                .addOnCompleteListener{
                    Toast.makeText(this, "Please Check your Email", Toast.LENGTH_LONG).
                    show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                }
        }

    }
}