package com.example.foodease.ui.request

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RequestListFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private val itemId = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_list, container, false)

        val assistanceDetail = view.findViewById<Button>(R.id.assistanceDetail)

        val addrequestButton = view.findViewById<Button>(R.id.addrequestbtn)

        val removeBtn = view.findViewById<Button>(R.id.removebtn)


        assistanceDetail.setOnClickListener {
            findNavController().navigate(R.id.action_requestListFragment_to_requestListDetailFragment)
        }

        addrequestButton.setOnClickListener {
            findNavController().navigate(R.id.action_requestListFragment_to_requestListAddFragment)
        }

        removeBtn.setOnClickListener {
            val databaseReference = FirebaseDatabase.getInstance().getReference("requests")

            // Use the reference to the specific request by its ID and remove it
            databaseReference.child(itemId).removeValue()
                .addOnSuccessListener {
                    Log.d("Firebase", "Item removed successfully")
                    Toast.makeText(requireContext(), "Item removed", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Error removing item: ${e.message}")
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
        return view
    }
}