package jp.arby.solo.adventure.screen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.arby.solo.adventure.DreiActivity
import jp.arby.solo.adventure.R
import jp.arby.solo.adventure.domain.Point
import jp.arby.solo.adventure.ui.theme.CairoBlack
import jp.arby.solo.adventure.ui.theme.CairoFonts
import jp.arby.solo.adventure.ui.theme.CairoRed
import jp.arby.solo.adventure.ui.theme.CairoWhite
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Content3() {
    //playcontent

    val activity3 = LocalContext.current as DreiActivity
    val screenW = LocalConfiguration.current.screenWidthDp
    val screenH = LocalConfiguration.current.screenHeightDp
    val currentDensity = LocalDensity.current

    //Game scores
    val points = remember { mutableStateOf(0) }
    val seconds = remember { mutableStateOf(15) }

    LaunchedEffect(Unit) {
        while (seconds.value > 0) {
            delay(1000)
            seconds.value -= 1
        }
    }

    val listOfTargets = remember { mutableStateListOf<Point>() }
    val listOfRects = remember { mutableStateListOf<Rect>() }

    val ballRect = remember { mutableStateOf(Rect.Zero) }
    val random = remember { Random(System.currentTimeMillis()) }


    LaunchedEffect(Unit) {
        repeat(7) {
            listOfTargets.add(
                Point(
                    positionX = random.nextInt(0, screenW - 50).dp,
                    positionY = random.nextInt(256, screenH - 50).dp
                )
            )
        }
    }


    val isTouchedBoarders = remember { mutableStateOf(false) }
    val isGameOver = remember { mutableStateOf(false) }
    val isWin = remember { mutableStateOf(false) }

    if (points.value == 7) {
        isWin.value = true
    }

    val ballOffsetX = remember { mutableStateOf(0f) }
    val ballOffsetY = remember { mutableStateOf(0f) }

    val sensorManager =
        LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    val gyroscopeEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {

            // You can use these values to move the ball
            val xRotation = event.values[1]
            val yRotation = event.values[0]

            ballOffsetX.value += xRotation * 30f
            ballOffsetY.value += yRotation * 30f

            //check collisions

            if (listOfTargets.size > 0 && listOfRects.size > 0) {
                for (i in 0 until listOfTargets.size) {
                    if (ballRect.value.overlaps(listOfRects[i])) {
                        if (listOfTargets[i].isVisible) {
                            points.value += 1
                            listOfTargets[i] = listOfTargets[i].copy(isVisible = false)
                        }
                    }
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // Handle accuracy changes if needed
        }
    }




    DisposableEffect(Unit) {
        sensorManager.registerListener(
            gyroscopeEventListener,
            gyroscopeSensor,
            SensorManager.SENSOR_DELAY_GAME
        )

        onDispose {
            sensorManager.unregisterListener(gyroscopeEventListener)
        }
    }

    LaunchedEffect(isTouchedBoarders.value) {
        //game over section
        delay(1400)
        if (isTouchedBoarders.value && !isGameOver.value && !isWin.value) {
            isGameOver.value = true
        }
    }



    Image(
        painter = painterResource(id = R.drawable.menubg),
        contentDescription = "play screen background",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(5.dp, if (isTouchedBoarders.value) CairoRed else CairoBlack.copy(alpha = 0.3f))
    ) {

        listOfTargets.forEach {
            if (it.isVisible) {
                Image(
                    painter = painterResource(id = R.drawable.ca2),
                    contentDescription = "targets",
                    modifier = Modifier
                        .offset(x = it.positionX, y = it.positionY)
                        .size(it.size)
                        .background(Color.Green)
                        .onGloballyPositioned { elem ->
                            listOfRects.add(elem.boundsInParent())
                        }
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ca5),
            contentDescription = "movable ball",
            modifier = Modifier
                .align(Alignment.Center)
                .size(32.dp)
                .offset(x = ballOffsetX.value.dp, y = ballOffsetY.value.dp)
                .onGloballyPositioned {
                    with(currentDensity) {
                        if (it.positionInRoot().x.toDp() in 0.dp..screenW.dp - it.size.width.toDp() &&
                            it.positionInRoot().y.toDp() in 0.dp..screenH.dp - it.size.height.toDp()
                        ) {
                            // in bounds
                        } else {
                            //out of the screen
                            if (!isWin.value && !isGameOver.value) {
                                isTouchedBoarders.value = true
                            }
                        }
                    }

                    ballRect.value = it.boundsInParent()
                }
        )

        ScoreFun(points = points.value)

        if (isTouchedBoarders.value && !isGameOver.value && !!isWin.value) {
            Text(
                text = "Out of bounds",
                fontFamily = CairoFonts.mainFont,
                color = CairoWhite,
                fontSize = 32.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }


        if (!isWin.value && !isGameOver.value) {
            Timer(seconds.value)
        }


        if (isGameOver.value && !isWin.value) {
            GameOver()
        }

        if (isWin.value && !isGameOver.value) {
            WinScreen()
        }

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close icon",
            tint = CairoWhite,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(32.dp)
                .background(CairoBlack)
                .clickable {
                    activity3.navigate2()
                }
        )
    }
}