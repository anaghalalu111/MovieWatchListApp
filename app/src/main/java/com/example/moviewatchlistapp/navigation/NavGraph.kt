package com.example.moviewatchlistapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviewatchlistapp.view.AddMovieScreen
import com.example.moviewatchlistapp.view.EditMovieScreen
import com.example.moviewatchlistapp.view.MovieListScreen
import com.example.moviewatchlistapp.viewmodel.MovieViewModel

@Composable
fun NavigationGraph(viewModel: MovieViewModel) {
    val navController = rememberNavController()

    /*create Nav Host-A map[Layout] which hold's our app's screens*/

    NavHost(navController = navController, startDestination = Screen.MovieList.route) {

        /*Movie List Screen*/
        composable(Screen.MovieList.route) {
            MovieListScreen(
                viewModel = viewModel,
                OnNavigateToAddMovie = {
                    navController.navigate(Screen.AddMovie.route)
                },
                OnNavigateToEditMovie = { movieId ->
                    navController.navigate(Screen.EditMovie.createRoute(movieId))
                }


            )
        }
        /*Add Movie screen*/
        composable(Screen.AddMovie.route) {
            AddMovieScreen(viewModel = viewModel,
                OnMovieAdded = { title, genre, year ->
                    navController.navigate(Screen.MovieList.route) // same as navController.popBackStack()
                }

            )
        }
        /*Edit movie screen*/
        composable(
            Screen.EditMovie.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        )
        { backStackEntry ->
            /*extracting the id from the route*/
            val movieId = backStackEntry.arguments?.getInt("movieId")?:0

            if (movieId != null) {
                EditMovieScreen(movieId = movieId, viewModel = viewModel,

                    OnMovieUpdated = { newUpdatedTitle, newUpdatedGenre, newUpdatedYear ->
                        navController.popBackStack() /* Go to previous screen*/
                    }
                )
            }
        }
    }
}






