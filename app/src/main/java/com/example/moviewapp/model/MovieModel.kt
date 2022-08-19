package com.example.moviewapp.model

import java.io.Serializable

data class MovieModel(val name: String, val imageUrl: String, val desc: String, val category: String) : Serializable