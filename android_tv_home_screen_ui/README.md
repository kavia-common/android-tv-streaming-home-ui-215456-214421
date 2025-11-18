# Android TV Home Screen UI (Native - Jetpack Compose)

This module now renders the Android TV Home screen using Jetpack Compose with the Ocean Professional theme.

What’s implemented:
- OceanTheme (Material3) with bold high-contrast colors.
- TopNavBar with DPAD-friendly focus states and selection.
- PreviewSection (hero) with gradient overlay and action buttons (Play, Add, Info).
- Two horizontally scrolling ContentRow carousels using PosterCard with focus scaling/shadow.
- Focus helpers (FocusScale) for TV DPAD scaling/glow.
- Mock data only; no backend/auth.

Entry:
- MainActivity sets content to HomeScreen (Compose).
- If you previously used activity_main.xml, it’s no longer used.

Compose setup:
- Enabled in app/build.gradle.kts (composeOptions + dependencies).
- Uses androidx.tv:tv-foundation and tv-material for TV-friendly behaviors.

Notes:
- Images are placeholder URLs; swap with local drawables or remote images as needed.
- Accessibility: content descriptions on posters, large readable typography, high contrast.

Preview/Startup (Web) on port 3000 remains unchanged and can still be used for visual reference.
