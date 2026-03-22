package com.moviecatalog.features.home.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moviecatalog.features.home.ui.detail.DetailScreen
import com.moviecatalog.features.home.ui.list.ListScreen


internal object CatalogListNavScreen : Screen {

    @Composable
    override fun Content() {
        ListScreen()
    }
}

internal data class DetailNavScreen(private val objectId: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        DetailScreen(
            objectId = objectId,
            navigateBack = { navigator.pop() },
        )
    }
}
