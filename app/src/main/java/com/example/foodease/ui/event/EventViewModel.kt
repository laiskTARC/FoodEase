package com.example.foodease.ui.event

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodease.database.event.EventDatabase
import com.example.foodease.database.event.EventRepository
import kotlinx.coroutines.launch

class EventViewModel(application: Application): AndroidViewModel(application) {
    //val eventList = MutableLiveData<ArrayList<Event>>()
    var eventList: LiveData<List<Event>>
    private val repository: EventRepository

    init {
        //val list = ArrayList<Event>()
        //eventList.value = list
        val eventDao = EventDatabase.getDatabase(application).eventDao()
        repository = EventRepository(eventDao)
        eventList = repository.allEvents
    }

    fun insert(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    fun delete(event: Event) = viewModelScope.launch {
        repository.delete(event)
    }

    fun update(event: Event) = viewModelScope.launch {
        repository.update(event)
    }
}