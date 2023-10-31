package com.example.navegacion2

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Portada(navController: NavHostController) {
    Scaffold(bottomBar = {
        MyNavigationBar(navController)
    }) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Mis Proyectos")
        }
    }
}

@Composable
fun MyNavigationBar(
    navController: NavHostController
) {
    var selectedItem  by remember { mutableStateOf(0) }
    val items = listOf("MyPhotos", "CoffeeShops", "ElSol")

    NavigationBar(modifier = Modifier.height(80.dp)) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (selectedItem) {
                        0 -> navController.navigate("PortadaFotos")
                        1 -> navController.navigate("PortadaCoffee")
                        2 -> navController.navigate("PortadaElSol")
                    }
                },
                icon = {
                    when (item) {
                        "MyPhotos" -> Icon(
                            imageVector = Icons.Filled.AccountBox,
                            contentDescription = item
                        )

                        "CoffeeShops" -> Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = item
                        )

                        "ElSol" -> Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = item
                        )
                    }
                })
        }
    }
}