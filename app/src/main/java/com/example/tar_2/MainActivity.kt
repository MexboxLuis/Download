package com.example.tar_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.math.cos
import kotlin.math.sin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable

fun TapGesture(onNavigateToSwipe: () -> Unit) {
    var count by rememberSaveable { mutableStateOf(0) }

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
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 400.dp),
        textAlign = TextAlign.Center
    )
    Button(onClick = { onNavigateToSwipe() }) {
        Text("Go to Swipe")
    }
}

@Composable
fun SwipeGesture(onNavigateToPinch: () -> Unit) {
    var backgroundColor by remember { mutableStateOf(Color.White) }
    var text by remember { mutableStateOf(" ")  }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    if (change.position.x > change.previousPosition.x) {
                        backgroundColor = Color.Red
                    } else if (change.position.x < change.previousPosition.x) {
                        backgroundColor = Color.Blue
                    } else if (change.position.y > change.previousPosition.y) {
                        text = "Abajo"
                    } else if (change.position.y < change.previousPosition.y) {
                        text = "Arriba"
                    }
                }
            }
    ){
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }

    Button(onClick = { onNavigateToPinch() }) {
        Text("Go to Pinch")
    }
}

@Composable
fun PinchGesture(onNavigateToRotate: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sasha),
            contentDescription = "Objeto",
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                )
        )
    }
    Button(onClick = { onNavigateToRotate() }) {
        Text("Go to Rotate")
    }
}


@Composable
fun RotateGesture(onNavigateToLongPress: () -> Unit) {
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
        val imageSize = 150.dp
        val imageCenterOffset = imageSize / 2

        Image(
            painter = painterResource(id = R.drawable.sasha),
            contentDescription = "Objeto",
            modifier = Modifier
                .size(imageSize)
                .offset {
                    val offsetX = (imageCenterOffset.value * cos(rotation.toRadians())).toInt()
                    val offsetY = (imageCenterOffset.value * sin(rotation.toRadians())).toInt()
                    IntOffset(offsetX, offsetY)
                }
                .rotate(rotation)
                .background(Color.Red)
        )
    }
    Button(onClick = { onNavigateToLongPress() }) {
        Text("Go to Long Press")
    }
}
fun Float.toRadians(): Float {
    return (this * kotlin.math.PI / 180).toFloat()
}

fun Float.toDegrees(): Float {
    return (this * 180 / kotlin.math.PI).toFloat()
}

@Composable
fun LongPress(onNavigateToDragAndDrop: () -> Unit) {
    var message by remember { mutableStateOf("Manten Presionado") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sasha),
            contentDescription = "Objeto",
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        message = "Long Press Detectado"
                    }
                )
            }
        )
        Text(
            text = message,
            modifier = Modifier.padding(top = 8.dp)
        )
    }


    Button(onClick = { onNavigateToDragAndDrop() }) {
        Text("Go to Drag and drop")
    }
}


@Composable
fun DragAndDropGesture(onNavigateToTap: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    offsetX += pan.x
                    offsetY += pan.y
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sasha),
            contentDescription = "Objeto",
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
        )
    }
    Button(onClick = { onNavigateToTap() }) {
        Text("Go to Tap")
    }
}


@Preview
@Composable
fun MyApp() {

    val navController = rememberNavController()
    NavHost(navController, startDestination = "tap") {
        composable("tap") { TapGesture(onNavigateToSwipe = { navController.navigate("swipe") }) }
        composable("swipe") { SwipeGesture(onNavigateToPinch = { navController.navigate("pinch") }) }
        composable("pinch") { PinchGesture(onNavigateToRotate = { navController.navigate("rotate") }) }
        composable("rotate") { RotateGesture(onNavigateToLongPress = { navController.navigate("longPress") }) }
        composable("longPress") { LongPress(onNavigateToDragAndDrop = { navController.navigate("dragAndDrop") }) }
        composable("dragAndDrop") { DragAndDropGesture(onNavigateToTap = { navController.navigate("tap") }) }
    }
}


