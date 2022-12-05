package com.usu.todosmvvm

import androidx.room.Room

object PastryRepository {
    init{
        Room.databaseBuilder(
            PastryApplication.getInstance(),
            AddDatabase::class.java, "database"
        ).build()
    }
}