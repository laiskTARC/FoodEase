package com.example.foodease.database.request

import androidx.activity.result.IntentSenderRequest
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.foodease.database.request.RequestDao
import com.example.foodease.ui.request.Request

class RequestRepository(private val requestDao: RequestDao) {
    //Room execute all queries on a separate thread
    val allRequest: LiveData<List<Request>> = requestDao.getAllRequest()

    @WorkerThread
    suspend fun insert(request: Request){
        requestDao.insert(request)
    }

    @WorkerThread
    suspend fun delete(request: Request){
        requestDao.delete(request)
    }

    @WorkerThread
    suspend fun update(request: Request){
        requestDao.update(request)
    }
}