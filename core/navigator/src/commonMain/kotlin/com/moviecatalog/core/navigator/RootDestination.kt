package com.moviecatalog.core.navigator

sealed class RootDestination : NavDestination {
    data object Login : RootDestination()
    data object Home : RootDestination()
    data object Catalog : RootDestination()
}
