package com.example.galeria_arte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeria_arte.ui.theme.GaleriaArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaArteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    var currentArtwork by remember { mutableStateOf(1) }
    val maxArtworks = 3

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {


        //  Imagen de la obra
        Image(
            painter = painterResource(id = when (currentArtwork) {
                    1 -> R.drawable.obra_1
                    2 -> R.drawable.obra_2
                    else -> R.drawable.obra_3
                }),
            contentDescription = "Obra de arte actual",
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp)
        )

        // Textos
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,

        ) {
            Text(
                text = when (currentArtwork) {
                    1 -> stringResource(R.string.obra_1_titulo)
                    2 -> stringResource(R.string.obra_2_titulo)
                    else -> stringResource(R.string.obra_3_titulo)
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = when (currentArtwork) {
                    1 -> stringResource(R.string.obra_1_autor)
                    2 -> stringResource(R.string.obra_2_autor)
                    else -> stringResource(R.string.obra_3_autor)
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        // Botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Boton antes
            Button(
                onClick = {
                    if (currentArtwork > 1) {
                        currentArtwork--
                    } else {
                        currentArtwork = maxArtworks
                    }
                }
            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    if (currentArtwork < maxArtworks) {
                        currentArtwork++
                    } else {
                        currentArtwork = 1
                    }
                }
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    GaleriaArteTheme {
        ArtSpaceApp()
    }
}