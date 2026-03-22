package com.moviecatalog.core.navigator

public sealed class LoginDestination : NavDestination {
    public data object Login : LoginDestination()
}

public sealed class HomeDestination : NavDestination {
    public data object Home : HomeDestination()
}
