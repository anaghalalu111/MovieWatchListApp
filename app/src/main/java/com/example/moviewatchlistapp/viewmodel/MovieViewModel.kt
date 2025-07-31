package com.example.moviewatchlistapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviewatchlistapp.model.Movie
import com.example.moviewatchlistapp.model.MovieRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MovieViewModel (private val repository: MovieRepository):ViewModel(){

    /*Expose movies list in viewmodel*/
   /* Converted the Flow of List of Data from Repository to LiveData in the View Model for the View to observe*/
    val movies:LiveData<List<Movie>> = repository.allMovies.asLiveData()

    /*Create a private mutable state variable that hold the selected movie object*/
    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie


    init{
        Log.d("MovieViewModel","ViewModel created")
    }

    fun addMovie(movie: Movie){
        viewModelScope.launch {
            repository.insert(movie)  /*View Model called the instance of repository to add movie into db*/
            Log.d("Adding Movies","Added:$movie")
        }
    }

    fun deleteMovie(movie: Movie){
        viewModelScope.launch {
            repository.delete(movie)
        }
    }
    fun updateMovie(movie:Movie){
        viewModelScope.launch {
            repository.update(movie)
        }
    }
    /*fetch the movie object[Selected movie from Database to ViewModel]*/

    fun loadMovieById(movieId:Int){
        viewModelScope.launch {
           repository.getMovieById(movieId)
               .collect{movie->         /*Collect the flow returned by the repository*/
                   _selectedMovie.value = movie

               }
        }
    }


}