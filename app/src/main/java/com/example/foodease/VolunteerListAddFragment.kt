package com.example.foodease

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.foodease.databinding.FragmentHomeBinding
import com.example.foodease.databinding.FragmentVolunteerListAddBinding

class VolunteerListAddFragment : Fragment() {

    private var _binding : FragmentVolunteerListAddBinding? = null
    private val binding get() = _binding!!
    private val URL: String ="https://chincheeonntesting.000webhostapp.com/volunteer/create.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVolunteerListAddBinding.inflate(inflater, container, false)

        val name = binding.name.text.toString().trim()





        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VolunteerListAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VolunteerListAddFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}