package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class DonationListUpdateFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_donation_list_update, container, false)

        val buttonBack = view.findViewById<Button>(R.id.buttonBack)

        val buttonUpdate2 = view.findViewById<Button>(R.id.buttonUpdate2)

        buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_donationListUpdateFragment_to_donationListDetailFragment)
        }

        buttonUpdate2.setOnClickListener {
            findNavController().navigate(R.id.action_donationListUpdateFragment_to_donationListDetailFragment)
        }

        return view
    }


}