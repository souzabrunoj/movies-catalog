package com.moviecatalog.core.database.persistence

import com.moviecatalog.core.database.LocalDiskStorage
import com.moviecatalog.core.database.requireSafeRelativePath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSHomeDirectory

internal class IosLocalDiskStorage : LocalDiskStorage {

    private val root: Path = "${NSHomeDirectory()}/Documents".toPath()

    override suspend fun read(relativePath: String): ByteArray? =
        withContext(Dispatchers.Default) {
            val path = resolvePath(relativePath)
            if (!FileSystem.SYSTEM.exists(path)) return@withContext null
            FileSystem.SYSTEM.read(path) { readByteArray() }
        }

    override suspend fun write(relativePath: String, bytes: ByteArray) {
        withContext(Dispatchers.Default) {
            val path = resolvePath(relativePath)
            path.parent?.let { parent ->
                if (!FileSystem.SYSTEM.exists(parent)) {
                    FileSystem.SYSTEM.createDirectories(parent)
                }
            }
            FileSystem.SYSTEM.write(path, mustCreate = false) {
                write(bytes)
            }
        }
    }

    private fun resolvePath(relativePath: String): Path {
        val safe = requireSafeRelativePath(relativePath)
        return safe.split('/').filter { it.isNotEmpty() }.fold(root) { acc, segment -> acc / segment }
    }
}
