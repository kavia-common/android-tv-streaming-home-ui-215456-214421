package com.example.android_tv_home_screen_ui.ui.components.contentinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.tv.focus.FocusScale

/**
 * PUBLIC_INTERFACE
 * MetadataRow (standalone)
 *
 * Displays genres as focusable chips and text rows for director/cast.
 */
@Composable
fun MetadataRow(
    genres: List<String>,
    director: String,
    castSnippet: String,
    badges: List<String>
) {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            genres.forEach { g ->
                FocusScale(cornerRadius = 10.dp) { _, interaction ->
                    AssistChip(
                        onClick = { /* no-op for mock */ },
                        label = { Text(g, style = MaterialTheme.typography.bodyMedium) },
                        interactionSource = interaction,
                        shape = RoundedCornerShape(10.dp),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            labelColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        if (director.isNotBlank()) {
            Text("Director: $director", style = MaterialTheme.typography.bodyLarge)
        }
        if (castSnippet.isNotBlank()) {
            Spacer(Modifier.height(6.dp))
            Text("Cast: $castSnippet", style = MaterialTheme.typography.bodyLarge)
        }
        if (badges.isNotEmpty()) {
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                badges.forEach { b ->
                    Surface(
                        tonalElevation = 2.dp,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            b,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .height(32.dp)
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}
