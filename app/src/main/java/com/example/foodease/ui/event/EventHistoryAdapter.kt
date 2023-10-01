package com.example.foodease.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R
import com.google.android.material.snackbar.Snackbar

class EventHistoryAdapter() :
    RecyclerView.Adapter<EventHistoryAdapter.ViewHolder>(){

    var onItemClick: ((Event) -> Unit)? = null
    //private val dataSet: List<EventHistory>
    var dataSet = emptyList<Event>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val eventHistoryName : TextView = view.findViewById(R.id.textViewEventHistoryName)
        //val eventHistoryInstruction : TextView = view.findViewById(R.id.textViewDataFoodInstruction)
        //val eventHistoryQuantity : TextView = view.findViewById(R.id.textViewDataFoodQuantity)
        //val eventHistoryExpiry: TextView = view.findViewById(R.id.textViewDataExpiryDate)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {

            }
        }
    }

    internal fun setEventHistory(eventHistory: List<Event>){
        dataSet = eventHistory
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_record, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventHistory = dataSet[position]
        holder.eventHistoryName.text = eventHistory.name
        //holder.eventHistoryInstruction.text = eventHistory.instruction
        //holder.eventHistoryQuantity.text = eventHistory.quantity.toString()
        //holder.eventHistoryExpiry.text = eventHistory.expiryDate.toString()
    }
}

