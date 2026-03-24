package com.moviecatalog.features.home.list.data.mapper

import com.moviecatalog.features.home.test.sampleMovieObjectResponse
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MovieObjectMapperTest {

    @Test
    fun toDomain_mapsFields() {
        val dto = sampleMovieObjectResponse(id = 7, title = "Night Watch")
        val domain = dto.toDomain()
        assertEquals(7, domain.id)
        assertEquals("Night Watch", domain.title)
        assertEquals(dto.artistDisplayName, domain.artistDisplayName)
        assertEquals(dto.primaryImageSmall, domain.primaryImageSmall)
    }
}
