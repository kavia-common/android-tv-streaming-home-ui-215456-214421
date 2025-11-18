# Android TV Home Screen UI (Web Preview)

This folder contains a web-based preview for the Android TV Home screen UI using Figma-derived assets and the Ocean Professional theme.

What’s included:
- index.html: Main entry page with top nav, hero/preview area, and horizontal carousels.
- styles.css: Ocean Professional theme tokens, layout, and focus effects.
- app.js: D‑pad (Arrow keys), Enter, and Escape/Back handling, mock data, carousel navigation, hero preview updates, and Info modal overlay logic.

How to use:
1) Serve the project root as static files. Any static server will work. Common options:
   - Python 3: `cd android-tv-streaming-home-ui-215456-214421/android_tv_home_screen_ui && python3 -m http.server 3000`
   - Node (http-server): `npx http-server -p 3000 android-tv-streaming-home-ui-215456-214421/android_tv_home_screen_ui`
   - Or open index.html directly in a modern browser.

2) Open http://localhost:3000 (or your chosen port).
   - Baseline layout targets 1920x1080; it degrades gracefully for other sizes.
   - Navigate with your keyboard arrows:
     - ArrowUp/Down/Left/Right: Move focus between top nav, rows, and items
     - Enter: Trigger primary action (Play) on the focused item
     - Escape/Backspace: Close Info overlay or collapse previews

Controls:
- D‑pad navigation moves focus between:
  - Top Nav (Home, Movies, Series, My List)
  - Rows: Trending, Recommended (horizontally scrollable carousels)
- Enter: Triggers "Play" and flashes the hero area
- Info button on hero: Opens overlay; Escape/Backspace closes it

Accessibility & TV Design:
- Ocean Professional theme with bold contrast and large targets
- 12px rounded corners
- High-visibility focus ring with primary-colored halo
- 60fps animations using transform/opacity only

No backend:
- Uses local mock data and assets in /assets/figmaimages.

Note:
- This is a web-based preview for Android TV UX development and design validation. The native Android TV app is scaffolded in the same repository for Leanback/Compose integration separately.
