package com.example.android_tv_home_screen_ui.data.mock

/**
 * Mock data model and lists used by the Home screen.
 * Image URLs refer to Figma-derived assets in web preview; for native we just use sample URLs,
 * but titles/descriptions match the preview content. In a real app, these would be drawables or remote URLs.
 */

// PUBLIC_INTERFACE
data class PosterItem(
    val id: String,
    val title: String,
    val backdrop: String,
    val description: String
)

// PUBLIC_INTERFACE
data class RowData(
    val title: String,
    val items: List<PosterItem>
)

// PUBLIC_INTERFACE
object MockContent {

    val rowTrending = RowData(
        title = "Trending Now",
        items = listOf(
            PosterItem("a1", "Gladiator II", "https://via.placeholder.com/1280x720.png?text=Gladiator+II", "Lucius must face the arena to restore Rome."),
            PosterItem("a2", "Ex Machina", "https://via.placeholder.com/1280x720.png?text=Ex+Machina", "An AI test spirals into a tense mind game."),
            PosterItem("a3", "2012", "https://via.placeholder.com/1280x720.png?text=2012", "The world collapses under cataclysmic events."),
            PosterItem("a4", "Sing Street", "https://via.placeholder.com/1280x720.png?text=Sing+Street", "A teen forms a band to impress a girl."),
            PosterItem("a5", "Ad Astra", "https://via.placeholder.com/1280x720.png?text=Ad+Astra", "An astronaut journeys to find his father."),
            PosterItem("a6", "Heroic Saga", "https://via.placeholder.com/1280x720.png?text=Heroic+Saga", "A bold tale of courage and fate."),
            PosterItem("a7", "Night Shift", "https://via.placeholder.com/1280x720.png?text=Night+Shift", "Mysteries unravel after dark.")
        )
    )

    val rowRecommended = RowData(
        title = "Recommended for You",
        items = listOf(
            PosterItem("b1", "Ocean Deep", "https://via.placeholder.com/1280x720.png?text=Ocean+Deep", "Dive into the unseen world under the sea."),
            PosterItem("b2", "The Last Stand", "https://via.placeholder.com/1280x720.png?text=The+Last+Stand", "A final fight against impossible odds."),
            PosterItem("b3", "City Lights", "https://via.placeholder.com/1280x720.png?text=City+Lights", "Neon dreams in a restless city."),
            PosterItem("b4", "Orbit", "https://via.placeholder.com/1280x720.png?text=Orbit", "A journey beyond gravitational bounds."),
            PosterItem("b5", "Far From Home", "https://via.placeholder.com/1280x720.png?text=Far+From+Home", "Returning is harder than leaving.")
        )
    )

    val rows: List<RowData> = listOf(rowTrending, rowRecommended)
}
