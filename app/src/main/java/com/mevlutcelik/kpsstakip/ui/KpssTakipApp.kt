package com.mevlutcelik.kpsstakip.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mevlutcelik.kpsstakip.KpssApplication
import com.mevlutcelik.kpsstakip.ui.screens.AddExamScreen
import com.mevlutcelik.kpsstakip.ui.screens.AnalysisScreen
import com.mevlutcelik.kpsstakip.ui.screens.ExamTimerScreen
import com.mevlutcelik.kpsstakip.ui.screens.HomeScreen
import com.mevlutcelik.kpsstakip.ui.screens.SettingsScreen
import com.mevlutcelik.kpsstakip.ui.screens.SubjectAnalysisScreen
import com.mevlutcelik.kpsstakip.viewmodel.AnalysisViewModel
import com.mevlutcelik.kpsstakip.viewmodel.ExamViewModel
import com.mevlutcelik.kpsstakip.viewmodel.HomeViewModel
import com.mevlutcelik.kpsstakip.viewmodel.TimerViewModel
import kotlinx.coroutines.launch

sealed class Screen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : Screen("home", "Özet", Icons.Default.Home)
    object Add : Screen("add", "Ekle", Icons.Default.Add)
    object Analysis : Screen("analysis", "Grafik", Icons.Default.List)
    object Settings : Screen("settings", "Ayarlar", Icons.Default.Settings)
}

// Custom screen for Timer with emoji
data class EmojiScreen(val route: String, val label: String, val emoji: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KpssTakipApp() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as KpssApplication
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory(app.repository))
    val examViewModel: ExamViewModel = viewModel(factory = ExamViewModel.Factory(app.repository))
    val analysisViewModel: AnalysisViewModel = viewModel(factory = AnalysisViewModel.Factory(app.repository))
    val timerViewModel: TimerViewModel = viewModel(factory = TimerViewModel.Factory(app.repository))
    
    val isDarkMode by app.preferencesManager.isDarkMode.collectAsState(initial = false)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("KPSS Takip") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                // Home
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Özet") },
                    label = { },
                    selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                    onClick = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                
                // Timer (with emoji)
                NavigationBarItem(
                    icon = { Text("⏱️", style = MaterialTheme.typography.titleLarge) },
                    label = { },
                    selected = currentDestination?.hierarchy?.any { it.route == "timer" } == true,
                    onClick = {
                        navController.navigate("timer") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                
                // Add
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Ekle") },
                    label = { },
                    selected = currentDestination?.hierarchy?.any { it.route == "add" } == true,
                    onClick = {
                        navController.navigate("add") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                
                // Subject Analysis
                NavigationBarItem(
                    icon = { Text("📚", style = MaterialTheme.typography.titleLarge) },
                    label = { },
                    selected = currentDestination?.hierarchy?.any { it.route == "subject_analysis" } == true,
                    onClick = {
                        navController.navigate("subject_analysis") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                
                // Analysis
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Grafik") },
                    label = { },
                    selected = currentDestination?.hierarchy?.any { it.route == "analysis" } == true,
                    onClick = {
                        navController.navigate("analysis") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                
                // Settings
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Ayarlar") },
                    label = { },
                    selected = currentDestination?.hierarchy?.any { it.route == "settings" } == true,
                    onClick = {
                        navController.navigate("settings") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(viewModel = homeViewModel)
            }
            composable("timer") {
                ExamTimerScreen(viewModel = timerViewModel)
            }
            composable("add") {
                AddExamScreen(
                    viewModel = examViewModel,
                    onExamAdded = { navController.popBackStack() }
                )
            }
            composable("subject_analysis") {
                SubjectAnalysisScreen(viewModel = analysisViewModel)
            }
            composable("analysis") {
                AnalysisScreen(viewModel = analysisViewModel)
            }
            composable("settings") {
                SettingsScreen()
            }
        }
    }
}
