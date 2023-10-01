package com.example.foodease.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.room.Database
import com.example.foodease.R
import com.google.firebase.database.DatabaseReference

/**
 * A simple [Fragment] subclass.
 * Use the [RequestListAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestListAddFragment : Fragment() {
    private lateinit var key: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //emailEditText, numberEditText, AddressEditText7, peopleEditText, dietaryEditText9


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_list_add, container, false)
        val email = view.findViewById<EditText>(R.id.emailEditText)
        val number = view.findViewById<EditText>(R.id.numberEditText)
        val address = view.findViewById<EditText>(R.id.AddressEditText7)
        val people = view.findViewById<EditText>(R.id.peopleEditText)
        val dietary = view.findViewById<EditText>(R.id.dietaryEditText9)
        val myButton = view.findViewById<Button>(R.id.Requestbutton)

        val email2 = email.text.toString()
        val number2 = number.text.toString()
        val address2 = address.text.toString()
        val people2 = people.text.toString()
        val dietary2 = dietary.text.toString()

        fun adddb(){
            //connect
            val add = key.push().key!!
            val request = Request(add, email2, number2, address2, people2, dietary2)
        }

        myButton.setOnClickListener{
            adddb()
        }



        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RequestListAddFragment().apply {

            }
    }
}