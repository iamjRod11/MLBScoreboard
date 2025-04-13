package com.mlb.baseballscores.ui.compose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mlb.baseballscores.ui.compose.ScoreboardScreen

enum class Routes {
    MLB_SCOREBOARD_ROUTE
}

sealed class NavigationItem(val route: String) {
    data object ScoreboardScreen: NavigationItem(route = Routes.MLB_SCOREBOARD_ROUTE.name)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreboardNav(
    navHost: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "MLB Scoreboard",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navHost,
            startDestination = NavigationItem.ScoreboardScreen.route,
        ) {
            composable(route = NavigationItem.ScoreboardScreen.route) {
                ScoreboardScreen()
            }
        }
    }
}