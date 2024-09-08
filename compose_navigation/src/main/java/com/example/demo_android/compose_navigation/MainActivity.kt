package com.example.demo_android.compose_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.demo_android.core.ui.theme.Demo_AndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    Demo_AndroidTheme {
        val navController: NavHostController = rememberNavController()
        val currentBackStack: NavBackStackEntry? by navController.currentBackStackEntryAsState()

        Scaffold(
            topBar = {
                Row {
                    IconButton(
                        onClick = {
                            navController.navigate(route = "home") { launchSingleTop = true }
                        }
                    ) {
                        Icon(imageVector = if (currentBackStack?.destination?.route == "home") Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(route = "person") { launchSingleTop = true }
                        }
                    ) {
                        Icon(imageVector = if (currentBackStack?.destination?.route == "person") Icons.Filled.Person else Icons.Outlined.Person, contentDescription = null)
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(route = "settings") { launchSingleTop = true }
                        }
                    ) {
                        Icon(imageVector = if (currentBackStack?.destination?.route == "settings") Icons.Filled.Settings else Icons.Outlined.Settings, contentDescription = null)
                    }
                }
            }
        ) {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = "home",
            ) {
                composable(route = "home") {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray),
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "home",
                            color = Color.White,
                        )
                    }
                }
                composable(route = "person") {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray),
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "person",
                            color = Color.White,
                        )
                    }
                }
                composable(route = "settings") {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray),
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "settings",
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}
