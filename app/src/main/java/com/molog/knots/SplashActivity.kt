package com.molog.knots

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.molog.knots.screens.Splash
import com.molog.knots.ui.theme.KnotsTheme
import com.molog.knots.ui.theme.knotsBlack

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUIController = rememberSystemUiController()
            systemUIController.setNavigationBarColor(knotsBlack, darkIcons = false)
            systemUIController.setStatusBarColor(knotsBlack, darkIcons = false)

            KnotsTheme {
                Splash {
                    println("FirebaseAuth: ${FirebaseAuth.getInstance().currentUser}")
                    if(FirebaseAuth.getInstance().currentUser!=null){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}