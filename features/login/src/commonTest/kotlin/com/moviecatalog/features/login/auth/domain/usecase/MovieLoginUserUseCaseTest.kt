package com.moviecatalog.features.login.auth.domain.usecase

import com.moviecatalog.features.login.auth.domain.model.MovieLoginResult
import com.moviecatalog.features.login.test.FakeMovieSignUpRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
internal class MovieLoginUserUseCaseTest {

    @Test
    fun emptyUsername() = runTest {
        val useCase = MovieLoginUserUseCase(FakeMovieSignUpRepository())
        assertEquals(MovieLoginResult.Failure.EmptyUsername, useCase("  ", "p"))
    }

    @Test
    fun emptyPassword() = runTest {
        val useCase = MovieLoginUserUseCase(FakeMovieSignUpRepository())
        assertEquals(MovieLoginResult.Failure.EmptyPassword, useCase("u", ""))
    }

    @Test
    fun success_afterDelay_whenCredentialsMatch() = runTest {
        val repo = FakeMovieSignUpRepository()
        repo.seed("alice", "secret")
        val useCase = MovieLoginUserUseCase(repo)
        val job =
            launch {
                val result = useCase("  Alice ", "secret")
                assertEquals(MovieLoginResult.Success, result)
            }
        advanceTimeBy(1_600)
        job.join()
    }

    @Test
    fun userNotFound() = runTest {
        val useCase = MovieLoginUserUseCase(FakeMovieSignUpRepository())
        val job =
            launch {
                val result = useCase("nobody", "p")
                assertIs<MovieLoginResult.Failure.UserNotFound>(result)
            }
        advanceTimeBy(1_600)
        job.join()
    }

    @Test
    fun wrongPassword() = runTest {
        val repo = FakeMovieSignUpRepository()
        repo.seed("bob", "right")
        val useCase = MovieLoginUserUseCase(repo)
        val job =
            launch {
                val result = useCase("bob", "wrong")
                assertIs<MovieLoginResult.Failure.InvalidPassword>(result)
            }
        advanceTimeBy(1_600)
        job.join()
    }
}
