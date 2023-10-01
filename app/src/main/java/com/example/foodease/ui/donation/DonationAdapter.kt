package com.example.foodease.ui.donation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodease.R
import com.example.foodease.ui.event.Event
import com.google.android.material.snackbar.Snackbar

class DonationAdapter(private val onItemClick: (Donation) -> Unit) :
    RecyclerView.Adapter<DonationAdapter.ViewHolder>(){

    //private val dataSet: List<Donation>
    var dataSet = emptyList<Donation>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val donationName : TextView = view.findViewById(R.id.textViewCardDonationName)
        //val donationInstruction : TextView = view.findViewById(R.id.textViewDataFoodInstruction)
        //val donationQuantity : TextView = view.findViewById(R.id.textViewDataFoodQuantity)
        //val donationExpiry: TextView = view.findViewById(R.id.textViewDataExpiryDate)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {

            }
        }
    }

    internal fun setDonation(donation: List<Donation>){
        dataSet = donation
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.donation_card, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val donation = dataSet[position]
        holder.donationName.text = donation.name
        //holder.donationInstruction.text = donation.instruction
        //holder.donationQuantity.text = donation.quantity.toString()
        //holder.donationExpiry.text = donation.expiryDate.toString()

        holder.itemView.setOnClickListener{
            onItemClick(donation)
        }
    }


}

