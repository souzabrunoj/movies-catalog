package com.moviecatalog.features.login.signup.data.persistence

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class SignUpRegisteredUsersJsonTest {

    @Test
    fun emptyMap_roundTrip() {
        val bytes = SignUpRegisteredUsersJson.encode(emptyMap())
        assertEquals(emptyMap(), SignUpRegisteredUsersJson.decode(bytes))
    }

    @Test
    fun decode_emptyBytes_returnsEmptyMap() {
        assertEquals(emptyMap(), SignUpRegisteredUsersJson.decode(byteArrayOf()))
    }

    @Test
    fun singleUser_roundTrip() {
        val original = mapOf("alice" to "secret1")
        val decoded = SignUpRegisteredUsersJson.decode(SignUpRegisteredUsersJson.encode(original))
        assertEquals(original, decoded)
    }

    @Test
    fun multipleUsers_roundTrip() {
        val original = mapOf("a" to "p1", "b" to "p2")
        val decoded = SignUpRegisteredUsersJson.decode(SignUpRegisteredUsersJson.encode(original))
        assertEquals(original.size, decoded.size)
        assertTrue(decoded.keys.containsAll(original.keys))
        original.forEach { (k, v) -> assertEquals(v, decoded[k]) }
    }
}
