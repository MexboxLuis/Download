package com.example.tar_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.tar_2.ui.theme.TAR_2Theme
import kotlin.math.atan2


class FourthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RotateGestureDemo()
        }
    }
}


@Composable
fun RotateGestureDemo() {
    var rotation by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, _, _, gestureRotation ->
                    rotation += gestureRotation.toDegrees()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset {
                    IntOffset(
                        ((100.dp.value / 2) * kotlin.math.cos(rotation.toRadians()).toFloat()).toInt(),
                        ((100.dp.value / 2) * kotlin.math.sin(rotation.toRadians()).toFloat()).toInt()
                    )
                }
                .background(Color.Red)
        )
    }
}

fun Float.toRadians(): Float {
    return (this * kotlin.math.PI / 180).toFloat()
}

fun Float.toDegrees(): Float {
    return (this * 180 / kotlin.math.PI).toFloat()
}

