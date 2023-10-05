package jp.arby.solo.adventure.screen

import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.arby.solo.adventure.R
import jp.arby.solo.adventure.ZweiActivity
import jp.arby.solo.adventure.ui.theme.CairoFonts
import jp.arby.solo.adventure.ui.theme.CairoWhite
import kotlin.system.exitProcess


@Composable
fun BoxScope.Buttons() {

    val activity = LocalContext.current as ZweiActivity
    val animateX = remember { Animatable(-128f) }

    LaunchedEffect(Unit) {
        animateX.animateTo(
            targetValue = 0f, tween(
                durationMillis = 1200,
                delayMillis = 200,
                easing = FastOutSlowInEasing
            )
        )
    }

    Column(
        modifier = Modifier
            .padding(bottom = 32.dp)
            .align(Alignment.BottomCenter)
            .offset(x = animateX.value.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.btnbackgr),
                contentDescription = "button background for launcher",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                    .clickable {
                        activity.navigate3()
                    }
            )

            Text(
                text = "Play",
                color = CairoWhite,
                fontSize = 24.sp,
                fontFamily = CairoFonts.mainFont,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box {
            Image(
                painter = painterResource(id = R.drawable.btnbackgr),
                contentDescription = "button background for launcher",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_MAIN)
                        intent.addCategory(Intent.CATEGORY_HOME)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        activity.startActivity(intent)
                        activity.finish()
                        exitProcess(0)
                    }
            )

            Text(
                text = "Escape",
                color = CairoWhite,
                fontSize = 24.sp,
                fontFamily = CairoFonts.mainFont,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}