package com.example.moviewatchlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.moviewatchlistapp.model.MovieDatabase
import com.example.moviewatchlistapp.model.MovieRepository
import com.example.moviewatchlistapp.navigation.NavigationGraph
import com.example.moviewatchlistapp.viewmodel.MovieViewModel
import com.example.moviewatchlistapp.viewmodel.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Step1:Create an instance of database*/
        val database = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,"movie_database"
        ).build()

        /*Step2:Get dao from database*/
        val movieDao = database.movieDao()

        /*Step3:Get repository instance*/
        val repository = MovieRepository(movieDao)

       /*Step 4: Get view model factory instance*/
        val factory = MovieViewModelFactory(repository)

        /* Step5 : Create viewmodel*/
        val viewModel: MovieViewModel =
            ViewModelProvider(this, factory)[MovieViewModel::class.java]

        enableEdgeToEdge()
        setContent {

            /* Step 6: Connect view model to UI*/
            NavigationGraph(viewModel = viewModel)

        }
    }
}

