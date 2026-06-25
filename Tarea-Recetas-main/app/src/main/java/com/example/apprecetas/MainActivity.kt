package com.example.apprecetas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apprecetas.ui.theme.AppRecetasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRecetasTheme {
                AppNavegacion()
            }

        }
    }
}

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()    // Instanciamos el ViewModel aquí para que viva durante toda la navegación
    val viewModel: RecetasViewModel = viewModel()

    // rutas
    NavHost(navController = navController, startDestination = "Home") {

        // R 1
        composable("Home") {
            PantallaLista(
                viewModel = viewModel,
                onNavegarADetalle = { idReceta ->
                    navController.navigate("Detail/$idReceta")
                }
            )
        }

        // R 2
        composable(
            route = "Detail/{recetaId}",
            arguments = listOf(navArgument("recetaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("recetaId") ?: return@composable

            LaunchedEffect(id) {
                viewModel.cargarDetalleReceta(id)
            }

            PantallaDetalle(
                viewModel = viewModel,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}