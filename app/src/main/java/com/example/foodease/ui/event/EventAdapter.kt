package com.example.foodease.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodease.R

class EventAdapter (private val onItemClick: (Event) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    private var dataList = emptyList<Event>()


    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.imageViewEvent)
        val eventId : TextView = view.findViewById(R.id.textViewEventId)
        val eventName : TextView = view.findViewById(R.id.textViewEventName)
        val venue : TextView = view.findViewById(R.id.textViewVenue)
        val date : TextView = view.findViewById(R.id.textViewProfileContact)


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
        if (!event.imageUrl.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(event.imageUrl) // Load the image from the event's imageUrl
                .error(R.drawable.no_img) // Error image if the load fails (replace with your resource)
                .into(holder.image) // Set the loaded image to your ImageView
        } else {
            // If no image URL is provided, load the default image
            holder.image.setImageResource(R.drawable.no_img) // Replace with your default image resource
        }
        holder.eventId.text = event.id
        holder.eventName.text = event.name
        holder.venue.text = event.venueAddress
        holder.date.text = event.startingDate + " - " + event.endingDate

        holder.itemView.setOnClickListener{
            onItemClick(event)
        }
    }
}