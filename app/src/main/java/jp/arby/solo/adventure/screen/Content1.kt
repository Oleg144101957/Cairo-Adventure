package jp.arby.solo.adventure.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.arby.solo.adventure.R
import jp.arby.solo.adventure.ui.theme.CairoFonts
import jp.arby.solo.adventure.ui.theme.CairoWhite


@Composable
fun Content1() {

    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1785, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Image(
        painter = painterResource(id = R.drawable.loadbg),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.decorpharaoh),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .rotate(rotation.value)
        )

        Text(
            text = "Your screen is preparing ...",
            color = CairoWhite,
            fontFamily = CairoFonts.mainFont,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
    }
}