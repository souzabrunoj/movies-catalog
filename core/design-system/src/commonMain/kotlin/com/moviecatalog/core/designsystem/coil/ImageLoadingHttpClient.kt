package com.moviecatalog.core.designsystem.coil

import io.ktor.client.HttpClient

internal expect fun createImageLoadingHttpClient(): HttpClient
