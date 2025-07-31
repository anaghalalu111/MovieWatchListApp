package com.example.moviewatchlistapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviewatchlistapp.R
import com.example.moviewatchlistapp.model.Movie
import com.example.moviewatchlistapp.viewmodel.MovieViewModel



@Composable
fun EditMovieScreen(movieId:Int,viewModel: MovieViewModel,OnMovieUpdated:(String,String, Int)->Unit) {

    val movie by viewModel.selectedMovie.collectAsState()
    var updatedTitle by remember { mutableStateOf("") }
    var updatedYear by remember { mutableStateOf("") }
    var updatedGenre by remember { mutableStateOf("") }

     //Run only  once when the edit screen is shown
    LaunchedEffect(key1 = movieId) {
        viewModel.loadMovieById(movieId)   /* to fetch a movie*/
    }

    //Populate the text field when movie data is fetched from the database
    LaunchedEffect(key1 = movie) { /*VImp:  Runs this block of code only when the selected movie changes*/
        movie?.let {movie->
          updatedTitle = movie.title
           updatedYear = movie.year.toString()
            updatedGenre = movie.genre
        }
    }

    Box(modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.movie_add),
            contentDescription ="Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Text(
                text = "Edit Movie",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))

            OutlinedTextField(value = updatedTitle,
                onValueChange = { updatedTitle = it },
                label = { Text(text = "Enter updated movie title") },
                textStyle = TextStyle(Color.White)
            )

            Spacer(modifier = Modifier.height(7.dp))

            OutlinedTextField(value = updatedYear,
                onValueChange = { updatedYear = it },
                label = { Text(text = "Enter updated movie year") },
                textStyle = TextStyle(Color.White)
            )

            Spacer(modifier = Modifier.height(7.dp))

            OutlinedTextField(value = updatedGenre,
                onValueChange = { updatedGenre = it },
                label = { Text(text = "Enter updated movie genre") },
                textStyle = TextStyle(Color.White))

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    val newUpdatedYear = updatedYear.toIntOrNull() ?: 0
                    val newUpdatedTitle = updatedTitle
                    val newUpdatedGenre = updatedGenre
                    movie?.let { originalMovie->// context of object
                        val updatedMovie = Movie(
                            id = originalMovie.id, // ✅ Safe, because inside 'let'
                            title = newUpdatedTitle,
                            genre = newUpdatedGenre,
                            year = newUpdatedYear
                        )
                        viewModel.updateMovie(updatedMovie)
                        OnMovieUpdated(newUpdatedTitle, newUpdatedGenre, newUpdatedYear)
                        //  }
                    }
                },
                shape = RoundedCornerShape(size = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                )
            ) {
                Text(text = "UPDATE CHANGES")
            }
        }
    }
}
