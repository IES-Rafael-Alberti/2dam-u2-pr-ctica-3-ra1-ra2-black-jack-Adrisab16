package com.pmdm.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.blackjack.ui.theme.BlackJackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackJackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {MainView()}
            }
        }
    }
}

@Composable
fun MainView(){MenuView()}
@Composable
fun MenuView(){
    var nav by remember {mutableStateOf(0)}
    if (nav == 0){
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
            Button(onClick = {nav = 1}, modifier = Modifier
                .padding(50.dp)
                .width(250.dp)
                .height(80.dp)){Text(text = "Jugador VS Jugador", color = Color.White)}
            //Botón para jugar 1 vs CPU:
            Button(onClick = {nav = 2}, modifier = Modifier
                .padding(20.dp)
                .width(250.dp)
                .height(80.dp),
            ){Text(text = "Jugador VS CPU", color = Color.White)}
            //Botón para jugar el blackjack clásico:
            Button(onClick = {nav = 3}, modifier = Modifier
                .padding(20.dp)
                .width(250.dp)
                .height(80.dp)){Text(text = "BlackJack Classic", color = Color.White)}
            //Botón para entrar al modo entrenamiento:
            Button(onClick = {nav = 4}, modifier = Modifier
                .padding(20.dp)
                .width(250.dp)
                .height(80.dp)){Text(text = "Modo Entrenamiento", color = Color.White)}
            //Botón para salir:
            Button(onClick = {nav = 5}, modifier = Modifier
                .padding(20.dp)
                .width(250.dp)
                .height(80.dp)){Text(text = "SALIR", color = Color.White)}
        }
    }
    when(nav){
        1->{JugadorVsJugador()}
        2->{JugadorVsCpu()}
        3->{ClassicGame()}
        4->{TrainingMode()}
        5->{android.os.Process.killProcess(android.os.Process.myPid())} // Esto hace que la aplicación se cierre, matando todos los porcesos en funcionamiento
    }
}

/**
 * Este modo de juego consistirá en un entrenamiento para entender las normas del
 * juego e implementar el funcionamiento básico del juego BlackJack para luego
 * implementar modos más complejos:
 */
@Composable
fun TrainingMode(){
    ModoEntrenamiento()
}

@Composable
fun ClassicGame(){}

@Composable
fun JugadorVsCpu(){}

@Composable
fun JugadorVsJugador(){}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlackJackTheme { TrainingMode() }
}