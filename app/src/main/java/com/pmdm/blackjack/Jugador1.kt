package com.pmdm.blackjack

import android.content.Context
import android.widget.Toast

//No se va aa usar por ahora
class Jugador1 {
    lateinit var nombre:String
    lateinit var mano:MutableList<String>
    var fichas:Int = 0
    private val deck = Baraja
    fun iniciojuego(){
        val card1 = deck.cogerCarta()
        val card2 = deck.cogerCarta()
        if (card1 != null && card2 != null) {
            mano.add("c${card1.idDrawable}")
            mano.add("c${card2.idDrawable}")
        }
    }
    fun pedircarta(context: Context) {
        val card = deck.cogerCarta() // Variable para mostrar cartas de la lista de cartas
        if (card != null) {
            mano.add("c${card.idDrawable}")
        }else{Toast.makeText(context,"Ups, ya no hay más cartas para mostrar", Toast.LENGTH_SHORT).show()} // Mensaje toast para cuando nos quedamos sin cartas en la baraja
    }

    fun plantarse(){}

    fun doblar(){}

    fun dividir(){}
}