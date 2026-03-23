package com.moviecatalog.features.login.signup.domain.repository

internal interface MovieSignUpRepository {
    suspend fun registerIfAbsent(normalizedUsername: String, password: String): Boolean
}