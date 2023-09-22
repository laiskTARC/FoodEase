package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val donationButton = view.findViewById<Button>(R.id.donationButton)
        donationButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }

        val inventoryButton = view.findViewById<Button>(R.id.inventoryButton)
        inventoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }

        val requestButton = view.findViewById<Button>(R.id.requestButton)
        requestButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }

        val volunteerButton = view.findViewById<Button>(R.id.volunteerButton)
        volunteerButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donationListFragment)
        }

        return view
    }


}