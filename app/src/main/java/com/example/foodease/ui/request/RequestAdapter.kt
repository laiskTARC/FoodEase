package com.example.foodease.ui.request

import com.example.foodease.ui.event.Event
import com.example.foodease.ui.event.EventClickListener

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R

class RequestAdapter (private val eventClickListener: EventClickListener) : RecyclerView.Adapter<RequestAdapter.ViewHolder>(){

    private var dataList = emptyList<Event>()


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.imageViewEvent)
        val eventId : TextView = view.findViewById(R.id.textViewEventId)
        val eventName : TextView = view.findViewById(R.id.textViewEventName)


        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {
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


        val button = holder.itemView.findViewById<Button>(R.id.buttonViewEvent)

        button.setOnClickListener {

            val sharedPref = it.context.getSharedPreferences("event_shared_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor?.putString("id", event.id)
            editor?.putString("name", event.name)
            editor?.putString("description", event.description)
            editor?.putString("address", event.venueAddress)
            editor?.putString("starting", event.startingDate)
            editor?.putString("ending", event.endingDate)
            editor?.putString("volunteerRequired", event.volunteerRequired.toString())

            editor?.apply()

            eventClickListener.onEventClick(event.id)
        }
    }
}