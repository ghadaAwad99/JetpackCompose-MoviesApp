package com.example.moviewapp.network

import com.example.moviewapp.model.MovieModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("movielist.json")
    suspend fun getMovies() : List<MovieModel>

    companion object {
        private const val BASE_URL: String = "https://howtodoandroid.com/apis/"
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}