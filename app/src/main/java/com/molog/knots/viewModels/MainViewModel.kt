package com.molog.knots.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.molog.knots.navigation.Routes

class MainViewModel:ViewModel() {
    val currentScreen = mutableStateOf<Routes>(Routes.HomeScreen)
}