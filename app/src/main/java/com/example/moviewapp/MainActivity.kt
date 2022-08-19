package com.example.moviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Scaffold(topBar = {
                TopAppBar(
                    backgroundColor = colorResource(id = R.color.dark_blue),
                    title = { Text(text = "Movies App", color = Color.White) })
            }) {
                MoviewAppTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        MoviesList(moviesList = moviesViewModel.moviesList) {
                            startActivity(
                                DetailsActivity.newIntent(this, it)
                            )
                        }
                        moviesViewModel.getMovies()
                    }
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun moviesApp() {
    MovieItem(
        movie = MovieModel(
            name = "Coco",
            desc = "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
            imageUrl = "https://howtodoandroid.com/images/coco.jpg",
            category = "Latest"
        ),
        {

        }
    )
}
*/


@Composable
fun MovieItem(movie: MovieModel, navigateToDetails: (MovieModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 8.dp)
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Surface(color = colorResource(id = R.color.card)) {
            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .clickable { navigateToDetails(movie) }
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
                        .weight(0.4f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.caption,
                        color = Color.LightGray
                    )
                    Text(
                        fontSize = 13.sp,
                        text = movie.desc,
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 4,
                        color = Color.White
                    )

                }

            }
        }
    }
}

@Composable
fun MoviesList(moviesList: List<MovieModel>, navigateToDetails: (MovieModel) -> Unit) {
    LazyColumn(modifier = Modifier.background(colorResource(id = R.color.dark_blue))) {
        itemsIndexed(items = moviesList) { index, item ->
            MovieItem(movie = item, navigateToDetails)
        }
    }
}