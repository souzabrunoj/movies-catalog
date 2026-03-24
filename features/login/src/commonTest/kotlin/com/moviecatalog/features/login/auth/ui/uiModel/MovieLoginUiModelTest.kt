package com.moviecatalog.features.login.auth.ui.uiModel

import com.moviecatalog.features.login.auth.domain.usecase.MovieLoginUserUseCase
import com.moviecatalog.features.login.auth.ui.uiModel.state.MovieLoginFeedbackEvent
import com.moviecatalog.features.login.test.FakeMovieSignUpRepository
import com.moviecatalog.core.tests.runViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieLoginUiModelTest {

    @Test
    fun emptyUsername_setsErrorText() = runViewModelTest {
        val vm = MovieLoginUiModel(MovieLoginUserUseCase(FakeMovieSignUpRepository()))
        vm.onUsernameChange("")
        vm.onPasswordChange("p")
        vm.login()
        advanceUntilIdle()
        assertEquals("Enter your username.", vm.state.value.data.usernameErrorText)
        assertFalseSubmitting(vm)
    }

    @Test
    fun success_clearsFieldsAndEmitsNavigateHome() = runViewModelTest {
        val repo = FakeMovieSignUpRepository()
        repo.seed("alice", "secret")
        val vm = MovieLoginUiModel(MovieLoginUserUseCase(repo))
        vm.onUsernameChange("alice")
        vm.onPasswordChange("secret")
        vm.login()
        advanceTimeBy(1_600)
        advanceUntilIdle()
        assertIs<MovieLoginFeedbackEvent.NavigateHome>(vm.state.value.data.feedbackEvent)
        assertEquals("", vm.state.value.data.username)
        assertEquals("", vm.state.value.data.password)
        assertFalseSubmitting(vm)
    }

    @Test
    fun ignoresSecondLoginWhileSubmitting() = runViewModelTest {
        val repo = FakeMovieSignUpRepository()
        repo.seed("u", "p")
        val vm = MovieLoginUiModel(MovieLoginUserUseCase(repo))
        vm.onUsernameChange("u")
        vm.onPasswordChange("p")
        vm.login()
        vm.login()
        advanceTimeBy(1_600)
        advanceUntilIdle()
        assertIs<MovieLoginFeedbackEvent.NavigateHome>(vm.state.value.data.feedbackEvent)
    }

    @Test
    fun consumeFeedback_clearsEvent() = runViewModelTest {
        val repo = FakeMovieSignUpRepository()
        repo.seed("u", "p")
        val vm = MovieLoginUiModel(MovieLoginUserUseCase(repo))
        vm.onUsernameChange("u")
        vm.onPasswordChange("p")
        vm.login()
        advanceTimeBy(1_600)
        advanceUntilIdle()
        assertIs<MovieLoginFeedbackEvent.NavigateHome>(vm.state.value.data.feedbackEvent)
        vm.consumeFeedback()
        assertNull(vm.state.value.data.feedbackEvent)
    }

    private fun assertFalseSubmitting(vm: MovieLoginUiModel) {
        assertFalse(vm.state.value.data.isSubmitting)
    }
}
