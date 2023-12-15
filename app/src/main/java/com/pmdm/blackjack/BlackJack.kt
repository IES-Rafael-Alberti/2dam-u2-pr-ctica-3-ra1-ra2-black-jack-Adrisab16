package com.pmdm.blackjack
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



//funcion que nos va imprimiendo las cartas en base a la mano y un contexto
@SuppressLint("DiscouragedApi")
@Composable
fun BucleCarta(mano: MutableList<Int>, context: Context) {
    LazyRow {
        items(mano) { item ->
            val intCarta = context.resources.getIdentifier(
                "c${item}",
                "drawable",
                context.packageName,
            )
            Image(
                painter = painterResource(id = intCarta),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

/*Imprimimos y damos formato para dos bucles, uno para cada jugador,
haciendo uso de la funcion de bucles anterior*/
@Composable
fun ImprimeBucles(player1: Jugador, player2: Jugador, context: Context) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        BucleCarta(mano = player1.mano, context = context)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        BucleCarta(mano = player2.mano, context = context)
    }

}

/**
 * Metodo que va actualizando nuestra carta
 */
@SuppressLint("DiscouragedApi")
@Composable
fun cardUpdater(dorsoCarta: String, context: Context): Int {
    return context.resources.getIdentifier(
        dorsoCarta,
        "drawable",
        context.packageName
    )
}

@SuppressLint("MutableCollectionMutableState")
@Preview
@Composable
fun BlackJackGame() { //Aquí comienza la lógica del juego
    Image(
        painter = painterResource(id = R.drawable.fondojuego), contentDescription = "",
        modifier = Modifier
            .fillMaxSize(), contentScale = ContentScale.FillBounds
    )

    //Variables necesarias para las cartas de nuestro juego
    val context = LocalContext.current
    var reversoCarta by rememberSaveable { mutableStateOf("reverso") }


    //Variables necesarias para los jugadores
    val manoJ1 by remember { mutableStateOf(mutableListOf<Int>()) }
    val J1 = Jugador("player1", manoJ1)
    val manoJ2 by remember { mutableStateOf(mutableListOf<Int>()) }
    val J2 = Jugador("player2", manoJ2)
    var turnoJ1 by remember { mutableStateOf(true) }
    var turnoJ2 by remember { mutableStateOf(false) }
    var puntosJ1 by remember { mutableStateOf(0) }
    var puntosJ2 by remember { mutableStateOf(0) }


    //Variable que controla si nuestros botones son pulsados para almacenar la opcion de pasar
    var btnPasarP1IsClicked by remember { mutableStateOf(true) }
    var btnPasarP2IsClicked by remember { mutableStateOf(false) }

    ImprimeBucles(player1 = J1, player2 = J2, context = context)

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row {
            SacarCartaJ2(onDameCartaClick = {
                val carta = Baraja.cogerCarta()
                if (turnoJ2 && btnPasarP2IsClicked && puntosJ2 < 21) {
                    reversoCarta = "c${carta.idDrawable}"
                    J2.mano.add(carta.idDrawable)
                    if (carta.puntosMin == 1) {
                        if (puntosJ2 + carta.puntosMax < 21) puntosJ2 += carta.puntosMax else puntosJ2 += carta.puntosMin
                    } else puntosJ2 += carta.puntosMin
                }
            })
            Pasar(onPasaClick = {
                turnoJ1 = true
                turnoJ2 = false
                //definimos isClicked a false para que el jug no pueda pedir cartas de nuevo
                btnPasarP2IsClicked = false
                btnPasarP1IsClicked = true
            })
        }

        Row {
            SacarCartaJ1(onDameCartaClick = {
                val carta = Baraja.cogerCarta()
                if (turnoJ1 && btnPasarP1IsClicked && puntosJ1 < 21) {
                    reversoCarta = "c${carta.idDrawable}"
                    J1.mano.add(carta.idDrawable)
                    if (carta.puntosMin == 1) {
                        if (puntosJ1 + carta.puntosMax < 21) puntosJ1 += carta.puntosMax else puntosJ1 += carta.puntosMin
                    } else puntosJ1 += carta.puntosMin
                }
            })
            Pasar(onPasaClick = {
                turnoJ1 = false
                turnoJ2 = true
                //definimos isClicked a false para que el jug no pueda pedir cartas de nuevo
                btnPasarP1IsClicked = false
                btnPasarP2IsClicked = true
            })
        }

        PuntosTotales(puntPlayer1 = puntosJ1, puntPlayer2 = puntosJ2)

        TurnoJ(turnoJ1 = turnoJ1)

        if(puntosJ2>=21 || puntosJ1>=21){
            Puntuacion(
                puntPlayer1 = puntosJ1,
                manoSizeP1 = manoJ1,
                puntPlayer2 = puntosJ2,
                manoSizeP2 = manoJ2,
                btnPasarP1IsClicked,
                btnPasarP2IsClicked
            )
        }

    }
    cardUpdater(dorsoCarta = reversoCarta, context = context)
}


@Composable
fun Puntuacion(
    puntPlayer1: Int,
    manoSizeP1: MutableList<Int>,
    puntPlayer2: Int,
    manoSizeP2: MutableList<Int>,
    botonPasarJ1: Boolean,
    botonPasarJ2: Boolean
) {

    var boolPuntosValidos = false
    var boolPuntosMayor = false
    var boolNumCartas = false

    when {
        puntPlayer1 <= 21 -> boolPuntosValidos = true
        puntPlayer1 > puntPlayer2 -> boolPuntosMayor = true
        manoSizeP1.size < manoSizeP2.size -> boolNumCartas = true
    }
    if (botonPasarJ1 && botonPasarJ2) {
        when {
            boolPuntosValidos && boolPuntosMayor -> Text(text = "GANADOR JUGADOR 1!!!")
            boolPuntosValidos && !boolPuntosMayor -> Text(text = "GANADOR JUGADOR 2!!!")
            puntPlayer1 == puntPlayer2 -> {
                if (boolNumCartas) Text(text = "GANADOR JUGADOR 1!!!") else Text(text = "GANADOR JUGADOR 2!!!")
            }
        }
    }
}

//Funcion que nos muestra el turno de cada jugador
@Composable
fun TurnoJ(turnoJ1: Boolean) {
    if (turnoJ1) {
        Row(Modifier.padding(30.dp)) {
            Text(text = "Turno Jugador 1")
        }
    } else {
        Row(Modifier.padding(30.dp)) {
            Text(text = "Turno Jugador 2")
        }
    }

}

@Composable
fun PuntosJ1(puntosJ1: Int) {
    Text(text = "Puntos Jugador 1 -> $puntosJ1")
}

@Composable
fun PuntosJ2(puntosJ2: Int) {
    Text(text = "Puntos Jugador 2 -> $puntosJ2")
}

@Composable
fun PuntosTotales(puntPlayer1: Int, puntPlayer2: Int) {
    Row {
        PuntosJ1(puntosJ1 = puntPlayer1)
        Spacer(modifier = Modifier.width(40.dp))
        PuntosJ2(puntosJ2 = puntPlayer2)
    }
}

@Composable
fun SacarCartaJ1(onDameCartaClick: () -> Unit) {
    Row(Modifier.padding(10.dp)) {
        Button(
            onClick = {
                onDameCartaClick()
            },
            Modifier
                .padding(10.dp),
            colors = ButtonDefaults.textButtonColors(Color.White)
        ) {
            Text(
                text = "Sacar carta",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun SacarCartaJ2(onDameCartaClick: () -> Unit) {
    Row(Modifier.padding(10.dp)) {
        Button(
            onClick = {
                onDameCartaClick()
            },
            Modifier
                .padding(10.dp),
            colors = ButtonDefaults.textButtonColors(Color.White)
        ) {
            Text(
                text = "Sacar carta",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun Pasar(onPasaClick: () -> Unit) {
    Row(Modifier.padding(10.dp)) {
        Button(
            onClick = {
                onPasaClick()
            },
            Modifier
                .padding(10.dp),
            colors = ButtonDefaults.textButtonColors(Color.White)
        ) {
            Text(
                text = "Paso",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}