package com.example.android_tv_home_screen_ui.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.R
import com.example.android_tv_home_screen_ui.tv.focus.FocusScale
import com.example.android_tv_home_screen_ui.ui.theme.OceanTheme

/**
 * PUBLIC_INTERFACE
 * ContentInfo data model used by ContentInfoScreen mock UI.
 */
data class ContentInfo(
    val id: String,
    val title: String,
    val year: Int,
    val durationMins: Int,
    val rating: String,
    val genres: List<String>,
    val synopsis: String,
    @DrawableRes val backdropRes: Int,
    val badges: List<String>,
    val castSnippet: String = "",
    val director: String = ""
)

/**
 * PUBLIC_INTERFACE
 * ContentInfoScreen
 *
 * Displays a detailed information screen for a selected content item following the
 * Ocean Professional design: large hero/backdrop with gradient, prominent title and
 * subtitle metadata, synopsis, action buttons, and rows for metadata/badges.
 *
 * D-pad accessibility:
 * - Focusable action buttons with clear focus halo and scale
 * - Chips row focusable as a group (assist chips)
 *
 * @param info Mock content info to display
 * @param onBack Invoked when user navigates back (system back will typically handle this)
 * @param onPlay Play callback
 * @param onAdd Add-to-list callback
 * @param onMoreInfo More info callback
 */
@Composable
fun ContentInfoScreen(
    info: ContentInfo,
    onBack: () -> Unit,
    onPlay: () -> Unit,
    onAdd: () -> Unit,
    onMoreInfo: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            HeroPreview(
                info = info,
                onPlay = onPlay,
                onAdd = onAdd,
                onInfo = onMoreInfo,
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 16.dp)
                    .fillMaxWidth()
                    .height(540.dp)
            )
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .fillMaxWidth()
            ) {
                // Title and subtitle
                Text(
                    text = info.title,
                    style = MaterialTheme.typography.displayLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                val subtitle = "${info.year} • ${info.durationMins / 60}h ${info.durationMins % 60}m • ${info.rating}"
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                )
                Spacer(Modifier.height(12.dp))

                // Synopsis
                Text(
                    text = info.synopsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.92f),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(16.dp))

                // Metadata row (genres + director/cast + badges)
                MetadataRow(
                    genres = info.genres,
                    director = info.director,
                    castSnippet = info.castSnippet,
                    badges = info.badges
                )
                Spacer(Modifier.height(24.dp))
            }
            Spacer(Modifier.height(36.dp))
        }
    }
}

/**
 * Large hero/backdrop with gradient overlay and action buttons.
 */
@Composable
private fun HeroPreview(
    info: ContentInfo,
    onPlay: () -> Unit,
    onAdd: () -> Unit,
    onInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 10.dp,
        shadowElevation = 10.dp
    ) {
        Box {
            Image(
                painter = painterResource(id = info.backdropRes),
                contentDescription = "${info.title} backdrop",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Ocean gradient hint: top-to-bottom orange-500/20 to black overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.20f),
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
                    .fillMaxWidth(0.6f),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = info.title,
                    style = MaterialTheme.typography.displayLarge
                )
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.focusGroup(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FocusScale(cornerRadius = 14.dp) { _, interaction ->
                        Button(
                            onClick = onPlay,
                            interactionSource = interaction,
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("Play", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                    FocusScale(cornerRadius = 14.dp) { _, interaction ->
                        Button(
                            onClick = onAdd,
                            interactionSource = interaction,
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add to List",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("Add to List", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                    FocusScale(cornerRadius = 14.dp) { _, interaction ->
                        Button(
                            onClick = onInfo,
                            interactionSource = interaction,
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
                                contentColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "More Info",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("More Info", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Row containing chips/tags and text metadata per Ocean theme.
 */
@Composable
private fun MetadataRow(
    genres: List<String>,
    director: String,
    castSnippet: String,
    badges: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Genre chips row
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.focusGroup()
        ) {
            genres.take(6).forEach { g ->
                FocusScale(cornerRadius = 10.dp) { _, interaction ->
                    AssistChip(
                        onClick = { /* no-op mock */ },
                        label = { Text(g, style = MaterialTheme.typography.bodyMedium) },
                        interactionSource = interaction,
                        shape = RoundedCornerShape(10.dp),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            labelColor = MaterialTheme.colorScheme.onSurface
                        ),
                        // Note: AssistChip uses its own ChipBorder type; omit explicit border to avoid type mismatch
                        // and rely on container/label colors for subtle outline per Ocean theme.
                        border = null
                    )
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        // Director / Cast
        if (director.isNotBlank()) {
            Text(
                text = "Director: $director",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(6.dp))
        }
        if (castSnippet.isNotBlank()) {
            Text(
                text = "Cast: $castSnippet",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(12.dp))
        }
        // Badges row (UHD, 5.1, etc.)
        if (badges.isNotEmpty()) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                badges.forEach { b ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = b,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

/**
 * PUBLIC_INTERFACE
 * ContentInfo mock provider for previews or navigation argument placeholder.
 */
object ContentInfoMockProvider {
    fun sample(): ContentInfo = ContentInfo(
        id = "mock-1",
        title = "Gladiator II",
        year = 2024,
        durationMins = 148,
        rating = "+16",
        genres = listOf("Action", "Adventure", "Drama"),
        synopsis = "Lucius must face the arena after his home is conquered by tyrannical emperors. With Rome's fate at stake, he must look to the past to restore its glory.",
        backdropRes = R.drawable.figure_poster_gladiator_ii,
        badges = listOf("UHD", "5.1"),
        castSnippet = "Paul Mescal, Pedro Pascal, Denzel Washington",
        director = "Ridley Scott"
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun PreviewContentInfo() {
    OceanTheme {
        ContentInfoScreen(
            info = ContentInfoMockProvider.sample(),
            onBack = {},
            onPlay = {},
            onAdd = {},
            onMoreInfo = {},
        )
    }
}
