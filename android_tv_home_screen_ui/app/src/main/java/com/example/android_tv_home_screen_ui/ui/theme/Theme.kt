package com.example.android_tv_home_screen_ui.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val OceanDarkScheme: ColorScheme = darkColorScheme(
    primary = OceanPrimary,
    onPrimary = OceanOnPrimary,
    secondary = OceanSecondary,
    onSecondary = OceanOnSecondary,
    error = OceanError,
    background = OceanBackground,
    onBackground = OceanText,
    surface = OceanSurface,
    onSurface = OceanText
)

/**
 * PUBLIC_INTERFACE
 * OceanTheme
 *
 * Applies the Ocean Professional color scheme and typography to the app.
 * Centralizes the palette so screens/components don't hardcode colors.
 *
 * @param content Composable content block for theming scope
 */
@Composable
fun OceanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = OceanDarkScheme,
        typography = OceanTypography,
        content = content
    )
}
