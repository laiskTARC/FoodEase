package com.example.foodease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodease.databinding.FragmentEventListBinding
import com.example.foodease.entities.Event

private var _binding : FragmentEventListBinding? = null
private val binding get() = _binding!!
class EventList : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)






//        val recyclerViewEvent = binding.recyclerViewEvent
//        recyclerViewEvent.layoutManager = LinearLayoutManager(requireContext())

        val data = ArrayList<Event>()

        for (i in 1..5) {
            data.add(Event(1,"Cedar Breaks Campground Host","1","1","1",1,"1"))
        }

        val adapter = EventAdapter()
        //val textViewE = binding.textView4
        adapter.setEvent(data)

        //recyclerViewEvent.adapter = adapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventList().apply {
                arguments = Bundle().apply {
                }
            }
    }
}