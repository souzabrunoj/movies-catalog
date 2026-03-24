package com.moviecatalog.features.login.signup.domain.repository

internal interface MovieSignUpRepository {
    suspend fun registerIfAbsent(normalizedUsername: String, password: String): Boolean

    suspend fun verifyCredentials(
        normalizedUsername: String,
        password: String,
    ): UserCredentialVerification
}