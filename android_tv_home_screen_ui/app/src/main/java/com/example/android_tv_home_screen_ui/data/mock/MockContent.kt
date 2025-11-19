package com.example.android_tv_home_screen_ui.data.mock

import androidx.annotation.DrawableRes
import com.example.android_tv_home_screen_ui.R

/**
 * Mock data model and lists used by the Home screen.
 * Image references point to local drawable resources for packaged poster art.
 */

// PUBLIC_INTERFACE
data class PosterItem(
    val id: String,
    val title: String,
    @DrawableRes val backdropRes: Int,
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
            PosterItem("a1", "Gladiator II", R.drawable.figure_poster_gladiator_ii, "Lucius must face the arena to restore Rome."),
            PosterItem("a2", "Ex Machina", R.drawable.figure_poster_ex_machina, "An AI test spirals into a tense mind game."),
            PosterItem("a3", "2012", R.drawable.figure_poster_2012, "The world collapses under cataclysmic events."),
            PosterItem("a4", "Sing Street", R.drawable.figure_poster_sing_street, "A teen forms a band to impress a girl."),
            PosterItem("a5", "Ad Astra", R.drawable.figure_poster_ad_astra, "An astronaut journeys to find his father."),
            PosterItem("a6", "Heroic Saga", R.drawable.figure_poster_heroic_saga, "A bold tale of courage and fate."),
            PosterItem("a7", "Night Shift", R.drawable.figure_poster_night_shift, "Mysteries unravel after dark.")
        )
    )

    val rowRecommended = RowData(
        title = "Recommended for You",
        items = listOf(
            PosterItem("b1", "Ocean Deep", R.drawable.figure_poster_ocean_deep, "Dive into the unseen world under the sea."),
            PosterItem("b2", "The Last Stand", R.drawable.figure_poster_the_last_stand, "A final fight against impossible odds."),
            PosterItem("b3", "City Lights", R.drawable.figure_poster_city_lights, "Neon dreams in a restless city."),
            PosterItem("b4", "Orbit", R.drawable.figure_poster_orbit, "A journey beyond gravitational bounds."),
            PosterItem("b5", "Far From Home", R.drawable.figure_poster_far_from_home, "Returning is harder than leaving.")
        )
    )

    val rows: List<RowData> = listOf(rowTrending, rowRecommended)
}
