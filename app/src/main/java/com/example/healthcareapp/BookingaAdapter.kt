package com.example.healthcareapp

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookingaAdapter(private val emplist: ArrayList<bookingmodel>) :
    RecyclerView.Adapter<BookingaAdapter.viewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(Position: Int)
    }
    fun setOnClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingaAdapter.viewHolder {
        val  itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.booklingist, parent, false)
        return viewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentEmp = emplist[position]
        holder.Empname.text = currentEmp.UserName
        holder.empdate.text = currentEmp.DateTime
        holder.empid.text = currentEmp.BookingId
    }

    override fun getItemCount(): Int {
        return emplist.size
    }

    class viewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val Empname : TextView = itemView.findViewById(R.id.booklistname)
        val empdate : TextView = itemView.findViewById(R.id.booktanggal)
        val empid : TextView = itemView.findViewById(R.id.bookid)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}