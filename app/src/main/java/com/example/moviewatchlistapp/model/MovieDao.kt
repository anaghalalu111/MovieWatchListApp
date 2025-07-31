package com.example.moviewatchlistapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao

interface MovieDao
{
    @Insert
    suspend fun insert(movie: Movie)  /*To insert a movie into database*/

    @Delete
    suspend fun delete(movie:Movie) /* To delete a movie from database*/

    @Update
    suspend fun update(movie: Movie)  /* To update an existing movie in the db*/

    @Query("SELECT * FROM MOVIES_TABLE")  /* To fetch all movies from DB to View*/
    fun getAllMovies():Flow<List<Movie>>

    @Query("SELECT * FROM MOVIES_TABLE WHERE id = :movieId")/* To get a movie by their ID*/
      fun getMovieById(movieId:Int):Flow<Movie>
}