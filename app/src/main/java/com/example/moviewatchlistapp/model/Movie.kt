package com.example.moviewatchlistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val title:String,
    val genre:String,
    val year: Int
)
