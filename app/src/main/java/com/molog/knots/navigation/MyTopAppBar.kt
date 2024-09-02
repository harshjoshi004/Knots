package com.molog.knots.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.molog.knots.ui.theme.fbFontFamily
import com.molog.knots.ui.theme.knotsBlack
import com.molog.knots.ui.theme.knotsGrey
import com.molog.knots.ui.theme.knotsPrimary
import com.molog.knots.ui.theme.knotsWhite
import com.molog.knots.viewModels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    topBarScrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        title = {
            Crossfade(targetState = mainViewModel.currentScreen.value) { it->
                Text(text = it.title, fontFamily = fbFontFamily, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp))
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = knotsBlack,
            scrolledContainerColor = knotsGrey,
            titleContentColor = knotsWhite
        ),
        navigationIcon = {
            Image(painter = painterResource(id = com.molog.knots.R.drawable.knotslogo),
                modifier = Modifier
                    .padding(8.dp)
                    .size(45.dp)
                    .clip(CircleShape),
                contentDescription = null)
        },
        scrollBehavior = topBarScrollBehavior
    )
}