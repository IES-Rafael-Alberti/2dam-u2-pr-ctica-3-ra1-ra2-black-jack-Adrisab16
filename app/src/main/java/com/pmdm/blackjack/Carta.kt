package com.pmdm.blackjack

class Carta (var nombre: Naipes, var palo: Palos, var puntosMin: Int = 0,
             var puntosMax: Int = 0, var idDrawable: Int = 0){
    init { // Cuando se llame a esta clase, se ejecutará directamente esté código
        when {
            nombre.valor == 1 -> {puntosMax = 11;puntosMin = 1}
            nombre.valor in 2 ..11 -> {puntosMax = nombre.valor;puntosMin = nombre.valor}
            nombre.valor > 10 -> {puntosMax = 10;puntosMin = 10}
        }
        idDrawable = nombre.valor + (palo.valor * 13)
    }
    override fun toString(): String {
        return "Carta(nombre=$nombre, palo=$palo, puntosMin=$puntosMin, puntosMax=$puntosMax, idDrawable=$idDrawable)"}
}