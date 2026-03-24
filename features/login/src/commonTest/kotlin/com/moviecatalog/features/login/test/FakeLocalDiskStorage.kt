package com.moviecatalog.features.login.test

import com.moviecatalog.core.database.LocalDiskStorage

internal class FakeLocalDiskStorage : LocalDiskStorage {
    private val files = mutableMapOf<String, ByteArray>()

    override suspend fun read(relativePath: String): ByteArray? = files[relativePath]

    override suspend fun write(relativePath: String, bytes: ByteArray) {
        files[relativePath] = bytes
    }
}
