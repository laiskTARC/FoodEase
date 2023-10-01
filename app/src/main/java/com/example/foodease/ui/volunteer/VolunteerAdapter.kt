    package com.example.foodease.ui.volunteer

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.core.content.ContextCompat
    import androidx.recyclerview.widget.RecyclerView
    import com.example.foodease.R
    import com.example.foodease.ui.event.Event

    class VolunteerAdapter(private val onItemClick: (Volunteer) -> Unit) :
        RecyclerView.Adapter<VolunteerAdapter.ViewHolder>() {

        //private val dataSet: List<Volunteer>
        private var dataSet = emptyList<Volunteer>()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewVolunteerName: TextView = view.findViewById(R.id.textViewVolunteerName)

            init {
                // Define click listener for the ViewHolder's View.
                view.setOnClickListener {

                }
            }
        }

        internal fun setVolunteer(volunteer: List<Volunteer>){
            dataSet = volunteer
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.volunteer_record, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val volunteer = dataSet[position]
            holder.textViewVolunteerName.text = volunteer.name

            holder.itemView.setOnClickListener{
                onItemClick(volunteer)
            }
        }
    }