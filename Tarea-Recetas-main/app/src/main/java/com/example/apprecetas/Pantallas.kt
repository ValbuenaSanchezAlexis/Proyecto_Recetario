package com.example.apprecetas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apprecetas.RecetasViewModel

@Composable
fun PantallaLista(viewModel: RecetasViewModel, onNavegarADetalle: (Int) -> Unit) {
    val recetas by viewModel.listaRecetas.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Mis Recetas", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recetas) { receta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        // Aquí cumplimos el requisito: "Al hacer clic, navega pasando el ID"
                        .clickable { onNavegarADetalle(receta.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = receta.nombre, style = MaterialTheme.typography.titleLarge)
                        Text(text = "${receta.calorias} kcal | ${receta.proteina}g proteína")
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaDetalle(viewModel: RecetasViewModel, onVolver: () -> Unit) {
    val receta by viewModel.recetaSeleccionada.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (receta != null) {
            Text(text = receta!!.nombre, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = receta!!.descripcion, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Información Nutricional:", style = MaterialTheme.typography.titleMedium)
            Text(text = "Calorías: ${receta!!.calorias} kcal")
            Text(text = "Proteína: ${receta!!.proteina} g")

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onVolver) {
                Text("Volver a la lista")
            }
        } else {
            Text("Cargando receta...")
        }
    }
}