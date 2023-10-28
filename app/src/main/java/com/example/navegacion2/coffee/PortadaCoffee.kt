package com.example.navegacion2.coffee

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navegacion2.MyNavigationBar
import com.example.navegacion2.R
import com.example.navegacion2.ui.theme.FontTittle
import com.example.navegacion2.ui.theme.Pink80
import com.example.navegacion2.ui.theme.Purple40
import com.example.navegacion2.ui.theme.Purple80

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortadaCoffee(navController: NavHostController) {
    var isMenuVisible by remember { mutableStateOf(false) }
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
        Box (modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())){
            LazyColumn() {
                items(getCardData()) { card ->
                    ItemCard(cardData = card, navController = navController)
                }
            }
        }
    }
}
data class CardData(
    var name: String,
    var adress: String,
    @DrawableRes var photo: Int
)

fun getCardData(): List<CardData> {
    return listOf(
        CardData(
            "Antico Caffè Greco",
            "St. Italy, Rome",
            R.drawable.images,
        ),
        CardData(
            "Coffee Room",
            "St. Germany, Berlin",
            R.drawable.images1,
        ),
        CardData(
            "Coffee Ibiza",
            "St. Colon, Madrid",
            R.drawable.images2,
        ),
        CardData(
            "Pudding Coffee Shop",
            "St. Diagonal, Barcelona",
            R.drawable.images3,
        ),
        CardData(
            "L'Express",
            "St. Picadilly Circus, London",
            R.drawable.images4,
        ),
        CardData(
            "Coffee Corner",
            "St. Àngel Guimerà",
            R.drawable.images5,
        ),
        CardData(
            "Sweet Cup",
            "St. Kinkerstraat, Amsterdam",
            R.drawable.images6,
        )
    )
}

@Composable
fun ItemCard(cardData: CardData, navController: NavHostController) {
    var rating by remember { mutableStateOf(0) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate("Comentarios/${cardData.name}")
            },
        colors = CardDefaults.cardColors(containerColor = Purple80),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Image(
            painter = painterResource(id = cardData.photo),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = cardData.name,
                    fontFamily = FontTittle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }

            RatingBar(modifier = Modifier.padding(start = 10.dp),
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                }
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = cardData.adress, modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Divider()
            Button(
                onClick = { },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.Transparent, contentColor = Color(0xFFD988B9)
                )
            ) {
                Text(text = "RESERVE")
            }

        }
    }
}


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    stars: Int = 5,
    starsColor: Color = Color.Black,
    onRatingChanged: (Int) -> Unit
) {
    Row(modifier = modifier) {
        repeat(stars) { starIndex ->
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if (starIndex < rating) Color.Black else Color.White,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { onRatingChanged(starIndex + 1) }
            )
        }
    }
}