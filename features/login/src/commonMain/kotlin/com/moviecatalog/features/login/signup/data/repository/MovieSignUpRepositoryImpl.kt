package com.moviecatalog.features.login.signup.data.repository

import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class MovieSignUpRepositoryImpl : MovieSignUpRepository {
    private val mutex = Mutex()
    private val users = mutableMapOf<String, String>()

    override suspend fun registerIfAbsent(normalizedUsername: String, password: String): Boolean =
        mutex.withLock {
            if (users.containsKey(normalizedUsername)) {
                false
            } else {
                users[normalizedUsername] = password
                true
            }
        }
}
