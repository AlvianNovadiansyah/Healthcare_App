package com.example.healthcareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcareapp.databinding.FinddoctorBinding

class FinddoctorActivity : AppCompatActivity() {
    private lateinit var binding: FinddoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinddoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardback.setOnClickListener {
            intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

        binding.cardfamily.setOnClickListener {
            intent = Intent(this, DoctordetailActivity::class.java)
            intent.putExtra("title", "Family Physicians")
            startActivity(intent)
        }

        binding.carddietician.setOnClickListener {
            intent = Intent(this, DoctordetailActivity::class.java)
            intent.putExtra("title", "Dieticians")
            startActivity(intent)
        }

        binding.carddentist.setOnClickListener {
            intent = Intent(this, DoctordetailActivity::class.java)
            intent.putExtra("title", "Dentists")
            startActivity(intent)
        }

        binding.cardsurgeon.setOnClickListener {
            intent = Intent(this, DoctordetailActivity::class.java)
            intent.putExtra("title", "Surgeons")
            startActivity(intent)
        }

        binding.cardcardio.setOnClickListener {
            intent = Intent(this, DoctordetailActivity::class.java)
            intent.putExtra("title", "Cardiologists")
            startActivity(intent)
        }
    }
}