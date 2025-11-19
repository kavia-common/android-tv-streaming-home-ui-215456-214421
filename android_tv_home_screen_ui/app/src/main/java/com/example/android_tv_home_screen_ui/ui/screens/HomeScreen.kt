package com.example.android_tv_home_screen_ui.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.data.mock.PosterItem
import com.example.android_tv_home_screen_ui.data.mock.RowData
import com.example.android_tv_home_screen_ui.ui.components.ContentRow
import com.example.android_tv_home_screen_ui.ui.components.PreviewSection
import com.example.android_tv_home_screen_ui.ui.components.TopNavBar

/**
 * PUBLIC_INTERFACE
 * HomeScreen
 *
 * Arranges the Ocean Professional TV UI with navigation hook to Content Info.
 *
 * @param rows Content rows to display
 * @param selectedItem Currently selected item for hero
 * @param onSelectedChange Callback when selection updates from row focus
 * @param onOpenContentInfo Navigate to ContentInfo screen when Info selected
 */
@Composable
fun HomeScreen(
    rows: List<RowData>,
    selectedItem: PosterItem,
    onSelectedChange: (PosterItem) -> Unit,
    onOpenContentInfo: () -> Unit
) {
    val navItems = listOf("Home", "Movies", "Series", "My List")
    val selectedNav = remember { mutableIntStateOf(0) }

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            TopNavBar(
                items = navItems,
                selectedIndex = selectedNav.intValue,
                onSelect = { selectedNav.intValue = it }
            )
            PreviewSection(
                item = selectedItem,
                onPlay = { /* visual feedback handled in component; mock */ },
                onAdd = { /* mock */ },
                onInfo = { onOpenContentInfo() }
            )
            Spacer(Modifier.height(8.dp))
            rows.forEach { row ->
                ContentRow(
                    title = row.title,
                    items = row.items,
                    onFocusItem = { onSelectedChange(it) }
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
