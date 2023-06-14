package com.example.healthcareapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.healthcareapp.databinding.BookappointmentBinding
import java.util.Calendar

class BookappointmentActivity : AppCompatActivity() {
    private  lateinit var binding: BookappointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BookappointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = arrayOf("Family Physicians", "Dieticians", "Dentists", "Surgeons", "Cardiologists")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        val title = intent.getStringExtra("text1")
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            binding.buttondate.text = selectedDate
        }, year, month, day)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.buttontime.text = selectedTime
        }, hour, minute, true)



        if (title != null)
            if (title.compareTo("Family Physicians")==0){
                val position = options.indexOf("Family Physicians")
                binding.spinner.setSelection(position)
            } else if (title.compareTo("Dieticians")==0){
                val position = options.indexOf("Dieticians")
                binding.spinner.setSelection(position)
            } else if (title.compareTo("Dentists")==0){
                val position = options.indexOf("Dentists")
                binding.spinner.setSelection(position)
            }  else if (title.compareTo("Surgeons")==0){
                val position = options.indexOf("Surgeons")
                binding.spinner.setSelection(position)
            } else {
                val position = options.indexOf("Cardiologists")
                binding.spinner.setSelection(position)
            }

        binding.buttonback.setOnClickListener {
            intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

        binding.buttondate.setOnClickListener {
            datePickerDialog.show()
        }

        binding.buttontime.setOnClickListener {
            timePickerDialog.show()
        }

        binding.buttonbook.setOnClickListener {
            val name = binding.appointmentname.text.toString()
            val email = binding.appointmentemail.text.toString()
            val address = binding.appointmentaddress.text.toString()
            val phone = binding.appointmentphone.text.toString()
            val selectedvalue = binding.spinner.selectedItem as String
            val datebook = binding.buttondate.text.toString()
            val timebook = binding.buttontime.text.toString()

            if (name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || selectedvalue.isEmpty()
                || datebook.isEmpty() || timebook.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            intent = Intent(this, ConfirmbookActivity::class.java)
            intent.putExtra("Usernama", name)
            intent.putExtra("Emailpasien", email)
            intent.putExtra("Address", address)
            intent.putExtra("Phone", phone)
            intent.putExtra("Doctorvalue", selectedvalue)
            intent.putExtra("time", "$datebook $timebook")
            startActivity(intent)
        }
    }

}
