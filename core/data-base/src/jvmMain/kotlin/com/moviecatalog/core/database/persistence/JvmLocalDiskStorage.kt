package com.moviecatalog.core.database.persistence

import com.moviecatalog.core.database.LocalDiskStorage
import com.moviecatalog.core.database.requireSafeRelativePath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

internal class JvmLocalDiskStorage : LocalDiskStorage {

    private val root: File =
        File(
            System.getProperty("user.home") ?: ".",
            ".moviecatalog",
        ).absoluteFile

    override suspend fun read(relativePath: String): ByteArray? =
        withContext(Dispatchers.IO) {
            val file = resolveFile(relativePath)
            if (!file.exists()) null else file.readBytes()
        }

    override suspend fun write(relativePath: String, bytes: ByteArray) {
        withContext(Dispatchers.IO) {
            val file = resolveFile(relativePath)
            file.parentFile?.mkdirs()
            file.writeBytes(bytes)
        }
    }

    private fun resolveFile(relativePath: String): File {
        val safe = requireSafeRelativePath(relativePath)
        var current = root
        for (segment in safe.split('/').filter { it.isNotEmpty() }) {
            current = File(current, segment)
        }
        return current
    }
}
