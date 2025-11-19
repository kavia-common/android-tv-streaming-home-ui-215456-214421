package com.example.android_tv_home_screen_ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.android_tv_home_screen_ui.ui.screens.HomeScreen
import com.example.android_tv_home_screen_ui.ui.theme.OceanTheme
import com.example.android_tv_home_screen_ui.data.mock.MockContent
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.android_tv_home_screen_ui.ui.screens.ContentInfoScreen
import com.example.android_tv_home_screen_ui.ui.screens.ContentInfoMockProvider

/**
 * PUBLIC_INTERFACE
 * MainActivity is the Android TV entrypoint.
 *
 * This activity hosts a Jetpack Compose UI for the Home screen and navigates to Content Info:
 * - Fixed top navigation
 * - Preview/Hero section
 * - Horizontally scrolling carousels
 * - ContentInfoScreen route with mock data
 *
 * No backend dependencies; uses static mock data and Figma-derived styles.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanTheme {
                // Simple in-memory "navigation" state: 0 = Home, 1 = ContentInfo
                var route by remember { mutableIntStateOf(0) }
                val selected = remember { mutableStateOf(MockContent.rows.first().items.first()) }

                when (route) {
                    0 -> HomeScreen(
                        rows = MockContent.rows,
                        selectedItem = selected.value,
                        onSelectedChange = { selected.value = it },
                        onOpenContentInfo = {
                            route = 1
                        }
                    )
                    else -> ContentInfoScreen(
                        info = ContentInfoMockProvider.sample(),
                        onBack = { route = 0 },
                        onPlay = { /* mock */ },
                        onAdd = { /* mock */ },
                        onMoreInfo = { /* mock */ }
                    )
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Allow Compose focus system to consume DPAD; fall back to default where necessary
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER -> {
                true
            }
            KeyEvent.KEYCODE_BACK -> {
                super.onKeyDown(keyCode, event)
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
