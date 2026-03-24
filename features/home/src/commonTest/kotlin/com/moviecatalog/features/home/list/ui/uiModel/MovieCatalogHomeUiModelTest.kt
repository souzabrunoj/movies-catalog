package com.moviecatalog.features.home.list.ui.uiModel

import com.moviecatalog.core.tests.runViewModelTest
import com.moviecatalog.core.uimodel.UiMode
import com.moviecatalog.features.home.shared.domain.model.Movies
import com.moviecatalog.features.home.test.FakeMovieRepository
import com.moviecatalog.features.home.test.sampleMovieObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieCatalogHomeUiModelTest {

    @Test
    fun getMovies_populatesItemsAfterDelay() = runViewModelTest {
        val movie = sampleMovieObject(title = "Listed")
        val repo = FakeMovieRepository(flowOf(Movies(movies = listOf(movie))))
        val vm = MovieCatalogHomeUiModel(repo)
        vm.getMovies()
        advanceTimeBy(1_100)
        advanceUntilIdle()
        assertEquals(1, vm.state.value.data.items.size)
        assertEquals("Listed", vm.state.value.data.items.first().title)
        assertIs<UiMode.Content>(vm.state.value.mode)
    }
}
