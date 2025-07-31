package com.example.moviewatchlistapp.navigation

/*Define the screens in a sealed class*/
sealed class Screen(val route:String){
    object MovieList:Screen("movie_list")
    object AddMovie:Screen("add_movie")
    object EditMovie:Screen("edit_movie/{movieId}")
    fun createRoute(movieId: Int) = "edit_movie/$movieId"


}