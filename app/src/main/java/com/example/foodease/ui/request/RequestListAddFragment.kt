package com.example.foodease.ui.request

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Database
import com.example.foodease.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.FirebaseApp

/**
 * A simple [Fragment] subclass.
 * Use the [RequestListAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestListAddFragment : Fragment() {
    private lateinit var keyid: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_request_list_add, container, false)

        keyid = FirebaseDatabase.getInstance().getReference("requests")

        val myButton = view.findViewById<Button>(R.id.Requestbutton)

        myButton.setOnClickListener {
            // Get user input from EditText fields
            val email = view.findViewById<EditText>(R.id.emailEditText).text.toString()
            val number = view.findViewById<EditText>(R.id.numberEditText).text.toString()
            val address = view.findViewById<EditText>(R.id.AddressEditText7).text.toString()
            val people = view.findViewById<EditText>(R.id.peopleEditText).text.toString()
            val dietary = view.findViewById<EditText>(R.id.dietaryEditText9).text.toString()

            // Check if any of the fields is empty
            if (email.isEmpty() || number.isEmpty() || address.isEmpty() || people.isEmpty() || dietary.isEmpty()) {
                Log.d("Firebase", "One or more fields are empty")
                return@setOnClickListener
            }

            Log.d("Firebase", "$email, $number, $address, $people, $dietary")

            Log.d("Firebase", "Attempting to add request to Firebase")
            // Push the new request to the Firebase Realtime Database
            val id = keyid.push().key!!
            val request = Request(id, email, number, address, people, dietary)
            keyid.child(id).setValue(request).addOnSuccessListener {
                Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show()
                Log.d("Firebase", "Request added successfully")

                // You can navigate to another fragment here if needed
            }.addOnFailureListener {
                Snackbar.make(view, "Failed", Snackbar.LENGTH_SHORT).show()
                Log.e("Firebase", "Error adding request: ${it.message}")
            }
        }

        return view
    }

    // Rest of your code...
}
