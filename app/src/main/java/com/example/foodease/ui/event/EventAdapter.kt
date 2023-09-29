package com.example.foodease.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R
import com.example.foodease.database.event.Event

class EventAdapter : RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    private var dataList = emptyList<Event>()


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.imageViewEvent)
        val eventName : TextView = view.findViewById(R.id.textViewInventoryName)


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
        holder.eventName.text = event.name
    }
}