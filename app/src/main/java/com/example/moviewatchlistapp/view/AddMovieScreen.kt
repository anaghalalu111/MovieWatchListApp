package com.example.moviewatchlistapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviewatchlistapp.R
import com.example.moviewatchlistapp.model.Movie
import com.example.moviewatchlistapp.viewmodel.MovieViewModel


@Composable
fun AddMovieScreen(viewModel: MovieViewModel, OnMovieAdded:(String,Int,String)->Unit) {
    var title by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.movie_add),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add Movie",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(value = title,
                onValueChange = { title = it },
                label = { Text(text = "Enter movie title") },
                textStyle = TextStyle(Color.White),
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = year,
                onValueChange = { year = it },
                label = { Text("Enter release year") },
                textStyle = TextStyle(Color.White)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = genre,
                onValueChange = { genre = it },
                label = { Text("Enter genre of the movie")},
                textStyle = TextStyle(Color.White)


                )

        }

        Column(
            modifier = Modifier
                .padding(top = 1.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {

            Row(
                modifier = Modifier
                    .padding(8.dp),
                verticalAlignment = Alignment.Top


            ) {
                OutlinedButton(
                    onClick = {
                        val yearInt = year.toIntOrNull() ?: 0
                        if(!title.isEmpty()&& !genre.isEmpty()&& !yearInt.toString().isEmpty()) {
                            val newMovie = Movie(0, title, genre, yearInt)/*Create a new movie object i.e an new row in the movies_table*/

                            viewModel.addMovie(newMovie)     /*logic for adding new movie into database*/
                        }
                        OnMovieAdded(title, yearInt, genre)

                        title = ""
                        year = ""
                        genre = " "

                    },
                    shape = RoundedCornerShape(size = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black
                    )
                ) {
                    Text(text = "SAVE")

                }
            }
        }
    }
}
