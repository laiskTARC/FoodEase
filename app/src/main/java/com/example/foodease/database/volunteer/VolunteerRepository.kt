package com.example.foodease.database.volunteer

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.foodease.ui.volunteer.Volunteer

class VolunteerRepository(private val volunteerDao: VolunteerDao) {
    //Room execute all queries on a separate thread
    val allContacts: LiveData<List<Volunteer>> = volunteerDao.getAllVolunteer()

    @WorkerThread
    suspend fun insert(contact: Volunteer){
        volunteerDao.insert(contact)
    }

    @WorkerThread
    suspend fun delete(contact: Volunteer){
        volunteerDao.delete(contact)
    }

    @WorkerThread
    suspend fun deleteAll(){
        volunteerDao.deleteAll()
    }

    @WorkerThread
    suspend fun update(contact: Volunteer){
        volunteerDao.update(contact)
    }
}