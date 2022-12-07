package com.usu.todosmvvm

import androidx.room.Room
import com.usu.todosmvvm.models.Pastry

object PastryRepository {
    private val db: AddDatabase
    init{
        db = Room.databaseBuilder(
            PastryApplication.getInstance(),
            AddDatabase::class.java, "database"
        ).build()
    }

    suspend fun createPastry(pastry: Pastry){

    }
    suspend fun getAllPastries(): List<Pastry>{
        //return PastryDao.getAllPastries()
        return listOf()
    }

    suspend fun updatePastryCount(pastry: Pastry){

    }

}