package com.moviecatalog.core.uitests

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import org.junit.Rule
import org.junit.Test

class MovieThemeSnapshotTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun material3_surfaceText() {
        paparazzi.snapshot {
            MaterialTheme {
                Surface {
                    Text(
                        text = "Movie Catalog",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }

    @Test
    fun movieTheme_headingSnapshot() {
        paparazzi.snapshot {
            MovieTheme {
                MovieText(
                    text = "Movie Catalog",
                    variant = MovieTextVariant.HeadingLarge(FontWeight.Bold),
                    contentColor = MovieTextColor.High,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}
