package com.pmdm.blackjack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class Carta (nombre: Naipes, palo : Palos, private var puntosMin: Int, private var puntosMax: Int, var idDrawable:Int){
    init { // Cuando se llame a esta clase, se ejecutará directamente esté código
        when {
            nombre.valor == 1 -> {puntosMax = 11;puntosMin = 1}
            nombre.valor in 2 ..11 -> {puntosMax = nombre.valor;puntosMin = nombre.valor}
            nombre.valor > 10 -> {puntosMax = 10;puntosMin = 10}
        }
        idDrawable = nombre.valor + (palo.valor * 13)
    }
}