package com.pmdm.blackjack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pmdm.blackjack.model.Routes
import androidx.navigation.NavHostController
import com.pmdm.blackjack.ui.theme.BlackJackTheme

@Composable
fun MenuView(navController: NavHostController){
    var nav by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(id = R.drawable.fondomenu),
            contentScale = ContentScale.FillHeight
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text = "BlackJack",
            modifier = Modifier
                .padding(40.dp),
            fontWeight = FontWeight.Bold)
        //Botón para jugar 1 vs 1:
        Button(onClick = {navController.navigate(Routes.jcj.route)}, modifier = Modifier
            .padding(50.dp)
            .width(250.dp)
            .height(80.dp)){ Text(text = "Jugador VS Jugador", color = Color.White) }
        //Botón para jugar 1 vs CPU:
        Button(onClick = {navController.navigate(Routes.jVsCpu.route)}, modifier = Modifier
            .padding(20.dp)
            .width(250.dp)
            .height(80.dp),
        ){ Text(text = "Jugador VS CPU", color = Color.White) }
        //Botón para jugar el blackjack clásico:
        Button(onClick = {navController.navigate(Routes.ClassicMode.route)}, modifier = Modifier
            .padding(20.dp)
            .width(250.dp)
            .height(80.dp)){ Text(text = "BlackJack Classic", color = Color.White) }
        //Botón para entrar al modo entrenamiento:
        Button(onClick = {navController.navigate(Routes.TrainingMode.route)}, modifier = Modifier
            .padding(20.dp)
            .width(250.dp)
            .height(80.dp)){ Text(text = "Modo Entrenamiento", color = Color.White) }
        //Botón para salir:
        Button(onClick = {android.os.Process.killProcess(android.os.Process.myPid())}, modifier = Modifier
            .padding(20.dp)
            .width(250.dp)
            .height(80.dp)){ Text(text = "SALIR", color = Color.White) }
    }
}