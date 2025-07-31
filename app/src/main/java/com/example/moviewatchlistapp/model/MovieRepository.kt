package com.example.moviewatchlistapp.model
import kotlinx.coroutines.flow.Flow

class MovieRepository (private val movieDao: MovieDao){

    val allMovies:Flow<List<Movie>> =  movieDao.getAllMovies() /* flow of data from database*/


    suspend fun insert(movie:Movie){
        movieDao.insert(movie)  // talks with database via dao
    }

    suspend fun delete(movie: Movie){
        movieDao.delete(movie)  // talks with database via dao
    }

    suspend fun update(movie: Movie){
        movieDao.update(movie)
    }
     fun getMovieById(movieId:Int): Flow<Movie> {
        return movieDao.getMovieById(movieId)
    }



}