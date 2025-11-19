package com.example.android_tv_home_screen_ui.ui.components.contentinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * PUBLIC_INTERFACE
 * HeroPreview (standalone)
 *
 * A reusable hero preview that can be used by screens to present a large
 * backdrop with a gradient overlay and a title. For this project, the
 * screen-specific version is in ContentInfoScreen; this component is
 * provided for future reuse and modularity.
 */
@Composable
fun HeroPreview(
    title: String,
    backdropRes: Int,
    modifier: Modifier = Modifier,
    bottomContent: @Composable () -> Unit = {}
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = backdropRes),
            contentDescription = "$title backdrop",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            Color(0x00000000),
                            Color(0xD0000000)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(32.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displayLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(12.dp))
            bottomContent()
        }
    }
}
