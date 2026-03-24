package com.moviecatalog.features.home.details.domain.mappers

import com.moviecatalog.features.home.test.sampleMovieObject
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MovieDetailUiMappersTest {

    @Test
    fun nullMovie_returnsEmptyDetail() {
        val detail = null.toDetailUiModel()
        assertEquals("", detail.title)
        assertEquals("", detail.heroImageUrl)
    }

    @Test
    fun usesPrimaryImage_whenNonBlank() {
        val m =
            sampleMovieObject().copy(
                primaryImage = "https://big.jpg",
                primaryImageSmall = "https://small.jpg",
            )
        val detail = m.toDetailUiModel()
        assertEquals("https://big.jpg", detail.heroImageUrl)
    }

    @Test
    fun fallsBackToSmallImage_whenPrimaryBlank() {
        val m =
            sampleMovieObject().copy(
                primaryImage = "  ",
                primaryImageSmall = "https://small.jpg",
            )
        val detail = m.toDetailUiModel()
        assertEquals("https://small.jpg", detail.heroImageUrl)
    }
}
