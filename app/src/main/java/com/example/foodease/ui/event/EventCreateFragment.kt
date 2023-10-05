package com.example.foodease.ui.event

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodease.R
import com.example.foodease.databinding.FragmentEventCreateBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class EventCreateFragment : Fragment() {

    private var _binding: FragmentEventCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var storageRef: StorageReference
    private var imageUri: Uri? = null // Declare imageUri here

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
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.GONE

        _binding = FragmentEventCreateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCreate = binding.buttonCreateEvent
        val buttonSelectImage = binding.buttonSelectImage
        val database = Firebase.database
        val ref = database.getReference("events") // Database Name
        storageRef = FirebaseStorage.getInstance().getReference("img")

        var imagePickerActivityResult =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                binding.imageView2.setImageURI(uri)
                if (uri != null) {
                    // Getting URI of the selected image
                    imageUri = uri
                }
            }

        buttonSelectImage.setOnClickListener {
            imagePickerActivityResult.launch("image/*") // Launch the gallery intent
        }


        buttonCreate.setOnClickListener {
            val eventName = binding.editTextEventName.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()
            val startDate = binding.editTextStartingDate.text.toString().trim()
            val endDate = binding.editTextStartingDate.text.toString().trim()
            val venue = binding.editTextVenueAddress.text.toString().trim()

            // Validate Event Name
            if (eventName.isEmpty()) {
                Snackbar.make(view, "Title cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate Description
            if (description.isEmpty()) {
                Snackbar.make(view, "Description cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (startDate.isEmpty()) {
                Snackbar.make(view, "Starting Date cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (endDate.isEmpty()) {
                Snackbar.make(view, "Ending Date cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (venue.isEmpty()) {
                Snackbar.make(view, "Venue address cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editTextVolunteerRequiredText =
                binding.editTextVolunteerRequired.text.toString().trim()

            if (editTextVolunteerRequiredText.isEmpty()) {
                Snackbar.make(view, "Volunteer Required cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editTextVolunteerRequired = editTextVolunteerRequiredText.toInt()

            // Database
            val newChildRef = ref.push()
            val id = newChildRef.key ?: ""

            val originalFilename = getFileName(requireContext(), imageUri!!)
            val timestamp = System.currentTimeMillis()
            val uniqueFilename = "${timestamp}_$originalFilename"

            val uploadTask = storageRef.child("events/$uniqueFilename").putFile(imageUri!!)
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setTitle("Please Wait")
            progressDialog.setMessage("Loading ...")
            progressDialog.show()
            uploadTask.addOnSuccessListener { uploadResult ->
                // Get the download URL for the uploaded image
                storageRef.child("events/$uniqueFilename").downloadUrl.addOnSuccessListener { downloadUri ->
                    // Create the event object with the image URL
                    val imageUrl = downloadUri.toString()
                    val event = Event(
                        id,
                        eventName,
                        description,
                        venue,
                        startDate,
                        endDate,
                        editTextVolunteerRequired,
                        imageUrl // Set the imageUrl property
                    )

                    // Push the event object to the Firebase Realtime Database
                    newChildRef.setValue(event).addOnSuccessListener {
                        Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                        findNavController().navigate(R.id.eventFragment)
                    }.addOnFailureListener {
                        Snackbar.make(view, "Failed", Snackbar.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Log.e("Firebase", "Failed to get image download URL")
                }
            }.addOnFailureListener {
                Log.e("Firebase", "Image Upload fail")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Show the bottom navigation view
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavAdmin)
        bottomNavigationView?.visibility = View.VISIBLE
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
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
