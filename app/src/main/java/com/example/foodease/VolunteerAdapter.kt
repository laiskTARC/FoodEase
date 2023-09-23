package com.example.foodease

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.database.Volunteer

class VolunteerAdapter() :
    RecyclerView.Adapter<VolunteerAdapter.ViewHolder>() {

    //private val dataSet: List<Contact>
    private var dataSet = emptyList<Volunteer>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.name)
        val textViewEmail: TextView = view.findViewById(R.id.email)
        val textViewPhone: TextView = view.findViewById(R.id.phone)
        val textViewAddress: TextView = view.findViewById(R.id.address)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {
            }
        }
    }

    internal fun setContact(contact: List<Volunteer>){
        dataSet = contact
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.volunteer_record, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val volunteer = dataSet[position]
        holder.textViewName.text = volunteer.name
        holder.textViewEmail.text = volunteer.email
        holder.textViewPhone.text = volunteer.contact
        holder.textViewAddress.text = volunteer.address
    }
}