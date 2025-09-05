package com.pirataram.marveldemo.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pirataram.marveldemo.presentation.ui.characters.CharactersScreen
import com.pirataram.marveldemo.presentation.ui.favorites.FavoritesScreen
import com.pirataram.marveldemo.presentation.ui.navigation.MapScreen

@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomTab.Characters.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomTab.Characters.route) {
                CharactersRoute(
                    onNavigateToFavorites = { navController.navigate(BottomTab.Favorites.route) }
                )
            }
            composable(BottomTab.Favorites.route) {
                FavoritesRoute()
            }
            composable(BottomTab.Map.route) {
                MapRoute()
            }
        }
    }
}

@Composable
private fun BottomBar(navController: NavController) {
    val backStack by navController.currentBackStackEntryAsState()
    val current = backStack?.destination?.route

    NavigationBar {
        BottomTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = current == tab.route,
                onClick = {
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(tab.icon, contentDescription = null) },
                label = { Text(tab.label) },
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun CharactersRoute(
    onNavigateToFavorites: () -> Unit
) {
    CharactersScreen(onNavigateToFavorites)
}

@Composable
fun FavoritesRoute() {
    FavoritesScreen()
}

@Composable
fun MapRoute() {
    MapScreen()
}


sealed class BottomTab(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Characters : BottomTab("characters", Icons.Outlined.Home, "Personajes")
    object Favorites : BottomTab("favorites", Icons.Outlined.Favorite, "Favoritos")
    object Map       : BottomTab("map", Icons.Outlined.LocationOn, "Mapa")

    companion object {
        val entries = listOf(Characters, Favorites, Map)
    }
}