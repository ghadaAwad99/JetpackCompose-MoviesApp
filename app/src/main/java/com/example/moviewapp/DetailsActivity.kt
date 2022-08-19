package com.example.moviewapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.example.moviewapp.model.MovieModel
import com.example.moviewapp.ui.theme.MoviewAppTheme

class DetailsActivity : ComponentActivity() {
    private val movie by lazy { intent.getSerializableExtra(MOVIE_ID) as MovieModel }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviewAppTheme {
                DetailsScreen(movie = movie)
            }
        }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun newIntent(context: Context, movie: MovieModel) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(MOVIE_ID, movie)
            }

    }
}


