package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.model.MovieRegisterUserResult
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertNull

internal class MovieValidateSignUpNonEmptyUseCaseTest {

    private val useCase = MovieValidateSignUpNonEmptyUseCase()

    @Test
    fun returnsNull_whenAllFieldsNonEmpty() {
        assertNull(useCase("user", "p", "p"))
    }

    @Test
    fun emptyUsername_whenBlank() {
        val failure = useCase("   ", "p", "p")
        assertIs<MovieRegisterUserResult.Failure.EmptyUsername>(failure)
    }

    @Test
    fun emptyPassword_whenPasswordEmpty() {
        val failure = useCase("user", "", "c")
        assertIs<MovieRegisterUserResult.Failure.EmptyPassword>(failure)
    }

    @Test
    fun emptyConfirm_whenConfirmEmpty() {
        val failure = useCase("user", "p", "")
        assertIs<MovieRegisterUserResult.Failure.EmptyConfirmPassword>(failure)
    }
}
