package com.example.healthcareapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcareapp.databinding.LoginpageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding: LoginpageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private  lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginpageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.textView2.setOnClickListener{
            val intent = Intent(this, ForgotPasswordpageActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString() // Get the current user's UID
                        val userref = dbRef.child(userId)

                        userref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // User data exists, perform your desired actions
                                    val existingUser = dataSnapshot.getValue(usermodel::class.java)
                                    // Do something with the existing user data
                                    Toast.makeText(applicationContext, "Welcome and Hello " +
                                            existingUser?.name, Toast.LENGTH_SHORT).show()

                                    val intent = Intent(applicationContext, MainMenu::class.java)
                                    startActivity(intent)
                                } else {
                                    // User data doesn't exist, prompt for name input
                                    val intent = Intent(applicationContext, InputprofilActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                                val errorMessage = "Database Error: ${error.message}"
                                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        })


                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }
}