package com.moviecatalog.core.database

internal fun requireSafeRelativePath(relativePath: String): String {
    val normalized = relativePath.replace('\\', '/').trim().trimStart('/')
    require(normalized.isNotEmpty()) { "relativePath must not be empty" }
    require(".." !in normalized) { "relativePath must not contain '..'" }
    require(':' !in normalized) { "relativePath must not contain ':'" }
    for (segment in normalized.split('/')) {
        if (segment.isEmpty()) continue
        require(segment != ".") { "relativePath must not contain '.' segments" }
        require(segment != "..") { "relativePath must not contain '..'" }
    }
    return normalized
}
