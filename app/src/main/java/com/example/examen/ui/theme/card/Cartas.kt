package com.example.examen.ui.theme.card


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.examen.R
import kotlinx.coroutines.launch
import androidx.compose.material3.ModalBottomSheet as ModalBottomSheet

val sheetbody = "contenido de la carta"
val body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ad vestibulum nunc."

val cartas = listOf(
    Carta(R.drawable.planeta1, body, sheetbody),
    Carta(R.drawable.planeta2, body, sheetbody),
    Carta(R.drawable.planeta3, body, sheetbody),
)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartasScreen() {
    Scaffold (topBar = { CartasAppBar() }) { innerPadding ->
        Cartas(modifier = Modifier.padding(innerPadding), cartas)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartasAppBar(){
    TopAppBar(title = { Text(text = "Yeppaa!") },
        actions = {IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "lupa")
        };
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "los 3 puntos")
            }}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cartas(modifier: Modifier, cartas: List<Carta>) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    LazyColumn{
        items(items = cartas) { cadaCarta ->
            Card (modifier = modifier
                .fillMaxWidth()
                .padding(30.dp)) {
                Row {
                    Image(
                        painter = painterResource(id = cadaCarta.imagePath),
                        contentDescription = "foto de un planeta")
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "los 3 puntos")
                    }
                }
                Text(text = cadaCarta.body)
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Salir")
                    }
                    Text(cadaCarta.sheetbody)
                }
            }
        }
    }
}