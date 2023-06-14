package com.example.healthcareapp

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcareapp.databinding.OrderdetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderdetailActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var emplist: ArrayList<bookingmodel>
    private lateinit var dbref: DatabaseReference
    private lateinit var binding: OrderdetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OrderdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        emplist = arrayListOf<bookingmodel>()

        getlistdata()


        binding.buttonlistback.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }


    }

    private fun getlistdata(){

        auth = FirebaseAuth.getInstance()
        val useruid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        dbref = FirebaseDatabase.getInstance().getReference("User/$useruid/bookingdetails")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    emplist.clear()
                    for (empsnap in snapshot.children){
                        val listdata = empsnap.getValue(bookingmodel::class.java)
                        emplist.add(listdata!!)
                    }
                    val adapter = BookingaAdapter(emplist)
                    recyclerView.adapter = adapter

                    adapter.setOnClickListener(object : BookingaAdapter.onItemClickListener{
                        override fun onItemClick(Position: Int) {
                           val intent = Intent(this@OrderdetailActivity, BookdetailsActivity::class.java)

                            intent.putExtra("idbook", emplist[Position].BookingId)
                            intent.putExtra("namauser", emplist[Position].UserName)
                            intent.putExtra("emailuser", emplist[Position].UserEmail)
                            intent.putExtra("addressuser", emplist[Position].UserAddress)
                            intent.putExtra("doctoruser", emplist[Position].DoctorType)
                            intent.putExtra("datetimeuser", emplist[Position].DateTime)
                            intent.putExtra("phoneuser", emplist[Position].Phone)
                            startActivity(intent)

                        }

                    })


                }
            }

            override fun onCancelled(error: DatabaseError) {
                val errorMessage = "Database Error: ${error.message}"
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}