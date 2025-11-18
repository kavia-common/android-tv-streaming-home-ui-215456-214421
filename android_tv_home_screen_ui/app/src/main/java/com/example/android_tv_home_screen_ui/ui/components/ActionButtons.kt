package com.example.android_tv_home_screen_ui.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.tv.focus.FocusScale

/**
 * PUBLIC_INTERFACE
 * ActionButtons
 *
 * Row of Play, Add to List, and Info buttons with Ocean accents and focus scaling.
 */
@Composable
fun ActionButtons(
    onPlay: () -> Unit,
    onAdd: () -> Unit,
    onInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FocusScale(cornerRadius = 12.dp) { _, interactionSource ->
            Button(
                onClick = onPlay,
                interactionSource = interactionSource,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Play", style = MaterialTheme.typography.labelLarge)
            }
        }
        FocusScale(cornerRadius = 12.dp) { _, interactionSource ->
            Button(
                onClick = onAdd,
                interactionSource = interactionSource,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text("Add to List", style = MaterialTheme.typography.labelLarge)
            }
        }
        FocusScale(cornerRadius = 12.dp) { _, interactionSource ->
            Button(
                onClick = onInfo,
                interactionSource = interactionSource,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text("Info", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
