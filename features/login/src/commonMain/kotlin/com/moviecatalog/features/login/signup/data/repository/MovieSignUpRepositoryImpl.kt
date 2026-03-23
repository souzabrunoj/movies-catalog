package com.moviecatalog.features.login.signup.data.repository

import com.moviecatalog.core.database.LocalDiskStorage
import com.moviecatalog.features.login.signup.data.persistence.SignUpRegisteredUsersJson
import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import com.moviecatalog.features.login.signup.domain.repository.UserCredentialVerification
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class MovieSignUpRepositoryImpl(
    private val disk: LocalDiskStorage,
) : MovieSignUpRepository {
    private val mutex = Mutex()
    private val users = mutableMapOf<String, String>()
    private var loaded = false

    override suspend fun registerIfAbsent(normalizedUsername: String, password: String): Boolean {
        ensureUsersLoaded()
        val encoded: ByteArray? =
            mutex.withLock {
                if (users.containsKey(normalizedUsername)) {
                    return@withLock null
                }
                users[normalizedUsername] = password
                SignUpRegisteredUsersJson.encode(users)
            }
        if (encoded == null) return false
        disk.write(REGISTERED_USERS_RELATIVE_PATH, encoded)
        return true
    }

    override suspend fun verifyCredentials(
        normalizedUsername: String,
        password: String,
    ): UserCredentialVerification {
        ensureUsersLoaded()
        return mutex.withLock {
            when {
                !users.containsKey(normalizedUsername) -> UserCredentialVerification.UnknownUser
                users[normalizedUsername] != password -> UserCredentialVerification.WrongPassword
                else -> UserCredentialVerification.Match
            }
        }
    }

    private suspend fun ensureUsersLoaded() {
        val needsLoad =
            mutex.withLock {
                if (loaded) false else true
            }
        if (!needsLoad) return

        val decoded =
            runCatching {
                disk.read(REGISTERED_USERS_RELATIVE_PATH)?.takeIf { it.isNotEmpty() }?.let { bytes ->
                    SignUpRegisteredUsersJson.decode(bytes)
                }
            }.getOrNull()

        mutex.withLock {
            if (loaded) return@withLock
            decoded?.let {
                users.clear()
                users.putAll(it)
            }
            loaded = true
        }
    }

    private companion object {
        const val REGISTERED_USERS_RELATIVE_PATH: String = "auth/registered_users.json"
    }
}
