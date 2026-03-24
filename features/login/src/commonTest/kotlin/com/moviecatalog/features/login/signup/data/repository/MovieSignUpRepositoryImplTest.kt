package com.moviecatalog.features.login.signup.data.repository

import com.moviecatalog.features.login.signup.domain.repository.UserCredentialVerification
import com.moviecatalog.features.login.test.FakeLocalDiskStorage
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class MovieSignUpRepositoryImplTest {

    @Test
    fun registerIfAbsent_trueWhenNew() = runTest {
        val disk = FakeLocalDiskStorage()
        val repo = MovieSignUpRepositoryImpl(disk)
        assertTrue(repo.registerIfAbsent("user", "pass"))
    }

    @Test
    fun registerIfAbsent_falseWhenDuplicate() = runTest {
        val disk = FakeLocalDiskStorage()
        val repo = MovieSignUpRepositoryImpl(disk)
        assertTrue(repo.registerIfAbsent("user", "pass"))
        assertFalse(repo.registerIfAbsent("user", "other"))
    }

    @Test
    fun verifyCredentials_loadsFromDiskOnNewInstance() = runTest {
        val disk = FakeLocalDiskStorage()
        MovieSignUpRepositoryImpl(disk).registerIfAbsent("alice", "secret")
        val second = MovieSignUpRepositoryImpl(disk)
        assertEquals(UserCredentialVerification.Match, second.verifyCredentials("alice", "secret"))
        assertEquals(UserCredentialVerification.WrongPassword, second.verifyCredentials("alice", "x"))
    }
}
