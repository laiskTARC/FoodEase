package com.example.foodease.ui.volunteer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodease.database.volunteer.VolunteerDatabase
import com.example.foodease.database.volunteer.VolunteerRepository
import com.example.foodease.ui.event.Event
import kotlinx.coroutines.launch

class VolunteerViewModel(application: Application): AndroidViewModel(application) {
    var volunteerList: LiveData<List<Volunteer>>
    private val repository: VolunteerRepository
    private val volunteerRecord = MutableLiveData<Volunteer>()

    init {

        //Create an instance of DB
        val volunteerDao = VolunteerDatabase.getDatabase(application).volunteerDao()
        //Connect DAO to repository
        repository = VolunteerRepository(volunteerDao)
        //Retrieve volunteer records
        volunteerList = repository.allVolunteers
    }

    //Coroutine
    fun insert(volunteer: Volunteer) = viewModelScope.launch {
        repository.insert(volunteer)
    }

    fun delete(volunteer: Volunteer) = viewModelScope.launch {
        repository.delete(volunteer)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun update(volunteer: Volunteer) = viewModelScope.launch {
        repository.update(volunteer)
    }

    val volunteers: LiveData<Volunteer> get() = volunteerRecord

    fun setSelected(volunteer: Volunteer){
        volunteerRecord.value = volunteer
    }
}