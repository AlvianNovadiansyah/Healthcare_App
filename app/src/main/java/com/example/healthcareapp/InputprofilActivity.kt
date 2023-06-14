package com.example.healthcareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.healthcareapp.databinding.InputprofilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InputprofilActivity : AppCompatActivity() {
    private lateinit var binding: InputprofilBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InputprofilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("User")

        binding.buttonuser.setOnClickListener {
            val name = binding.usernameET.text.toString()
            val age = binding.ageET.text.toString()
            val selectedgender = when (binding.radioButtonGroup.checkedRadioButtonId){
                binding.radioButtonMale.id -> "Male"
                binding.radioButtonFemale.id -> "Female"
                binding.radioButtonOther.id -> "Other"
                else -> ""
            }
            if (name.isEmpty() || age.isEmpty() || selectedgender.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = usermodel(name, age.toInt(), selectedgender)
            val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
            dbRef.child(userId).setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "User data saved successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainMenu::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save user data", Toast.LENGTH_SHORT).show()
                }
        }
    }
}