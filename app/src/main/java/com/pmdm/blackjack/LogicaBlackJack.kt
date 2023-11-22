package com.pmdm.blackjack

import androidx.compose.runtime.Composable


@Composable
fun PedirCarta(mano: MutableList<String>){
    val carta = Baraja.cogerCarta()
    if (carta != null) {
        mano.add(carta.idDrawable.toString())
    }
}

@Composable
fun Plantarse(){}