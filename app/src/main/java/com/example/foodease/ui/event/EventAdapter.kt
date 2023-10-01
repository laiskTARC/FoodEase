package com.example.foodease.ui.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R

class EventAdapter (private val onItemClick: (Event) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    private var dataList = emptyList<Event>()


    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.imageViewEvent)
        val eventId : TextView = view.findViewById(R.id.textViewEventId)
        val eventName : TextView = view.findViewById(R.id.textViewEventName)
        val venue : TextView = view.findViewById(R.id.textViewVenue)
        val date : TextView = view.findViewById(R.id.textViewDate)


        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {

/*
                val sharedPref = it.context.getSharedPreferences("event_shared_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()

                val event = dataList[adapterPosition]
                editor?.putString("id", event.id)
                editor?.putString("name", event.name)
                editor?.putString("description", event.description)
                editor?.putString("address", event.venueAddress)
                editor?.putString("starting", event.startingDate)
                editor?.putString("ending", event.endingDate)
                editor?.putString("volunteerRequired", event.volunteerRequired.toString())
                it.findNavController().navigate(R.id.action_eventFragment_to_eventDetailFragment)
                editor?.apply()
                */
            }
        }
    }

    internal fun setEvent(event:List<Event>){
        dataList = event
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_card, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() : Int{
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = dataList[position]
        holder.eventId.text = event.id
        holder.eventName.text = event.name
        holder.venue.text = event.venueAddress
        holder.date.text = event.startingDate + " - " + event.endingDate

        holder.itemView.setOnClickListener{
            onItemClick(event)
        }
    }
}