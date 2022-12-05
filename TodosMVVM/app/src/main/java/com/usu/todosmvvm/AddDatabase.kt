package com.usu.todosmvvm

import androidx.room.Database
import androidx.room.RoomDatabase
import com.usu.todosmvvm.models.Pastry

@Database(entities = [Pastry::class], version = 1)
abstract class AddDatabase: RoomDatabase() {
    abstract fun getPastryDao(): PastryDao
}