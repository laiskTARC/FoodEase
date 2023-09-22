package com.example.foodease.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.foodease.Volunteer

class VolunteerRepository(private val volunteerDao: VolunteerDao) {
    //Room execute all queries on a separate thread
    val allContacts: LiveData<List<Volunteer>> = VolunteerDao.getAllContact()

    @WorkerThread
    suspend fun insert(contact: Volunteer){
        VolunteerDao.insert(contact)
    }

    @WorkerThread
    suspend fun delete(contact: Volunteer){
        VolunteerDao.delete(contact)
    }

    @WorkerThread
    suspend fun deleteAll(){
        VolunteerDao.deleteAll()
    }

    @WorkerThread
    suspend fun update(contact: Volunteer){
        VolunteerDao.update(contact)
    }
}