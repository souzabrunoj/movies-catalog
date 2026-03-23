package com.moviecatalog.features.login.signup.data.repository

import com.moviecatalog.core.database.LocalDiskStorage
import com.moviecatalog.features.login.signup.data.persistence.SignUpRegisteredUsersJson
import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class MovieSignUpRepositoryImpl(
    private val disk: LocalDiskStorage,
) : MovieSignUpRepository {
    private val mutex = Mutex()
    private val users = mutableMapOf<String, String>()
    private var loaded = false

    override suspend fun registerIfAbsent(normalizedUsername: String, password: String): Boolean =
        mutex.withLock {
            if (!loaded) {
                runCatching {
                    disk.read(REGISTERED_USERS_RELATIVE_PATH)?.takeIf { it.isNotEmpty() }?.let { bytes ->
                        SignUpRegisteredUsersJson.decode(bytes)
                    }
                }.getOrNull()?.let { decoded ->
                    users.clear()
                    users.putAll(decoded)
                }
                loaded = true
            }
            if (users.containsKey(normalizedUsername)) {
                return@withLock false
            }
            users[normalizedUsername] = password
            disk.write(REGISTERED_USERS_RELATIVE_PATH, SignUpRegisteredUsersJson.encode(users))
            true
        }

    private companion object {
        const val REGISTERED_USERS_RELATIVE_PATH: String = "auth/registered_users.json"
    }
}
