package com.pmdm.blackjack

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("DiscouragedApi")
@Composable
fun ModoEntrenamiento(){
    lateinit var mano:MutableList<String>
    val context = LocalContext.current
    var start by remember {mutableStateOf(false)}
    var c = 0
    Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(id = R.drawable.fondojuego),
            contentScale = ContentScale.FillHeight
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Column(modifier = Modifier
            .padding(bottom = 10.dp)
            .padding(20.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {

            //Título:
            Row {
                Text(text = "MODO ENTRENAMIENTO", Modifier.padding(30.dp), color = Color.White)
            }
            //Fila para las cartas:
                if (!start){
                    Button(onClick = {start = true;c=0}, modifier = Modifier
                        .padding(10.dp)
                        .padding(bottom = 20.dp)
                        .width(200.dp)
                        .height(80.dp)) { Text(text = "COMENZAR") }
                }else{
                    Muestracarta(context = context, c++)}
            //Fila para los botones de decisión:
            Row {
                if(!start){
                    //Si no se ha pulsado el boton comenzar, los botones de pedir carta y plantarse no existirán
                }else{
                    Button(onClick = {}, modifier = Modifier
                        .padding(10.dp)
                        .padding(bottom = 20.dp)
                        .width(120.dp)
                        .height(60.dp)) { Text(text = "Pedir carta") }
                    Button(onClick = {}, modifier = Modifier
                        .padding(10.dp)
                        .padding(bottom = 20.dp)
                        .width(120.dp)
                        .height(60.dp)) { Text(text = "Plantarse") }
                }
            }
        }
    }
}

/**
 * Función para añadir una LazyRow a la vista, y así va añadiendo cartas progresivamente en la pantalla
 */



@Composable
fun Muestracarta(context: Context, c:Int) {
    // Aquí debes cargar la imagen según el nombre o ID de la carta
    var idimage:String
    Row {
        when (c){
            0->{idimage = "reverso";Numimages(idimage = idimage, context = context)}
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun Numimages(idimage:String,context: Context){
    Image(
        painter = painterResource(id = context.resources.getIdentifier(idimage, "drawable", context.packageName) ),
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
        // Agregar otros modificadores según sea necesario
    )
}
