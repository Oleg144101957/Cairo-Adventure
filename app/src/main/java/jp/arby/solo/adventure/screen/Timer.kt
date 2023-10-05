package jp.arby.solo.adventure.screen

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.arby.solo.adventure.ui.theme.CairoFonts
import jp.arby.solo.adventure.ui.theme.CairoRed
import jp.arby.solo.adventure.ui.theme.CairoWhite

@Composable
fun BoxScope.Timer(seconds: Int) {
    Text(
        text = "Time: $seconds",
        color = if (seconds < 7) CairoRed else CairoWhite,
        fontFamily = CairoFonts.mainFont,
        fontSize = 16.sp,
        modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(4.dp)
    )

    if (seconds == 0) {
        GameOver()
    }
}