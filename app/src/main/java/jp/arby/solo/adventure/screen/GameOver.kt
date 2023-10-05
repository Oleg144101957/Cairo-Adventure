package jp.arby.solo.adventure.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.arby.solo.adventure.DreiActivity
import jp.arby.solo.adventure.R
import jp.arby.solo.adventure.ui.theme.CairoBlack
import jp.arby.solo.adventure.ui.theme.CairoFonts
import jp.arby.solo.adventure.ui.theme.CairoWhite

@Composable
fun GameOver() {
    //Game Over Screen
    val activity3 = LocalContext.current as DreiActivity
    val screenH = LocalConfiguration.current.screenHeightDp
    val yPositionForAPlane = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        yPositionForAPlane.animateTo(
            targetValue = screenH.toFloat(),
            animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier
            .background(CairoBlack)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.el1),
            contentDescription = "decoration",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp)
                .offset(y = yPositionForAPlane.value.dp)
        )

        Text(
            text = "Game over",
            color = CairoWhite,
            fontSize = 32.sp,
            fontFamily = CairoFonts.mainFont,
            modifier = Modifier
                .align(Alignment.Center)
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close icon",
            tint = CairoWhite,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(32.dp)
                .clickable {
                    activity3.navigate2()
                }
        )
    }
}