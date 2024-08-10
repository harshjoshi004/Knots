package com.molog.knots

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.molog.knots.screens.Login
import com.molog.knots.screens.Register
import com.molog.knots.ui.theme.KnotsTheme
import com.molog.knots.ui.theme.knotsBlack

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnotsTheme {
                val systemUIController = rememberSystemUiController()
                systemUIController.setNavigationBarColor(knotsBlack, darkIcons = false)
                systemUIController.setStatusBarColor(knotsBlack, darkIcons = false)

                val navController2 = rememberNavController()
                NavHost(navController = navController2, startDestination = "login") {

                    composable("login") {
                        Login(navController = navController2) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                    }

                    composable("register") {
                        Register(navController = navController2) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }
}