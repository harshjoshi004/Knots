package com.molog.knots.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.molog.knots.viewModels.MainViewModel

@Composable
fun Home(navController: NavHostController, mainViewModel: MainViewModel) {
    Text(text = "Home")
}