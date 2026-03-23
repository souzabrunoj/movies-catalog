package com.moviecatalog.core.designsystem.coil

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout

internal actual fun createImageLoadingHttpClient(): HttpClient =
    HttpClient(OkHttp) {
        install(HttpTimeout) {
            requestTimeoutMillis = MovieImageNetworkConfig.REQUEST_TIMEOUT_MS
            connectTimeoutMillis = MovieImageNetworkConfig.CONNECT_TIMEOUT_MS
            socketTimeoutMillis = MovieImageNetworkConfig.SOCKET_TIMEOUT_MS
        }
    }
