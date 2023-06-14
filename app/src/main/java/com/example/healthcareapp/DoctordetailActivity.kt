package com.example.healthcareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import com.example.healthcareapp.databinding.DoctordetailBinding

class DoctordetailActivity : AppCompatActivity() {
    private lateinit var binding: DoctordetailBinding
    private var doctorlist1: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Salam Prasa", "Hospital Address: Sentra Medika, Cibinong", "Exp: 5yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Bagas Prata", "Hospital Address: Hasanudin, Bogor", "Exp: 8yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Riski Slamet", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 10yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Agus Sasta", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 12yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Indri Ayu", "Hospital Address: UI, Depok", "Exp: 5yrs", "Mobile No.: 0812345678")
    )
    private var doctorlist2: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Abdul Salam", "Hospital Address: Sentra Medika, Cibinong", "Exp: 5yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Ayu Putri", "Hospital Address: Hasanudin, Bogor", "Exp: 8yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Ananda Riski", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 10yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Khoitul Imam", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 12yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Fiqih Sitompul", "Hospital Address: UI, Depok", "Exp: 5yrs", "Mobile No.: 0812345678")
    )
    private var doctorlist3: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Veisyana Sasyawati", "Hospital Address: Sentra Medika, Cibinong", "Exp: 5yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Hilmi Soleh", "Hospital Address: Hasanudin, Bogor", "Exp: 8yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Riski Rafi", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 10yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Adelia Putri", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 12yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Putri Manganang", "Hospital Address: UI, Depok", "Exp: 5yrs", "Mobile No.: 0812345678")
    )
    private var doctorlist4: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: Alvano Pratama", "Hospital Address: Sentra Medika, Cibinong", "Exp: 5yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Salsa Numi", "Hospital Address: Hasanudin, Bogor", "Exp: 8yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Bagus Santri", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 10yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Agus Selamet", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 12yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Indri Putri", "Hospital Address: UI, Depok", "Exp: 5yrs", "Mobile No.: 0812345678")
    )
    private var doctorlist5: Array<Array<String>> = arrayOf(
        arrayOf("Doctor Name: I Kadek", "Hospital Address: Sentra Medika, Cibinong", "Exp: 5yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Aufa Sitompul", "Hospital Address: Hasanudin, Bogor", "Exp: 8yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Alfat Soleh", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 10yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Indra Bagas", "Hospital Address: Gatot Subroto, Jakarta", "Exp: 12yrs", "Mobile No.: 0812345678"),
        arrayOf("Doctor Name: Putri Rahayu", "Hospital Address: UI, Depok", "Exp: 5yrs", "Mobile No.: 0812345678")
    )

    private var doctorDetails: Array<Array<String>> = arrayOf()
    private var list: ArrayList<HashMap<String, String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DoctordetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        binding.viewtitle.setText(title)

        if (title != null) {
            if(title.compareTo("Family Physicians")==0)
                doctorDetails = doctorlist1
            else if (title.compareTo("Dieticians")==0)
                doctorDetails = doctorlist2
            else if (title.compareTo("Dentists")==0)
                doctorDetails = doctorlist3
            else if (title.compareTo("Surgeons")==0)
                doctorDetails = doctorlist4
            else
                doctorDetails = doctorlist5

            binding.button2.setOnClickListener {
                    intent = Intent(this, FinddoctorActivity::class.java)
                    startActivity(intent)
                }
        }

        this.doctorDetails.indices.forEach { i ->
            val map: HashMap<String, String> = HashMap()
            map["Line1"] = doctorDetails[i][0]
            map["Line2"] = doctorDetails[i][1]
            map["Line3"] = doctorDetails[i][2]
            map["Line4"] = doctorDetails[i][3]
            list.add(map)
        }
        val adapter = SimpleAdapter(applicationContext, list, R.layout.multilines, arrayOf("Line1", "Line2", "Line3", "Line4"),
        intArrayOf(R.id.linea, R.id.lineb, R.id.linec, R.id.lined)
        )

        binding.listdoctor.adapter = adapter
        
        binding.listdoctor.setOnItemClickListener { adapterView, view, i, l ->
            intent = Intent(applicationContext, BookappointmentActivity::class.java)
            this.intent.putExtra("text1", title)
            startActivity(intent)
        }
    }

}