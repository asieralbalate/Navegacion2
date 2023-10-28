package com.example.navegacion2.coffee

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.navegacion2.MyNavigationBar
import com.example.navegacion2.ui.theme.FontTittle
import com.example.navegacion2.ui.theme.Pink80
import com.example.navegacion2.ui.theme.Purple40
import com.example.navegacion2.ui.theme.Purple80

@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Comentarios(navControllerName: String, navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val nombreCafeteria = navBackStackEntry?.arguments?.getString("cafeteriaName") ?: ""
    var isMenuVisible by remember { mutableStateOf(false) }
    val listState = rememberLazyStaggeredGridState()
    val buttonVisible = remember { mutableStateOf(true) }
    val comments = listOf<String>(
        "Some loose, I still recommend it.",
        "Centeral and cozy. We'll come back safely.",
        "The setting very good, but on the top floor a little bit...",
        "The food was delicious and quite well priced, lots of variety of dishes.",
        "The very friendly staff, they allowed us to see the whole establishment.",
        "Very good.","Excellent. Highlight the extensive coffee chart." ,"Good atmos,phere and good service. I recommend it.",
        "On holidays too much waiting time. The waiters are not enough. I don't recommend it. I won't come back." ,
        "We will repeat. Great selection of cakes and coffees.", "Everything I've tried in the cafeteria is rich, sweet or salty." ,
        "The very nice dishes all of design that in the surroundings of the bar is ideal.",
        "Negative points: the service is very slow and prices are a little high."
    )

    Scaffold(bottomBar = { MyNavigationBar(navController = navController)}, topBar = {
        TopAppBar(
            title = {
                Text(text = "CoffeeShops", color = Color.White, fontSize = 20.sp)
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            actions = {
                Row (){
                    IconButton(
                        onClick = {
                            isMenuVisible = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                Row (){
                    DropdownMenu(
                        expanded = isMenuVisible,
                        onDismissRequest = {
                            isMenuVisible = false
                        },
                        modifier = Modifier.background(Purple40)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Compartir",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            },
                            onClick = { isMenuVisible = false },
                            leadingIcon = {Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                tint = Color.White
                            ) },
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Album",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            },
                            onClick = { isMenuVisible = false },
                            leadingIcon = {Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = Color.White
                            ) },
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Purple40)
        )
    }) {
        Column (modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())){
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                Text(
                    text = nombreCafeteria,
                    fontFamily = FontTittle,
                    fontSize = 32.sp,
                    color = Color.Black,
                )
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                state = listState
            ) {
                items(comments.size) { index ->
                    Card(
                        modifier = Modifier.padding(6.dp),
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(Purple80)
                    ) {
                        Text(
                            text = comments[index],
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp))
                    }
                }

                val scrollOffset = listState.firstVisibleItemScrollOffset
                if (scrollOffset > 0 && buttonVisible.value) {
                    buttonVisible.value = false
                } else if (scrollOffset == 0 && !buttonVisible.value) {
                    buttonVisible.value = true
                }

            }

        }
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {
            if (!buttonVisible.value) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(16.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(Purple40),
                ) {
                    Text(text = "Add new comment")
                }
            }
        }
    }
}