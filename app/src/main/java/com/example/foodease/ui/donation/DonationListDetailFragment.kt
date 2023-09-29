package com.example.foodease.ui.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.foodease.R


class DonationListDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_donation_list_detail, container, false)

        val buttonAccept = view.findViewById<Button>(R.id.buttonAccept)

        val buttonReject = view.findViewById<Button>(R.id.buttonReject)

        val buttonUpdate = view.findViewById<Button>(R.id.buttonUpdate)

        buttonAccept.setOnClickListener {
            findNavController().navigate(R.id.action_donationListDetailFragment_to_donationListFragment)

        }

        buttonReject.setOnClickListener {
            findNavController().navigate(R.id.action_donationListDetailFragment_to_donationListFragment)

        }

        buttonUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_donationListDetailFragment_to_donationListUpdateFragment)

        }



        return view
    }


}