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
import com.google.android.material.snackbar.Snackbar

class DonationAdapter() :
    RecyclerView.Adapter<DonationAdapter.ViewHolder>(){

    var onItemClick: ((Donation) -> Unit)? = null
    //private val dataSet: List<Donation>
    var dataSet = emptyList<Donation>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val donationName : TextView = view.findViewById(R.id.textViewCardDonationName)

        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {
                val sharedPref = it.context.getSharedPreferences("donation_shared_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()

                val donation = dataSet[adapterPosition]
                editor.putString("id", donation.id.toString())
                editor.putString("name", donation.name.toString())
                editor?.apply()
                it.findNavController().navigate(R.id.donationListDetailFragment)
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
    }
}

