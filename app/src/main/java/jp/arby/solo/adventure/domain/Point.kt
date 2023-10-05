package jp.arby.solo.adventure.domain

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Point(
    val positionX: Dp,
    val positionY: Dp,
    val isVisible: Boolean = true,
    val size: Dp = 32.dp

)
