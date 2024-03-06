package com.example.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.examen.ui.theme.ExamenTheme
import com.example.examen.ui.theme.login.LoginScreen
import com.example.examen.ui.theme.card.CartasScreen
import com.example.examen.ui.theme.viewModel.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var cambiarPantalla = rememberSaveable { mutableStateOf(true) }
                    if (cambiarPantalla.value){
                        LoginScreen(viewModel(), Continuar = {cambiarPantalla.value = false})
                    }else{
                        CartasScreen()
                    }

                }
            }
        }
    }
}
