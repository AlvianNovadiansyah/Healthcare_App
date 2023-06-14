package com.example.healthcareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.healthcareapp.databinding.BookdetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BookdetailsActivity : AppCompatActivity() {
    private lateinit var binding: BookdetailsBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BookdetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValuestoviews()

        binding.buttonbook.setOnClickListener {
            openUpdateDialog()
        }

        binding.buttonback.setOnClickListener {
            val intent = Intent(this, OrderdetailActivity::class.java)
            startActivity(intent)
        }

        binding.buttondelete.setOnClickListener {
            deleterecord()
        }
    }

    private fun setValuestoviews(){

        val id = intent.getStringExtra("idbook")
        val name = intent.getStringExtra("namauser")
        val email = intent.getStringExtra("emailuser")
        val address = intent.getStringExtra("addressuser")
        val phone = intent.getStringExtra("phoneuser")
        val DoctorType = intent.getStringExtra("doctoruser")
        val time = intent.getStringExtra("datetimeuser")

        binding.appointmentname.setText(name)
        binding.appointmentemail.setText(email)
        binding.appointmentaddress.setText(address)
        binding.appointmentphone.setText(phone)
        binding.appointmentdoctor.setText(DoctorType)
        binding.appointmentdatetime.setText(time)
        binding.appointmentid.setText(id)
    }

    private fun deleterecord(){
        val id = intent.getStringExtra("idbook").toString()
        auth = FirebaseAuth.getInstance()
        val useruid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        dbRef = FirebaseDatabase.getInstance().getReference("User/$useruid/bookingdetails").child(id)

        val mtask = dbRef.removeValue()
        mtask.addOnSuccessListener {
            Toast.makeText(this, "Order Data Deleted", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, OrderdetailActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun openUpdateDialog(){

        val id = intent.getStringExtra("idbook")
        val name = intent.getStringExtra("namauser")
        val email = intent.getStringExtra("emailuser")
        val address = intent.getStringExtra("addressuser")
        val phone = intent.getStringExtra("phoneuser")
        val DoctorType = intent.getStringExtra("doctoruser")
        val time = intent.getStringExtra("datetimeuser")

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updatedialog, null)

        mDialog.setView(mDialogView)

        val etEmName = mDialogView.findViewById<EditText>(R.id.nameuser)
        val etEmMail = mDialogView.findViewById<EditText>(R.id.Emailuser)
        val etEmAddress = mDialogView.findViewById<EditText>(R.id.Address)
        val etEmPhone = mDialogView.findViewById<EditText>(R.id.Phone)
        val etEmDoctortype = mDialogView.findViewById<EditText>(R.id.DoctorType)
        val etEmDatetime = mDialogView.findViewById<EditText>(R.id.dDatetime)
        val etEmId = mDialogView.findViewById<EditText>(R.id.TextId)

        val btnupdate = mDialogView.findViewById<Button>(R.id.buttonupdate)
        val btnexit = mDialogView.findViewById<Button>(R.id.buttonexit)

        etEmName.setText(name)
        etEmMail.setText(email)
        etEmAddress.setText(address)
        etEmPhone.setText(phone)
        etEmDoctortype.setText(DoctorType)
        etEmDatetime.setText(time)
        etEmId.setText(id)

        mDialog.setTitle("Updating $name Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnupdate.setOnClickListener {
            updatedata(
                etEmId.text.toString(),
                etEmName.text.toString(),
                etEmMail.text.toString(),
                etEmAddress.text.toString(),
                etEmPhone.text.toString(),
                etEmDoctortype.text.toString(),
                etEmDatetime.text.toString()
            )
            Toast.makeText(applicationContext, "Order Data Updated", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        btnexit.setOnClickListener {
            alertDialog.dismiss()
        }

    }

    private fun updatedata(
        id: String,
        UserName: String,
        UserEmail: String,
        UserAddress: String,
        Phone: String,
        DoctorType: String,
        DateTime: String,
    ){
        auth = FirebaseAuth.getInstance()
        val useruid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        dbRef = FirebaseDatabase.getInstance().getReference("User/$useruid/bookingdetails").child(id)
        var fee : Float = 0.00F

        if (DoctorType != null)
            if (DoctorType.compareTo("Family Physicians")==0){
                fee = 150000.000f
            } else if (DoctorType.compareTo("Dieticians")==0){
                fee = 100000.000f
            } else if (DoctorType.compareTo("Dentists")==0){
                fee = 125000.000f
            }  else if (DoctorType.compareTo("Surgeons")==0){
                fee = 500000.000f
            } else {
                fee = 450000.000f
            }
        val orinfo = bookingmodel(UserName, UserEmail, UserAddress, Phone, DoctorType, DateTime, fee.toString(), id)
        dbRef.setValue(orinfo)
    }

}