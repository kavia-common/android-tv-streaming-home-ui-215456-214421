package com.example.android_tv_home_screen_ui.tv.focus

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * PUBLIC_INTERFACE
 * FocusScale
 *
 * Wrap composable content with a scale animation and focus halo when focused.
 *
 * @param modifier Additional modifier
 * @param focusedScale Scale value when focused (1.06 - 1.1 recommended)
 * @param cornerRadius Corner radius for halo
 * @param content Content
 */
@Composable
fun FocusScale(
    modifier: Modifier = Modifier,
    focusedScale: Float = 1.08f,
    cornerRadius: Dp = 12.dp,
    content: @Composable (isFocused: Boolean, interactionSource: MutableInteractionSource) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocusedState = interactionSource.collectIsFocusedAsState()

    val scale = animateFloatAsState(if (isFocusedState.value) focusedScale else 1f, label = "focus-scale")
    val border = if (isFocusedState.value) BorderStroke(
        2.dp,
        Brush.radialGradient(
            listOf(
                Color.White.copy(alpha = 0.95f),
                MaterialTheme.colorScheme.primary.copy(alpha = 0.55f)
            )
        )
    ) else null

    Surface(
        modifier = modifier
            .scale(scale.value)
            .focusable(interactionSource = interactionSource),
        shape = RoundedCornerShape(cornerRadius),
        color = Color.Transparent,
        tonalElevation = if (isFocusedState.value) 6.dp else 0.dp,
        shadowElevation = if (isFocusedState.value) 12.dp else 0.dp,
        border = border
    ) {
        content(isFocusedState.value, interactionSource)
    }
}
