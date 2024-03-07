package com.example.tar_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
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
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun TapGesture(
    onNavigateToSwipe: () -> Unit,
    onNavigateToPinch: () -> Unit,
    onNavigateToRotate: () -> Unit,
    onNavigateToLongPress: () -> Unit,
    onNavigateToDragAndDrop: () -> Unit
) {
    var count by rememberSaveable { mutableStateOf(0) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Gestos por @mexboxluis",
                    modifier = Modifier.padding(36.dp),
                    fontWeight = FontWeight.Bold
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Tap") },
                    selected = true,
                    onClick = {
                        Toast.makeText(context, "¡Ya te encuentras aquí!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Swipe") },
                    selected = false,
                    onClick = { onNavigateToSwipe() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Pinch") },
                    selected = false,
                    onClick = { onNavigateToPinch() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Rotate") },
                    selected = false,
                    onClick = { onNavigateToRotate() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Long & Press") },
                    selected = false,
                    onClick = { onNavigateToLongPress() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drag & Drop") },
                    selected = false,
                    onClick = { onNavigateToDragAndDrop() }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Swipe", color = colorResource(id = R.color.Default)) },
                    colors = topAppBarColors(colorResource(id = R.color.Objects)),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu, contentDescription = "",
                                tint = colorResource(id = R.color.Default)
                            )
                        }
                    },


                    )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        count++
                    }
            ) {
                Text(
                    text = "Veces tocadas: $count",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 400.dp),
                    textAlign = TextAlign.Center
                )
            }


        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SwipeGesture(
    onNavigateToTap: () -> Unit,
    onNavigateToPinch: () -> Unit,
    onNavigateToRotate: () -> Unit,
    onNavigateToLongPress: () -> Unit,
    onNavigateToDragAndDrop: () -> Unit
) {
    var backgroundColor by remember { mutableStateOf(Color.White) }
    var text by remember { mutableStateOf(" ") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Gestos por @mexboxluis",
                    modifier = Modifier.padding(36.dp),
                    fontWeight = FontWeight.Bold
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Tap") },
                    selected = false,
                    onClick = { onNavigateToTap() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Swipe") },
                    selected = true,
                    onClick = {
                        Toast.makeText(context, "¡Ya te encuentras aquí!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Pinch") },
                    selected = false,
                    onClick = { onNavigateToPinch() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Rotate") },
                    selected = false,
                    onClick = { onNavigateToRotate() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Long & Press") },
                    selected = false,
                    onClick = { onNavigateToLongPress() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drag & Drop") },
                    selected = false,
                    onClick = { onNavigateToDragAndDrop() }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Swipe", color = colorResource(id = R.color.Default)) },
                    colors = topAppBarColors(colorResource(id = R.color.Objects)),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu, contentDescription = "",
                                tint = colorResource(id = R.color.Default)
                            )
                        }
                    },
                )
            }
        ) {
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
            ) {
                Text(
                    text = text,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PinchGesture(
    onNavigateToTap: () -> Unit,
    onNavigateToSwipe: () -> Unit,
    onNavigateToRotate: () -> Unit,
    onNavigateToLongPress: () -> Unit,
    onNavigateToDragAndDrop: () -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Gestos por @mexboxluis",
                    modifier = Modifier.padding(36.dp),
                    fontWeight = FontWeight.Bold
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Tap") },
                    selected = false,
                    onClick = { onNavigateToTap() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Swipe") },
                    selected = false,
                    onClick = { onNavigateToSwipe() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Pinch") },
                    selected = true,
                    onClick = {
                        Toast.makeText(context, "¡Ya te encuentras aquí!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Rotate") },
                    selected = false,
                    onClick = { onNavigateToRotate() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Long & Press") },
                    selected = false,
                    onClick = { onNavigateToLongPress() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drag & Drop") },
                    selected = false,
                    onClick = { onNavigateToDragAndDrop() }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pinch", color = colorResource(id = R.color.Default)) },
                    colors = topAppBarColors(colorResource(id = R.color.Objects)),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu, contentDescription = "",
                                tint = colorResource(id = R.color.Default)
                            )
                        }
                    },


                    )
            }
        ) {
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
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RotateGesture(
    onNavigateToTap: () -> Unit,
    onNavigateToSwipe: () -> Unit,
    onNavigateToPinch: () -> Unit,
    onNavigateToLongPress: () -> Unit,
    onNavigateToDragAndDrop: () -> Unit
) {
    var rotation by remember { mutableStateOf(0f) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Gestos por @mexboxluis",
                    modifier = Modifier.padding(36.dp),
                    fontWeight = FontWeight.Bold
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Tap") },
                    selected = false,
                    onClick = { onNavigateToTap() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Swipe") },
                    selected = false,
                    onClick = { onNavigateToSwipe() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Pinch") },
                    selected = false,
                    onClick = { onNavigateToPinch() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Rotate") },
                    selected = true,
                    onClick = {
                        Toast.makeText(context, "¡Ya te encuentras aquí!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Long & Press") },
                    selected = false,
                    onClick = { onNavigateToLongPress() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drag & Drop") },
                    selected = false,
                    onClick = { onNavigateToDragAndDrop() }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Rotate", color = colorResource(id = R.color.Default)) },
                    colors = topAppBarColors(colorResource(id = R.color.Objects)),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu, contentDescription = "",
                                tint = colorResource(id = R.color.Default)
                            )
                        }
                    },


                    )
            }
        ) {
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
                            val offsetX =
                                (imageCenterOffset.value * cos(rotation.toRadians())).toInt()
                            val offsetY =
                                (imageCenterOffset.value * sin(rotation.toRadians())).toInt()
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
    }

}

fun Float.toRadians(): Float {
    return (this * kotlin.math.PI / 180).toFloat()
}

fun Float.toDegrees(): Float {
    return (this * 180 / kotlin.math.PI).toFloat()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LongPressGesture(
    onNavigateToTap: () -> Unit,
    onNavigateToSwipe: () -> Unit,
    onNavigateToPinch: () -> Unit,
    onNavigateToRotate: () -> Unit,
    onNavigateToDragAndDrop: () -> Unit
) {
    var message by remember { mutableStateOf("Manten Presionado") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Gestos por @mexboxluis",
                    modifier = Modifier.padding(36.dp),
                    fontWeight = FontWeight.Bold
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Tap") },
                    selected = false,
                    onClick = { onNavigateToTap() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Swipe") },
                    selected = false,
                    onClick = { onNavigateToSwipe() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Pinch") },
                    selected = false,
                    onClick = { onNavigateToPinch() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Rotate") },
                    selected = false,
                    onClick = { onNavigateToRotate() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Long & Press") },
                    selected = true,
                    onClick = {
                        Toast.makeText(context, "¡Ya te encuentras aquí!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drag & Drop") },
                    selected = false,
                    onClick = { onNavigateToDragAndDrop() }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Long & Press", color = colorResource(id = R.color.Default)) },
                    colors = topAppBarColors(colorResource(id = R.color.Objects)),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu, contentDescription = "",
                                tint = colorResource(id = R.color.Default)
                            )
                        }
                    },


                    )
            }
        ) {
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
                                Toast.makeText(
                                    context,
                                    "Le mantuviste presionado bro",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                    }
                )
                Text(
                    text = message,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DragAndDropGesture(
    onNavigateToTap: () -> Unit,
    onNavigateToSwipe: () -> Unit,
    onNavigateToPinch: () -> Unit,
    onNavigateToRotate: () -> Unit,
    onNavigateToLongPress: () -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Gestos por @mexboxluis",
                    modifier = Modifier.padding(36.dp),
                    fontWeight = FontWeight.Bold
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Tap") },
                    selected = false,
                    onClick = { onNavigateToTap() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Swipe") },
                    selected = false,
                    onClick = { onNavigateToSwipe() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Pinch") },
                    selected = false,
                    onClick = { onNavigateToPinch() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Rotate") },
                    selected = false,
                    onClick = { onNavigateToRotate() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Long & Press") },
                    selected = false,
                    onClick = { onNavigateToLongPress() }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drag & Drop") },
                    selected = true,
                    onClick = {
                        Toast.makeText(context, "¡Ya te encuentras aquí!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Drag & Drop", color = colorResource(id = R.color.Default)) },
                    colors = topAppBarColors(colorResource(id = R.color.Objects)),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu, contentDescription = "",
                                tint = colorResource(id = R.color.Default)
                            )
                        }
                    },


                    )
            }
        ) {
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
    }

}

@Preview
@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "tap") {
        composable("tap") {
            TapGesture(
                onNavigateToSwipe = { navController.navigate("swipe") },
                onNavigateToPinch = { navController.navigate("pinch") },
                onNavigateToRotate = { navController.navigate("rotate") },
                onNavigateToLongPress = { navController.navigate("longPress") },
                onNavigateToDragAndDrop = { navController.navigate("dragAndDrop") })
        }
        composable("swipe") {
            SwipeGesture(
                onNavigateToTap = { navController.navigate("tap") },
                onNavigateToPinch = { navController.navigate("pinch") },
                onNavigateToRotate = { navController.navigate("rotate") },
                onNavigateToLongPress = { navController.navigate("longPress") },
                onNavigateToDragAndDrop = { navController.navigate("dragAndDrop") })
        }
        composable("pinch") {
            PinchGesture(
                onNavigateToTap = { navController.navigate("tap") },
                onNavigateToSwipe = { navController.navigate("swipe") },
                onNavigateToRotate = { navController.navigate("rotate") },
                onNavigateToLongPress = { navController.navigate("longPress") },
                onNavigateToDragAndDrop = { navController.navigate("dragAndDrop") })
        }
        composable("rotate") {
            RotateGesture(
                onNavigateToTap = { navController.navigate("tap") },
                onNavigateToSwipe = { navController.navigate("swipe") },
                onNavigateToPinch = { navController.navigate("pinch") },
                onNavigateToLongPress = { navController.navigate("longPress") },
                onNavigateToDragAndDrop = { navController.navigate("dragAndDrop") })
        }
        composable("longPress") {
            LongPressGesture(
                onNavigateToTap = { navController.navigate("tap") },
                onNavigateToSwipe = { navController.navigate("swipe") },
                onNavigateToPinch = { navController.navigate("pinch") },
                onNavigateToRotate = { navController.navigate("rotate") },
                onNavigateToDragAndDrop = { navController.navigate("dragAndDrop") })
        }
        composable("dragAndDrop") {
            DragAndDropGesture(
                onNavigateToTap = { navController.navigate("tap") },
                onNavigateToSwipe = { navController.navigate("swipe") },
                onNavigateToPinch = { navController.navigate("pinch") },
                onNavigateToRotate = { navController.navigate("rotate") },
                onNavigateToLongPress = { navController.navigate("longPress") })
        }
    }
}



