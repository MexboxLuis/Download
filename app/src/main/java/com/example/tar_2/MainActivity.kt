package com.example.tar_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
@Preview
fun TapGestureDemo() {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                count++
            }
    ) {
        Text(
            text = "Tocame!",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()

        )
    }

    Text(
        text = "Veces tocadas: $count",
        modifier = Modifier.fillMaxSize().padding(top = 400.dp),
        textAlign = TextAlign.Center
    )
}
@Composable
fun MyApp() {
    TapGestureDemo()
}
