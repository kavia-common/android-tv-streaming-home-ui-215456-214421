package com.example.android_tv_home_screen_ui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material3.Text
import com.example.android_tv_home_screen_ui.tv.focus.FocusScale
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.android_tv_home_screen_ui.R

/**
 * PUBLIC_INTERFACE
 * PosterCard
 *
 * Focus-scalable poster card for a content item, with label overlay and shine hint.
 *
 * @param title Label text
 * @param imageDrawable Poster/backdrop drawable resource
 * @param onClick Invoked on DPAD center/Enter
 * @param modifier Modifier
 */
@Composable
fun PosterCard(
    title: String,
    imageDrawable: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var imageError by remember { mutableStateOf(false) }
    val fallbackRes = R.drawable.figure_poster_fallback
    FocusScale(cornerRadius = 12.dp) { _, interactionSource ->
        Box(
            modifier = modifier
                .width(320.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            AsyncImage(
                model = if (!imageError) imageDrawable else fallbackRes,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
                onError = { imageError = true }
            )
            // Gradient label background
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .height(40.dp)
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                androidx.compose.ui.graphics.Color.Transparent,
                                androidx.compose.ui.graphics.Color(0x66000000)
                            )
                        )
                    )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clip(RoundedCornerShape(10.dp))
                    .background(androidx.compose.ui.graphics.Color(0x66000000))
                    .then(Modifier),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
