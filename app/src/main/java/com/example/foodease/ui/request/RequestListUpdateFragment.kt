package com.example.foodease.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.foodease.R
import com.example.foodease.database.request.RequestDatabase
import com.example.foodease.database.request.RequestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [RequestListUpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestListUpdateFragment : Fragment() {
    private var id = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_list_update, container, false)

        val reqbtn = view.findViewById<Button>(R.id.Requestbutton)

        val requestDatabase = RequestDatabase.getDatabase(requireContext())
        val requestRepository = RequestRepository(requestDatabase.requestDao())

        reqbtn.setOnClickListener {
            val number = view.findViewById<EditText>(R.id.AddressEditText).text.toString()
            val address = view.findViewById<EditText>(R.id.numberText).text.toString()
            val people = view.findViewById<EditText>(R.id.AddressText).text.toString()
            val dietary = view.findViewById<EditText>(R.id.peopleText).text.toString()

            // Create a Request object with the fixed id of 1
            val updatedRequest = Request(id, number = number, address = address, people = people, dietary = dietary)

            // Update the Request object in the database using a coroutine
            lifecycleScope.launch(Dispatchers.IO) {
                requestRepository.update(updatedRequest)
            }

            // Display a message or perform any other actions as needed
            Toast.makeText(requireContext(), "Request updated", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
