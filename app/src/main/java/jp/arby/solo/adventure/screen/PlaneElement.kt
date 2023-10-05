package jp.arby.solo.adventure.screen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import jp.arby.solo.adventure.R


@Composable
fun BoxScope.PlaneElement() {

    val movableOffsetX = remember { mutableStateOf(0f) }
    val movableOffsetY = remember { mutableStateOf(0f) }

    val sensorManager =
        LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    val gyroscopeEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {

            // You can use these values to move the ball
            val xRotation = event.values[1]
            val yRotation = event.values[0]

            movableOffsetX.value += xRotation
            movableOffsetY.value += yRotation


            // Update the position of the ball based on the rotation values
            // Modify your ball position logic here

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

    Image(
        painter = painterResource(id = R.drawable.ca4),
        contentDescription = "plane element",
        modifier = Modifier
            .align(Alignment.Center)
            .offset(x = movableOffsetX.value.dp, y = movableOffsetY.value.dp)
    )

}