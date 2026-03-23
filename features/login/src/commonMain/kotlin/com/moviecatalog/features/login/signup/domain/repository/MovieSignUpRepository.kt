package com.moviecatalog.features.login.signup.domain.repository

internal interface MovieSignUpRepository {
     suspend fun countUsersWithUsername(normalizedUsername: String): Long

     suspend fun insertUser(normalizedUsername: String, password: String)
}