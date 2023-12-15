package com.pmdm.blackjack.model

/**
 * Rutas que usaremos para controlar los distintos modos de juego de nuestra app
 * @param route ruta a la que nos dirigimos
 */
sealed class Routes(val route: String) {
    object menuView : Routes("MenuView")
    object jVsCpu : Routes("JugadorVsCpu")
    object jcj : Routes("JugadorVsJugador")
    object TrainingMode : Routes("TrainingMode")
    object ClassicMode : Routes("ClassicMode")
}