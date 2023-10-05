package jp.arby.solo.adventure.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.arby.solo.adventure.R
import jp.arby.solo.adventure.ui.theme.CairoFonts
import jp.arby.solo.adventure.ui.theme.CairoWhite

@Composable
fun BoxScope.ScoreFun(points: Int) {

    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .align(Alignment.TopCenter)
    ) {

        Image(
            painter = painterResource(id = R.drawable.btnbackgr),
            contentDescription = "points background",
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(0.9f)
        )

        Text(
            text = "Score is: $points",
            color = CairoWhite,
            fontFamily = CairoFonts.mainFont,
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}