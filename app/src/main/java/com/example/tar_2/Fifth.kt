package com.example.tar_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput


class Fifth : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable

fun MainScreen() {
    var message by remember { mutableStateOf("Manten Presionado") }

    Text(
        text = message,
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    message = "Long Press Detectado"
                }
            )
        }
    )
}

