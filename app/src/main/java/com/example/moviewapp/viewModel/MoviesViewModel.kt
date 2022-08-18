package com.example.moviewapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviewapp.model.MovieModel
import com.example.moviewapp.network.ApiService
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    var moviesList: List<MovieModel> by mutableStateOf(listOf())

    fun getMovies(){
        var apiService = ApiService.getInstance()
        viewModelScope.launch { moviesList = apiService.getMovies() }
    }
}