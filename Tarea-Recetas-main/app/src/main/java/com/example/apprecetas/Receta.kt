package com.example.apprecetas

data class Receta(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val calorias: Int,
    val proteina: Int
)