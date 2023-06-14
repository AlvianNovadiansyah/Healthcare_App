package com.example.healthcareapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.healthcareapp.databinding.ConfirmbookBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConfirmbookActivity : AppCompatActivity() {
    private lateinit var binding: ConfirmbookBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val useruid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        dbRef = FirebaseDatabase.getInstance().getReference("User/$useruid/bookingdetails")
        val newChildRef = dbRef.push()
        val bookingId = newChildRef.key

        val name = intent.getStringExtra("Usernama")
        val email = intent.getStringExtra("Emailpasien")
        val address = intent.getStringExtra("Address")
        val phone = intent.getStringExtra("Phone")
        val doctor = intent.getStringExtra("Doctorvalue")
        val time = intent.getStringExtra("time")
        var fee: Float = 0F

        binding.appointmentname.setText(name)
        binding.appointmentemail.setText(email)
        binding.appointmentaddress.setText(address)
        binding.appointmentphone.setText(phone)
        binding.appointmentdoctor.setText(doctor)
        binding.appointmentdatetime.setText(time)

        if (doctor != null)
            if (doctor.compareTo("Family Physicians")==0){
                fee = 150000.000f
                binding.appointmentfee.setText("Rp $fee")
            } else if (doctor.compareTo("Dieticians")==0){
                fee = 100000.000f
                binding.appointmentfee.setText("Rp $fee")
            } else if (doctor.compareTo("Dentists")==0){
                fee = 125000.000f
                binding.appointmentfee.setText("Rp $fee")
            }  else if (doctor.compareTo("Surgeons")==0){
                fee = 500000.000f
                binding.appointmentfee.setText("Rp $fee")
            } else {
                fee = 450000.000f
                binding.appointmentfee.setText("Rp $fee")
            }

        binding.buttonbook.setOnClickListener {
            val modeldata = bookingmodel(name, email, address, phone, doctor, time, fee.toString(), bookingId)
            dbRef.child("$bookingId").setValue(modeldata)
                .addOnSuccessListener {
                    Toast.makeText(this, "User data saved successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainMenu::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save user data", Toast.LENGTH_SHORT).show()
                }
        }

        binding.buttonback.setOnClickListener {
            val intent = Intent(this, BookappointmentActivity::class.java)
            startActivity(intent)
        }


    }
}