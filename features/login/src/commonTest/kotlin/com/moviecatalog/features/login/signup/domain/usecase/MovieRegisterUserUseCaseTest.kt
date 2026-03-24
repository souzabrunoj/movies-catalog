package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.model.MovieRegisterUserResult
import com.moviecatalog.features.login.test.FakeMovieSignUpRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieRegisterUserUseCaseTest {

    private val evaluate = MovieEvaluatePasswordRulesUseCase()
    private val validateEmpty = MovieValidateSignUpNonEmptyUseCase()

    @Test
    fun success_afterDelay_whenValidAndUsernameFree() = runTest {
        val repo = FakeMovieSignUpRepository()
        val useCase = MovieRegisterUserUseCase(validateEmpty, evaluate, repo)
        val job =
            launch {
                val result = useCase("Alice", "Abcdef1!", "Abcdef1!")
                assertEquals(MovieRegisterUserResult.Success, result)
            }
        advanceTimeBy(1_600)
        job.join()
    }

    @Test
    fun usernameTaken_whenAlreadyRegistered() = runTest {
        val repo = FakeMovieSignUpRepository()
        repo.seed("alice", "x")
        val useCase = MovieRegisterUserUseCase(validateEmpty, evaluate, repo)
        val job =
            launch {
                val result = useCase("alice", "Abcdef1!", "Abcdef1!")
                assertIs<MovieRegisterUserResult.Failure.UsernameTaken>(result)
            }
        advanceTimeBy(1_600)
        job.join()
    }

    @Test
    fun passwordMismatch_beforeDelay() = runTest {
        val repo = FakeMovieSignUpRepository()
        val useCase = MovieRegisterUserUseCase(validateEmpty, evaluate, repo)
        val result = useCase("Alice", "Abcdef1!", "Abcdef1?")
        assertIs<MovieRegisterUserResult.Failure.PasswordMismatch>(result)
    }
}
