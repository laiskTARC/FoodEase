package com.example.foodease

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.foodease.databinding.FragmentHomeBinding
import com.example.foodease.databinding.FragmentVolunteerListAddBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class VolunteerListAddFragment : Fragment() {

    private var _binding : FragmentVolunteerListAddBinding? = null
    private val binding get() = _binding!!
    //private val URL: String ="https://chincheeonntesting.000webhostapp.com/volunteer/create.php"

    val volunteerViewModel: VolunteerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVolunteerListAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveData()
    }

    private fun saveData(){
        val firebaseDatabase = Firebase.database
        val myRef = firebaseDatabase.getReference("volunteers")
        with(myRef.child(volunteerViewModel.volunteer.value!!.contact)){
            child("name").setValue(volunteerViewModel.volunteer.value!!.name)
            child("email").setValue(volunteerViewModel.volunteer.value!!.email)
            child("phone").setValue(volunteerViewModel.volunteer.value!!.contact)
            child("address").setValue(volunteerViewModel.volunteer.value!!.address)
        }
    }


}