package com.example.moviewatchlistapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.moviewatchlistapp.model.Movie
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moviewatchlistapp.R
import com.example.moviewatchlistapp.viewmodel.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieViewModel,
    OnNavigateToEditMovie: (Int) -> Unit, OnNavigateToAddMovie: () -> Unit){

    val moviesList by viewModel.movies.observeAsState(emptyList())

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.movie_add),
            contentDescription = "null",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "MOVIE WATCHLIST",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 28.dp),
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    ) },
                    colors = TopAppBarDefaults.topAppBarColors(Color.LightGray))
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { OnNavigateToAddMovie() }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add movie", tint = Color.Black)

                }
            },
            containerColor = Color.Transparent


            ) { innerPadding -> // to avoid overlapping

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(0.dp)


            ) {
                items(moviesList) { movie ->
                    MovieItem(movie, viewModel, OnNavigateToEditMovie)
                }
            }

        }
    }
}



@Composable
fun MovieItem(movie: Movie, viewModel: MovieViewModel, OnNavigateToEditMovie: (Int) -> Unit){
    
   Card(
       modifier = Modifier
           .width(400.dp)
           .height(130.dp)
           .padding(16.dp),
       colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
       elevation = CardDefaults.cardElevation(4.dp),

   ) {

       Row(
           modifier = Modifier.padding(10.dp),
           verticalAlignment = Alignment.CenterVertically

       )

       {
           Icon(imageVector = Icons.Filled.Movie, contentDescription = "movie_icon", modifier = Modifier.size(38.dp).padding(start=3.dp))

           Spacer(modifier = Modifier.width(10.dp))
           Column(
               modifier = Modifier.weight(1f)
           )
            {

               Text(
                   text = "${movie.title}",
                   style = MaterialTheme.typography.titleMedium,
                   fontSize = 24.sp,
                   color = Color.White,

               )
               Text(
                   text = "${movie.genre} | ${movie.year}",
                   style = MaterialTheme.typography.titleSmall,
                   fontSize = 18.sp,
                   color = Color.White
               )
           }

           IconButton(onClick = { OnNavigateToEditMovie(movie.id)}) {
               Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit movie", modifier = Modifier.size(38.dp))
           }
           IconButton(onClick = { viewModel.deleteMovie(movie)}) {
               Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear movie", modifier = Modifier.size(38.dp))

           }


       }
   }
}
