package com.moviecatalog.core.database

public interface LocalDiskStorage {
    public suspend fun read(relativePath: String): ByteArray?

    public suspend fun write(relativePath: String, bytes: ByteArray)
}
