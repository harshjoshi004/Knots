package com.molog.knots.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.molog.knots.ui.theme.knotsPrimary
import kotlinx.coroutines.delay

@Composable
fun Splash(function: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000L)
        function()
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = com.molog.knots.R.drawable.knotslogo),
            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(30)),
            contentDescription = null)
    }
}