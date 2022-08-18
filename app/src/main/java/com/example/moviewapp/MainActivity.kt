package com.example.moviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.moviewapp.model.MovieModel
import com.example.moviewapp.ui.theme.MoviewAppTheme
import com.example.moviewapp.viewModel.MoviesViewModel

class MainActivity : ComponentActivity() {
    private val moviesViewModel by viewModels<MoviesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(topBar = { TopAppBar(title = { Text(text = "Movies App") }) }) {
                MoviewAppTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        MoviesList(moviesList = moviesViewModel.moviesList)
                        moviesViewModel.getMovies()
                    }
                }
            }
        }
    }
}


@Composable
fun MovieItem(movie: MovieModel) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Surface() {
            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = movie.imageUrl,
                        builder = {
                            scale(coil.size.Scale.FILL)
                            transformations(CircleCropTransformation())
                            placeholder(R.drawable.ic_launcher_background)
                        }
                    ),
                    contentDescription = movie.desc,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(4.dp),
                        color = Color.LightGray
                    )
                    Text(
                        text = movie.desc,
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )

                }

            }
        }
    }
}

@Composable
fun MoviesList(moviesList: List<MovieModel>) {
    LazyColumn {
        itemsIndexed(items = moviesList) { index, item ->
            MovieItem(movie = item)
        }
    }
}