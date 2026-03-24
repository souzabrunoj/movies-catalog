package com.moviecatalog.features.home.details.ui.uiModel

import com.moviecatalog.core.tests.runViewModelTest
import com.moviecatalog.core.uimodel.UiMode
import com.moviecatalog.features.home.details.ui.uiModel.state.MovieObjectDetailUiModel
import com.moviecatalog.features.home.test.FakeMovieDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieCatalogDetailsUiModelTest {

    @Test
    fun getMovieDetails_loadsFirstEmission() = runViewModelTest {
        val detail = MovieObjectDetailUiModel(title = "Hero")
        val repo =
            FakeMovieDetailsRepository { _ ->
                flowOf(detail)
            }
        val vm = MovieCatalogDetailsUiModel(repo)
        vm.getMovieDetails(1)
        advanceUntilIdle()
        assertEquals("Hero", vm.state.value.data.detail.title)
        assertIs<UiMode.Content>(vm.state.value.mode)
    }
}
