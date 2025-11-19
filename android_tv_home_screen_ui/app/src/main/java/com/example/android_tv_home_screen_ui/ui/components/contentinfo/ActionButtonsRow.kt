package com.example.android_tv_home_screen_ui.ui.components.contentinfo

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.tv.focus.FocusScale

/**
 * PUBLIC_INTERFACE
 * ActionButtonsRow
 *
 * Focusable buttons: Play, Add to List, More Info, using Ocean theme colors and rounded corners.
 */
@Composable
fun ActionButtonsRow(
    onPlay: () -> Unit,
    onAdd: () -> Unit,
    onInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.focusGroup(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                Icon(Icons.Default.PlayArrow, contentDescription = "Play")
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
                Icon(Icons.Default.Add, contentDescription = "Add")
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
                Icon(Icons.Default.Info, contentDescription = "Info")
                Spacer(Modifier.width(8.dp))
                Text("More Info", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
