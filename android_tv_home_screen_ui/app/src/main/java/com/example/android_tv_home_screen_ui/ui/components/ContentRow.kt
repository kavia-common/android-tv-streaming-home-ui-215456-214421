package com.example.android_tv_home_screen_ui.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_tv_home_screen_ui.data.mock.PosterItem

/**
 * PUBLIC_INTERFACE
 * ContentRow
 *
 * A horizontally scrolling row of PosterCard items with TV-friendly spacing and focus.
 *
 * @param title Row title
 * @param items List of poster items
 * @param onFocusItem Called when a card gains focus, used to update hero
 */
@Composable
fun ContentRow(
    title: String,
    items: List<PosterItem>,
    onFocusItem: (PosterItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            itemsIndexed(items, key = { _, it -> it.id }) { _, item ->
                PosterCard(
                    title = item.title,
                    imageDrawable = item.backdropRes,
                    onClick = { onFocusItem(item) }
                )
            }
        }
    }
}
