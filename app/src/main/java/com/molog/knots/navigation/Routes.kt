package com.molog.knots.navigation

sealed class Routes(val route: String, val title: String) {
    data object AddThreadScreen : Routes("addThread", "Add Thread")
    data object HomeScreen : Routes("home", "Home")
    data object ProfileScreen : Routes("profile", "Profile")
    data object SearchScreen : Routes("search", "Search")
}