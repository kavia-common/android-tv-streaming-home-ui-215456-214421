package com.example.android_tv_home_screen_ui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.tv.focus.FocusScale

/**
 * PUBLIC_INTERFACE
 * TopNavBar
 *
 * A fixed top navigation bar with bold Ocean styling.
 *
 * @param items Labels to display
 * @param selectedIndex Currently selected index
 * @param onSelect Callback when selection changes
 */
@Composable
fun TopNavBar(
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 48.dp, vertical = 12.dp)
            .navigationBarsPadding()
            .height(88.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Brand block
        Spacer(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                        )
                    )
                )
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "StreamTV",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.width(24.dp))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0x991F2937))
                .focusGroup()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, label ->
                val isSelected = index == selectedIndex
                FocusScale(cornerRadius = 12.dp) { _, interactionSource ->
                    Button(
                        onClick = { onSelect(index) },
                        interactionSource = interactionSource,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0x2EF97316) else Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))

        // Profile avatar
        Spacer(
            modifier = Modifier
                .width(56.dp)
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0x33FFFFFF))
        )
    }
}
