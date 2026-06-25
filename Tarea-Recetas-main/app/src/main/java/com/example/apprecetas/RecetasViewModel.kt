package com.example.apprecetas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecetasViewModel : ViewModel() {
    private val _listaRecetas = MutableStateFlow(
        listOf(
            Receta(1, "Tazón de Pollo y Arroz", "Comida ideal para cuadrar macros diarios. Pechuga a la plancha con arroz blanco.", 450, 45),
            Receta(2, "Hamburguesa Casera Fit", "Versión limpia para evitar comida rápida. Carne magra y pan integral.", 520, 35),
            Receta(3, "Batido de Proteína y Avena", "Rápido para el post-entrenamiento. Incluye scoop de suero de leche y creatina.", 300, 30),
            Receta(4, "Huevos Revueltos con Espinaca", "Desayuno ligero y nutritivo.", 250, 20)
        )
    )
    val listaRecetas: StateFlow<List<Receta>> = _listaRecetas.asStateFlow()

    private val _recetaSeleccionada = MutableStateFlow<Receta?>(null)
    val recetaSeleccionada: StateFlow<Receta?> = _recetaSeleccionada.asStateFlow()

    fun cargarDetalleReceta(id: Int) {
        val receta = _listaRecetas.value.find { it.id == id }
        _recetaSeleccionada.value = receta
    }
}