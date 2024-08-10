package com.molog.knots

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.molog.knots.navigation.MyBottomNavBar
import com.molog.knots.navigation.MyTopAppBar
import com.molog.knots.navigation.Routes
import com.molog.knots.screens.AddThread
import com.molog.knots.screens.Home
import com.molog.knots.screens.Profile
import com.molog.knots.screens.Search
import com.molog.knots.ui.theme.KnotsTheme
import com.molog.knots.ui.theme.knotsBlack
import com.molog.knots.viewModels.MainViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemUIController = rememberSystemUiController()
            systemUIController.setNavigationBarColor(knotsBlack, darkIcons = false)
            systemUIController.setStatusBarColor(Color.Transparent, darkIcons = false)

            val navController = rememberNavController()
            val mainViewModel : MainViewModel = viewModel()

            val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

            KnotsTheme {
                Scaffold(
                    modifier = Modifier.nestedScroll(topBarScrollBehavior.nestedScrollConnection),
                    topBar = {
                        MyTopAppBar(topBarScrollBehavior = topBarScrollBehavior, navController = navController, mainViewModel = mainViewModel)
                    },
                    bottomBar = {
                        MyBottomNavBar(navController = navController, mainViewModel = mainViewModel)
                    }
                ) { innerPadding ->

                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = Routes.HomeScreen.route
                    ){
                        composable(Routes.AddThreadScreen.route){
                            mainViewModel.currentScreen.value = Routes.AddThreadScreen
                            AddThread(navController,mainViewModel)
                        }

                        composable(Routes.HomeScreen.route){
                            mainViewModel.currentScreen.value = Routes.HomeScreen
                            Home(navController,mainViewModel)
                        }

                        composable(Routes.ProfileScreen.route){
                            mainViewModel.currentScreen.value = Routes.ProfileScreen
                            Profile(navController,mainViewModel)
                        }

                        composable(Routes.SearchScreen.route){
                            mainViewModel.currentScreen.value = Routes.SearchScreen
                            Search(navController,mainViewModel)
                        }
                    }
                }
            }
        }
    }
}