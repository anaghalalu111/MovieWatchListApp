package com.example.moviewatchlistapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 2)
abstract class MovieDatabase :RoomDatabase(){ /*Create a custom movie database in your room database*/
    abstract fun movieDao():MovieDao

}