package com.example.moviewapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.moviewapp.model.MovieModel
import java.util.*

@Composable
fun DetailsScreen(movie: MovieModel) {
    BoxWithConstraints {

        Surface(color = colorResource(id = R.color.dark_blue)) {
            Column(modifier = Modifier.fillMaxSize()) {
                DetailsHeader(movie, this@BoxWithConstraints.maxHeight)
                DetailsContent(movie)
            }
        }
    }

}

@Composable
fun DetailsHeader(movie: MovieModel, containerHeight: Dp) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(containerHeight / 2),
            painter = rememberImagePainter(data = movie.imageUrl),
            contentDescription = movie.desc,
            contentScale = ContentScale.Crop
        )
}


@Composable
fun DetailsContent(movie: MovieModel) {
    Text(
        text = movie.name,
        modifier = Modifier.padding(16.dp, 10.dp),
        style = MaterialTheme.typography.h5,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = movie.category,
        style = TextStyle(color = colorResource(id = R.color.caption), fontSize = 12.sp),
        modifier = Modifier.padding(16.dp, 3.dp)
    )
    Text(
        text = movie.desc,
        style = TextStyle(color = Color.White, fontSize = 15.sp),
        modifier = Modifier.padding(16.dp, 3.dp)
    )
}