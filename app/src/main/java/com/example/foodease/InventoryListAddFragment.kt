package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class InventoryListAddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory_list_add, container, false)

        val buttonBack2 = view.findViewById<Button>(R.id.buttonBack2)

        buttonBack2.setOnClickListener {
            findNavController().navigate(R.id.action_inventoryListAddFragment_to_inventoryListFragment)
        }

        return view
    }


}