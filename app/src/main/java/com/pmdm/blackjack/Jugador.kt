package com.pmdm.blackjack

import android.content.Context
import android.widget.Toast

class Jugador {
    lateinit var nombre:String
    lateinit var mano:Baraja
    var fichas:Int = 0
    private val deck = Baraja
    fun pedircarta(context: Context): String {
        val card = deck.cogerCarta() // Variable para mostrar cartas de la lista de cartas
        // Si no hay cartas en la baraja, aparecerá el mensaje toast:
        return if (card == null) {Toast.makeText(context,"Ups, ya no hay más cartas para mostrar", Toast.LENGTH_SHORT).show(); "reverso"} // Mensaje toast para cuando nos quedamos sin cartas en la baraja:
        else return "c${card.idDrawable}" // Si hay cartas en la baraja, las mostrará
    }

    fun plantarse(){}

    fun doblar(){}

    fun dividir(){}
}