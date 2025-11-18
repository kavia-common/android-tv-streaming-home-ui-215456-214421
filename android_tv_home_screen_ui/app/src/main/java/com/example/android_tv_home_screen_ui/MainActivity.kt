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

/**
 * PUBLIC_INTERFACE
 * MainActivity is the Android TV entrypoint.
 *
 * This activity hosts a Jetpack Compose UI for the Home screen:
 * - Fixed top navigation
 * - Preview/Hero section
 * - Horizontally scrolling carousels
 * - DPAD/Enter/Back handling friendly for TV
 *
 * No backend dependencies; uses static mock data and Figma-derived styles.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanTheme {
                val selected = remember { mutableStateOf(MockContent.rows.first().items.first()) }
                HomeScreen(
                    rows = MockContent.rows,
                    selectedItem = selected.value,
                    onSelectedChange = { selected.value = it }
                )
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Allow Compose focus system to consume DPAD; fall back to default where necessary
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER -> {
                // Compose handles click via focused element
                true
            }
            KeyEvent.KEYCODE_BACK -> {
                // Let Compose handle overlay states; default finishes if none
                super.onKeyDown(keyCode, event)
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
