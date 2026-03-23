package com.moviecatalog.features.login.signup.data.repository

import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class MovieSignUpRepositoryImpl : MovieSignUpRepository {
    private val mutex = Mutex()
    private val users = mutableMapOf<String, String>()

    override suspend fun countUsersWithUsername(normalizedUsername: String): Long =
        mutex.withLock {
            if (users.containsKey(normalizedUsername)) 1L else 0L
        }

    override suspend fun insertUser(normalizedUsername: String, password: String) {
        mutex.withLock {
            users[normalizedUsername] = password
        }
    }
}
