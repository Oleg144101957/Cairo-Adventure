package jp.arby.solo.adventure.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import jp.arby.solo.adventure.R

@Composable
fun Content2() {

    Image(
        painter = painterResource(id = R.drawable.menubg),
        contentDescription = "launcher background",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Buttons()
        PlaneElement()
    }

}