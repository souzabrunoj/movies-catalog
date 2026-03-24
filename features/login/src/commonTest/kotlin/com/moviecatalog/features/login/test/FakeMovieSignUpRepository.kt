package com.moviecatalog.features.login.test

import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import com.moviecatalog.features.login.signup.domain.repository.UserCredentialVerification

internal class FakeMovieSignUpRepository : MovieSignUpRepository {
    private val users = mutableMapOf<String, String>()

    fun seed(normalizedUsername: String, password: String) {
        users[normalizedUsername] = password
    }

    override suspend fun registerIfAbsent(normalizedUsername: String, password: String): Boolean {
        if (users.containsKey(normalizedUsername)) return false
        users[normalizedUsername] = password
        return true
    }

    override suspend fun verifyCredentials(
        normalizedUsername: String,
        password: String,
    ): UserCredentialVerification =
        when {
            !users.containsKey(normalizedUsername) -> UserCredentialVerification.UnknownUser
            users[normalizedUsername] != password -> UserCredentialVerification.WrongPassword
            else -> UserCredentialVerification.Match
        }
}
