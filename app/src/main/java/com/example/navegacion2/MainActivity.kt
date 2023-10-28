package com.example.navegacion2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navegacion2.coffee.Comentarios
import com.example.navegacion2.coffee.PortadaCoffee
import com.example.navegacion2.elsol.PortadaElSol
import com.example.navegacion2.elsol.Email
import com.example.navegacion2.elsol.Info
import com.example.navegacion2.fotos.PortadaFotos
import com.example.navegacion2.ui.theme.Navegacion2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navegacion2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Portada") {
                        composable("PortadaFotos") { PortadaFotos(navController) }
                        composable("Portada") { Portada(navController) }
                        composable("PortadaElSol") { PortadaElSol(navController, snackbarHostState = SnackbarHostState()) }
                        composable("Filled.Email") { Email(navController, snackbarHostState =  SnackbarHostState()) }
                        composable("Filled.Info") { Info(navController, snackbarHostState =  SnackbarHostState()) }
                        composable("Filled.Build") { PortadaElSol(navController, snackbarHostState =  SnackbarHostState()) }
                        composable("PortadaCoffee") { PortadaCoffee(navController) }
                        composable(
                            route = "Comentarios/{cafeteriaName}",
                            arguments = listOf(navArgument("cafeteriaName") { type = NavType.StringType })
                        ) { backStackEntry ->
                            Comentarios(backStackEntry.arguments?.getString("cafeteriaName") ?: "", navController)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Navegacion2Theme {
    }
}