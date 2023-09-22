package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class InventoryListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory_list, container, false)

        val buttonView = view.findViewById<Button>(R.id.inventoryView)

        buttonView.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryListFragment_to_inventoryListDetailFragment)
        }

        return view
    }


}