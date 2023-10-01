package com.example.foodease.database.event

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.foodease.ui.event.Event

class EventRepository(private val eventDao: EventDao) {

    //Room execute all queries on a separate thread
    val allEvents: LiveData<List<Event>> = eventDao.getAllEvent()

    @WorkerThread
    suspend fun insert(event: Event){
        eventDao.insert(event)
    }

    @WorkerThread
    suspend fun delete(event: Event){
        eventDao.delete(event)
    }

    @WorkerThread
    suspend fun update(event: Event){
        eventDao.update(event)
    }
}