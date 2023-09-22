package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class InventoryListDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory_list_detail, container, false)

        val buttonAccept = view.findViewById<Button>(R.id.buttonAccept)

        val buttonDeny = view.findViewById<Button>(R.id.buttonDeny)

        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)
        buttonAccept.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryListDetailFragment_to_inventoryListFragment)
        }

        buttonDeny.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryListDetailFragment_to_inventoryListFragment)
        }

        buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryListDetailFragment_to_inventoryListAddFragment)
        }

        return view
    }


}