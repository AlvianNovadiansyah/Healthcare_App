package com.example.healthcareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcareapp.databinding.ActivityMainBinding
import com.example.healthcareapp.databinding.InputprofilBinding
import com.google.firebase.auth.FirebaseAuth

class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.Logout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginPageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.doctor.setOnClickListener {
            val intent = Intent(this, FinddoctorActivity::class.java)
            startActivity(intent)
        }

        binding.cardbook.setOnClickListener {
            val intent = Intent(this, BookappointmentActivity::class.java)
            startActivity(intent)
        }

        binding.orderdetail.setOnClickListener {
            val intent = Intent(this, OrderdetailActivity::class.java)
            startActivity(intent)
        }

    }
}