package com.example.foodease.ui.event

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentEventCreateBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
//import com.bumptech.glide.Glide

class EventCreateFragment : Fragment() {

    private var _binding : FragmentEventCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var storageRef : StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the back button
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Hide the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.GONE

        _binding = FragmentEventCreateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCreate = binding.buttonCreateEvent
        val buttonSelectImage = binding.buttonSelectImage

        buttonSelectImage.setOnClickListener {
            // PICK INTENT picks item from data
            // and returned selected item
            val galleryIntent = Intent(Intent.ACTION_PICK)
            // here item is type of image
            galleryIntent.type = "image/*"
            // ActivityResultLauncher callback
            imagePickerActivityResult.launch(galleryIntent)
        }

        buttonCreate.setOnClickListener{
            val eventName = binding.editTextEventName.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()
            val startDate = binding.editTextStartingDate.text.toString().trim()
            val endDate = binding.editTextStartingDate.text.toString().trim()
            val venue = binding.editTextVenueAddress.text.toString().trim()


            //Validate Event Name
            if (eventName.isEmpty()){
                Snackbar.make(view, "Title cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Validate Description
            if(description.isEmpty()){
                Snackbar.make(view, "Description cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(startDate.isEmpty()){
                Snackbar.make(view, "Starting Date cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(endDate.isEmpty()){
                Snackbar.make(view, "Ending Date cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(venue.isEmpty()){
                Snackbar.make(view, "Venue address cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editTextVolunteerRequiredText = binding.editTextVolunteerRequired.text.toString().trim()

            if (editTextVolunteerRequiredText.isEmpty()) {
                Snackbar.make(view, "Volunteer Required cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editTextVolunteerRequired = editTextVolunteerRequiredText.toInt()

            // Database
            val database = Firebase.database
            val ref = database.getReference("events") //Database Name
            val newChildRef = ref.push()
            val id = newChildRef.key?: ""

            val event = Event(id,eventName,description, venue, startDate, endDate, editTextVolunteerRequired)

            newChildRef.setValue(event).addOnSuccessListener {
                Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(R.id.eventFragment)
            }.addOnFailureListener{
                Snackbar.make(view, "Failed", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        //Show the bottom navigation view
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.VISIBLE
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private var imagePickerActivityResult: ActivityResultLauncher<Intent> =
    // lambda expression to receive a result back, here we
        // receive single item(photo) on selection
        registerForActivityResult( ActivityResultContracts.StartActivityForResult()) { result ->
            if (result != null) {
                // getting URI of selected Image
                val imageUri: Uri? = result.data?.data

                // val fileName = imageUri?.pathSegments?.last()

                // extract the file name with extension
                val sd = getFileName(requireContext(), imageUri!!)

                // Upload Task with upload to directory 'file'
                // and name of the file remains same
                val uploadTask = storageRef.child("file/$sd").putFile(imageUri)

                // On success, download the file URL and display it
                uploadTask.addOnSuccessListener {
                    // using glide library to display the image
                    storageRef.child("upload/$sd").downloadUrl.addOnSuccessListener {
                        /*
                        Glide.with(this)
                            .load(it)
                            .into(imageview)*/

                        Log.e("Firebase", "download passed")
                    }.addOnFailureListener {
                        Log.e("Firebase", "Failed in downloading")
                    }
                }.addOnFailureListener {
                    Log.e("Firebase", "Image Upload fail")
                }
            }
        }

    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null && cursor.moveToFirst()) {
                    val displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (displayNameIndex != -1) {
                        return cursor.getString(displayNameIndex)
                    }
                }
            }
        }
        return uri.path?.substringAfterLast('/') // Fallback if DISPLAY_NAME is not available
    }
}