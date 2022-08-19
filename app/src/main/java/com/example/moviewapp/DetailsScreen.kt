package com.example.moviewapp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.moviewapp.model.MovieModel

@Composable
fun DetailsScreen(movie: MovieModel) {
    val activity = LocalContext.current as Activity
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = movie.name, color = Color.White) },
            navigationIcon = {
                IconButton(
                    onClick = {
                        activity.finish()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            },
            backgroundColor = colorResource(id = R.color.dark_blue)
        )

    }) {
        BoxWithConstraints {
            Surface(color = colorResource(id = R.color.dark_blue)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    DetailsHeader(movie, this@BoxWithConstraints.maxHeight)
                    DetailsContent(movie)
                }
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

//extension function
fun Context.getActivity(): AppCompatActivity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is AppCompatActivity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}