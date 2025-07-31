package com.example.moviewatchlistapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviewatchlistapp.model.MovieRepository

class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            Log.d("MovieFactory", "Factory create() called")
            return MovieViewModel(repository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}