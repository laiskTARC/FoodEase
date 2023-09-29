package com.example.foodease.ui.volunteer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R
import com.example.foodease.databinding.FragmentVolunteerListBinding
import com.example.foodease.ui.event.EventClickListener

class VolunteerAdapter(private val volunteerClickListener: VolunteerClickListener) :
    RecyclerView.Adapter<VolunteerAdapter.ViewHolder>() {

    //private val dataSet: List<Volunteer>
    private var dataSet = emptyList<Volunteer>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewVolunteerName: TextView = view.findViewById(R.id.textViewVolunteerName)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {

            }
        }
    }

    internal fun setVolunteer(volunteer: List<Volunteer>){
        dataSet = volunteer
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
        holder.textViewVolunteerName.text = volunteer.name

        holder.itemView.setOnClickListener{
            val sharedPref = it.context.getSharedPreferences("volunteer_shared_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor?.putString("name", volunteer.name)
            editor?.apply()

            volunteerClickListener.onVolunteerClick(volunteer.id)
        }

    }
}