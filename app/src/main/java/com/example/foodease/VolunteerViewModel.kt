package com.example.foodease

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodease.database.Volunteer
import com.example.foodease.database.VolunteerDatabase
import com.example.foodease.database.VolunteerRepository
import kotlinx.coroutines.launch

class VolunteerViewModel(application: Application): AndroidViewModel(application) {
    var volunteerList: LiveData<List<Volunteer>>
    private val repository: VolunteerRepository

    private val _volunteer : MutableLiveData<Volunteer> by lazy {
        MutableLiveData<Volunteer>()
    }

    init {
        //val list = ArrayList<Contact>()
        //contactList.value = list

        //Create an instance of DB
        val contactDao = VolunteerDatabase.getDatabase(application).volunteerDao()
        //Connect DAO to repository
        repository = VolunteerRepository(contactDao)
        //Retrieve contact records
        volunteerList = repository.allContacts

        _volunteer.value = Volunteer("","","","")
    }

    val volunteer: LiveData<Volunteer> get() = _volunteer

    fun setVolunteer(v: Volunteer){
        _volunteer.value = v
    }

    //Coroutine
    fun insert(contact: Volunteer) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete(contact: Volunteer) = viewModelScope.launch {
        repository.delete(contact)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun update(contact: Volunteer) = viewModelScope.launch {
        repository.update(contact)
    }
}