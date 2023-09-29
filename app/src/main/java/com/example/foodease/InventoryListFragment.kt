package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class InventoryListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_food_list, menu) // Replace with your menu resource
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Handle your menu items here
            R.id.itemFoodRequest-> {
                findNavController().navigate(R.id.requestListFragment)
                return true
            }
            R.id.itemDonation -> {
                findNavController().navigate(R.id.donationListFragment)
                return true
            }
            // Add more menu item cases as needed
            else -> super.onOptionsItemSelected(item)
        }
    }

}