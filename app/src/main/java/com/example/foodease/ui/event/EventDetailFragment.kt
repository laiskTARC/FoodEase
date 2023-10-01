package com.example.foodease.ui.event

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentEventDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File


class EventDetailFragment : Fragment() {

    val eventViewModel: EventViewModel by activityViewModels()
    private var _binding : FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)

        // Show the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Hide the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.GONE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventViewModel.events.observe(viewLifecycleOwner, { selectedItem ->
            id = selectedItem.id
            val name = selectedItem.name
            val description = selectedItem.description
            val start = selectedItem.startingDate
            val end = selectedItem.endingDate
            val address = selectedItem.venueAddress
            val volunteer = selectedItem.volunteerRequired

            binding.editTextEventName.setText(name)
            binding.textViewDataDescription.setText(description)
            binding.textViewDataVenueAddress.setText(address)
            binding.textViewDataStart.setText(start)
            binding.textViewDataEnd.setText(end)
            binding.textViewDataVolunteerRequired.setText(volunteer.toString())

            val eventHistory = Event(id,name,description,address,start,end,volunteer)
            eventViewModel.insert(eventHistory)
        })

        /*


         */

        /*
    private fun uploadPicture(){
        val filename = "profile.png"
        val file = File(this.context?.filesDir, filename)
        //Create an instance of Firebase Storage
        val firebaseStorage = Firebase.storage
        //Create a reference (a folder) to the storage
        val myRef = firebaseStorage.reference
            .child("profile")
            .child(profileViewModel.profile.value!!.phone)

        //Upload the file to firebase storage
        myRef.putFile(file.toUri())
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "File Uploaded",
                    Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { }
    }
*/
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_event_list, menu) // Replace with your menu resource
        menu.findItem(R.id.itemAddEvent).setVisible(false)
        menu.findItem(R.id.itemEditEventConfirm).setVisible(false)
        menu.findItem(R.id.itemDeleteEvent).setVisible(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.itemDeleteEvent ->{
                showDeleteConfirmationDialog()
                return true
            }

            R.id.itemEditEvent ->{
                updateEvent()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

        //Show the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.VISIBLE
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext()) // Use your activity or fragment context
        builder.setTitle("Confirm Delete")
        builder.setMessage("Are you sure you want to delete this item?")

        val id = eventViewModel.events.value?.id.toString()

        builder.setPositiveButton("Delete") { _, _ ->
            // Remote Database
            val firebaseDatabase = Firebase.database
            val ref = firebaseDatabase.getReference("events").child(id)

            ref.removeValue()
            Snackbar.make(binding.root, "Event Successfully Deleted", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.eventFragment)
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss() // Dismiss the dialog if the user cancels
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun updateEvent(){
        val firebaseDatabase = Firebase.database
        val ref = firebaseDatabase.getReference("events")

        val newName = binding.editTextEventName.text.toString()
        val newDescription = binding.textViewDataDescription.text.toString()
        val newAddress = binding.textViewDataVenueAddress.text.toString()
        val newStartingDate = binding.textViewDataStart.text.toString()
        val newEndingDate= binding.textViewDataEnd.text.toString()
        val newQuantity = binding.textViewDataVolunteerRequired.text.toString().toIntOrNull() ?: 0

        /*
                //Validate Event Name
                if (newName.isEmpty()){
                    Snackbar.make(binding.root, "Title cannot be empty", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                //Validate Description
                if(newDescription.isEmpty()){
                    Snackbar.make(binding.root, "Description cannot be empty", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if(newStartingDate.isEmpty()){
                    Snackbar.make(binding.root, "Starting Date cannot be empty", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if(newEndingDate.isEmpty()){
                    Snackbar.make(binding.root, "Ending Date cannot be empty", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if(newAddress.isEmpty()){
                    Snackbar.make(binding.root, "Venue address cannot be empty", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val newQuantity = binding.textViewDataVolunteerRequired.text.toString().trim()
                if (newQuantity.isEmpty()) {
                    Snackbar.make(binding.root, "Volunteer Required cannot be empty", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val editTextVolunteerRequired = newQuantity.toInt()
        */
        val event = mapOf(
            "name" to newName,
            "description" to newDescription,
            "venueAddress" to newAddress,
            "startingDate" to newStartingDate,
            "endingDate" to newEndingDate,
            "volunteerRequired" to newQuantity,
            )

        ref.child(id).updateChildren(event).addOnSuccessListener {
            Snackbar.make(binding.root, "Event Successfully Updated", Snackbar.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_eventDetailFragment_to_eventFragment)
        }.addOnFailureListener{
            Snackbar.make(binding.root, "Failed", Snackbar.LENGTH_SHORT).show()
        }

    }

}