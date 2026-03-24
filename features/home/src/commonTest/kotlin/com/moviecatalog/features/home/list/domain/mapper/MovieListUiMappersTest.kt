package com.moviecatalog.features.home.list.domain.mapper

import com.moviecatalog.features.home.test.sampleMovieObject
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MovieListUiMappersTest {

    @Test
    fun cardSubtitle_joinsDepartmentAndMedium() {
        val item = sampleMovieObject(department = "Dept", medium = "Film").toListItemUiModel()
        assertEquals("Dept • Film", item.cardSubtitle)
    }

    @Test
    fun cardSubtitle_fallsBackToArtist_whenDeptAndMediumBlank() {
        val item =
            sampleMovieObject(department = "  ", medium = "", artistDisplayName = "Solo Artist")
                .toListItemUiModel()
        assertEquals("Solo Artist", item.cardSubtitle)
    }

    @Test
    fun listItem_mapsIdTitleAndPoster() {
        val m = sampleMovieObject(id = 99, title = "T")
        val item = m.toListItemUiModel()
        assertEquals(99, item.movieId)
        assertEquals("T", item.title)
        assertEquals(m.primaryImageSmall, item.posterImageUrl)
    }
}
