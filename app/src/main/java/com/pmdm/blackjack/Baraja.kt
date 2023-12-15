package com.pmdm.blackjack

/**
 * Clase baraja, donde podremos crear una baraja nueva, podremos barajar las cartas y mostrarlas:
 */
class Baraja {
    companion object{ // Usamos el Companion Object para no tener que inicializar las variables directamente en el constructor primario
        private val cardlist = arrayListOf<Carta>()
        private var size = 0
        init {newDeck();shuffle()}

        /**
         * Crea la baraja de cartas usando las clases creadas (naipes y palos):
         */
        fun newDeck(){
            cardlist.clear() // Eliminamos lo que pueda haber en la lista de cartas
            val listaFiguras = listOf("c11","c12","c13","c24","c25","c26","c37","c38","c39","c50","c51","c52")
            var idCarta = 1
            for (palo in Palos.values()) {
                for (numero in Naipes.values()) {
                    val newCarta = Carta(numero, palo, numero.ordinal + 1, numero.ordinal + 1, idCarta)
                    //Definimos a 10 todos los valores figuras (PARA BLACK JACK)
                    if ("c$idCarta" in listaFiguras) newCarta.puntosMin = 10
                    //Definimos el doble valor de AS
                    if (numero.name == "AS") newCarta.puntosMax = 11
                    cardlist.add(newCarta)
                    idCarta++
                }
            }
            size = cardlist.size // Actualizamos el tamaño de la lista
        }

        /**
         * Esta función barajará las cartas (Desordenará las cartas de la lista de cartas de la baraja):
         */
        fun shuffle(){cardlist.shuffle()}

        /**
         * Esta función mostrará una carta de la baraja si la baraja tiene como mínimo 1 carta:
         */
        fun cogerCarta() = cardlist.removeLast()
    }
}