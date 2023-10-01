package com.example.foodease.ui.inventory

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.google.firebase.database.FirebaseDatabase


class InventoryListDetailFragment : Fragment() {
    private lateinit var inventoryId: TextView
    private lateinit var inventoryName: TextView
    private lateinit var inventoryDesc: TextView
    private lateinit var inventoryQuantity: TextView
    private lateinit var inventoryDate: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnBack: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory_list_detail, container, false)

        inventoryId = view.findViewById(R.id.textViewInventoryId2)
        inventoryName = view.findViewById(R.id.textViewInventoryName2)
        inventoryDesc = view.findViewById(R.id.textViewInventoryDesc2)
        inventoryQuantity = view.findViewById(R.id.textViewInventoryQuantity2)
        inventoryDate = view.findViewById(R.id.textViewInventoryDate2)

        btnUpdate = view.findViewById(R.id.btnUpdate)
        btnDelete = view.findViewById(R.id.btnDelete)
        btnBack = view.findViewById(R.id.btnBack)

        val args = arguments
        if (args != null) {
            val inventoryId = args.getString("InventoryId")
            val inventoryName = args.getString("InventoryName")
            val inventoryDesc = args.getString("InventoryDesc")
            val inventoryQuantity = args.getString("InventoryQuantity")
            val inventoryDate = args.getString("InventoryDate")

            // Find TextViews in fragment layout and set the data
            val idTextView = view.findViewById<TextView>(R.id.textViewInventoryId2)
            val nameTextView = view.findViewById<TextView>(R.id.textViewInventoryName2)
            val descTextView = view.findViewById<TextView>(R.id.textViewInventoryDesc2)
            val quantityTextView = view.findViewById<TextView>(R.id.textViewInventoryQuantity2)
            val dateTextView = view.findViewById<TextView>(R.id.textViewInventoryDate2)

            idTextView.text = "$inventoryId"
            nameTextView.text = "$inventoryName"
            descTextView.text = "$inventoryDesc"
            quantityTextView.text = "$inventoryQuantity"
            dateTextView.text = "$inventoryDate"
        }

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnUpdate.setOnClickListener {
            val inventoryIdValue = inventoryId.text.toString()
            val inventoryNameValue = inventoryName.text.toString()
            val inventoryDescValue = inventoryDesc.text.toString()
            val inventoryQuantityValue = inventoryQuantity.text.toString()
            val inventoryDateValue = inventoryDate.text.toString()
            val argsToUpdate = Bundle()
            Log.d(ContentValues.TAG, "Inventory ID: $inventoryIdValue")
            argsToUpdate.putString("InventoryId", inventoryIdValue)
            argsToUpdate.putString("InventoryName", inventoryNameValue)
            argsToUpdate.putString("InventoryDesc", inventoryDescValue)
            argsToUpdate.putString("InventoryQuantity", inventoryQuantityValue)
            argsToUpdate.putString("InventoryDate", inventoryDateValue)
            findNavController().navigate(R.id.action_inventoryListDetailFragment_to_inventoryListUpdateFragment, argsToUpdate)
        }

        btnDelete.setOnClickListener {
            val inventoryIdValue = inventoryId.text.toString()
            val databaseReference = FirebaseDatabase.getInstance().getReference("inventory")

            // Remove the inventory item with the given ID from Firebase
            databaseReference.child(inventoryIdValue).removeValue().addOnSuccessListener {
                // Deletion successful
                findNavController().navigateUp()
                Toast.makeText(requireContext(), "The inventory deleted", Toast.LENGTH_LONG).show()
                Log.d(ContentValues.TAG, "Inventory deleted successfully")
            }.addOnFailureListener {
                // Handle deletion failure
                Log.e(ContentValues.TAG, "Failed to delete inventory: ${it.message}")
            }
        }

        return view
    }

}