package com.example.android_tv_home_screen_ui.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.data.mock.PosterItem
import com.example.android_tv_home_screen_ui.R

/**
 * PUBLIC_INTERFACE
 * PreviewSection
 *
 * Large hero banner with background image, gradient overlay, title/description and action buttons.
 *
 * @param item Currently selected item
 * @param onPlay Play button action
 * @param onAdd Add to list action
 * @param onInfo Info action
 */
@Composable
fun PreviewSection(
    item: PosterItem,
    onPlay: () -> Unit,
    onAdd: () -> Unit,
    onInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    val painter: Painter = painterResource(id = item.backdropRes)
    Surface(
        modifier = modifier
            .padding(horizontal = 48.dp, vertical = 12.dp)
            .fillMaxWidth()
            .height(520.dp),
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 10.dp,
        shadowElevation = 10.dp
    ) {
        Box {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                androidx.compose.ui.graphics.Color(0xC0000000),
                                androidx.compose.ui.graphics.Color(0x40000000),
                                androidx.compose.ui.graphics.Color(0xA6000000)
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(32.dp)
                    .fillMaxWidth(0.6f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = item.title, style = MaterialTheme.typography.displayLarge)
                Spacer(Modifier.height(12.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                )
                Spacer(Modifier.height(16.dp))
                ActionButtons(
                    onPlay = onPlay,
                    onAdd = onAdd,
                    onInfo = onInfo
                )
            }
        }
    }
}
