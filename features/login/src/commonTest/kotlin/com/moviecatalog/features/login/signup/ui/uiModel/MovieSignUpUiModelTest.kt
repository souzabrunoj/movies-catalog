package com.moviecatalog.features.login.signup.ui.uiModel

import com.moviecatalog.features.login.signup.domain.usecase.MovieEvaluatePasswordRulesUseCase
import com.moviecatalog.features.login.signup.domain.usecase.MovieRegisterUserUseCase
import com.moviecatalog.features.login.signup.domain.usecase.MovieValidateSignUpNonEmptyUseCase
import com.moviecatalog.features.login.signup.ui.uiModel.state.MovieSignUpFeedbackEvent
import com.moviecatalog.features.login.test.FakeMovieSignUpRepository
import com.moviecatalog.core.tests.runViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieSignUpUiModelTest {

    private val evaluate = MovieEvaluatePasswordRulesUseCase()
    private val validateEmpty = MovieValidateSignUpNonEmptyUseCase()

    @Test
    fun onPasswordChange_updatesRules() {
        val vm =
            MovieSignUpUiModel(
                evaluate,
                MovieRegisterUserUseCase(validateEmpty, evaluate, FakeMovieSignUpRepository()),
            )
        vm.onPasswordChange("Abcdef1!")
        vm.onConfirmPasswordChange("Abcdef1!")
        assertTrue(vm.state.value.data.passwordRules.allSatisfied)
    }

    @Test
    fun completeRegistration_success() = runViewModelTest {
        val repo = FakeMovieSignUpRepository()
        val register = MovieRegisterUserUseCase(validateEmpty, evaluate, repo)
        val vm = MovieSignUpUiModel(evaluate, register)
        vm.onUsernameChange("newuser")
        vm.onPasswordChange("Abcdef1!")
        vm.onConfirmPasswordChange("Abcdef1!")
        vm.completeRegistration()
        advanceTimeBy(1_600)
        advanceUntilIdle()
        assertIs<MovieSignUpFeedbackEvent.Success>(vm.state.value.data.feedbackEvent)
        assertEquals("", vm.state.value.data.username)
    }

    @Test
    fun completeRegistration_usernameTaken() = runViewModelTest {
        val repo = FakeMovieSignUpRepository()
        repo.seed("taken", "x")
        val register = MovieRegisterUserUseCase(validateEmpty, evaluate, repo)
        val vm = MovieSignUpUiModel(evaluate, register)
        vm.onUsernameChange("taken")
        vm.onPasswordChange("Abcdef1!")
        vm.onConfirmPasswordChange("Abcdef1!")
        vm.completeRegistration()
        advanceTimeBy(1_600)
        advanceUntilIdle()
        assertIs<MovieSignUpFeedbackEvent.UsernameTaken>(vm.state.value.data.feedbackEvent)
    }

    @Test
    fun completeRegistration_noOpWhenRulesNotMet() = runTest {
        val repo = FakeMovieSignUpRepository()
        val register = MovieRegisterUserUseCase(validateEmpty, evaluate, repo)
        val vm = MovieSignUpUiModel(evaluate, register)
        vm.onUsernameChange("u")
        vm.onPasswordChange("short")
        vm.onConfirmPasswordChange("short")
        vm.completeRegistration()
        advanceUntilIdle()
        assertNull(vm.state.value.data.feedbackEvent)
    }

    @Test
    fun togglePasswordVisible_flipsFlag() {
        val vm = MovieSignUpUiModel(
            evaluate,
            MovieRegisterUserUseCase(validateEmpty, evaluate, FakeMovieSignUpRepository()),
        )
        assertEquals(false, vm.state.value.data.passwordVisible)
        vm.togglePasswordVisible()
        assertEquals(true, vm.state.value.data.passwordVisible)
    }
}
