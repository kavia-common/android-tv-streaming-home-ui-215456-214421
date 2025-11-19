package com.example.android_tv_home_screen_ui.ui.components.contentinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.tv.focus.FocusScale

/**
 * PUBLIC_INTERFACE
 * TagChipsRow
 *
 * A generic row for selectable tags with TV focusable chips.
 */
@Composable
fun TagChipsRow(
    tags: List<String>,
    onSelect: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        tags.forEach { tag ->
            FocusScale(cornerRadius = 10.dp) { _, interaction ->
                FilterChip(
                    selected = false,
                    onClick = { onSelect(tag) },
                    label = { Text(tag, style = MaterialTheme.typography.bodyMedium) },
                    interactionSource = interaction,
                    shape = RoundedCornerShape(10.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        labelColor = MaterialTheme.colorScheme.onSurface,
                        selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}
