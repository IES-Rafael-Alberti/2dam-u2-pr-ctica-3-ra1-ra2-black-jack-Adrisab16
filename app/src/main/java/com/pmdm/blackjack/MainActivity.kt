package com.pmdm.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.blackjack.model.Routes
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
                ) {
                    //creamos variable controller para movernos por las pantallas
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = Routes.menuView.route
                    ) {
                        composable(Routes.menuView.route) { MenuView(navController) }
                        composable(Routes.jVsCpu.route) { BlackJackGame() }
                        composable(Routes.jcj.route) { BlackJackGame() }
                        composable(Routes.ClassicMode.route) { BlackJackGame() }
                        composable(Routes.TrainingMode.route) { BlackJackGame() }
                    }
                }
            }
        }
    }
}

