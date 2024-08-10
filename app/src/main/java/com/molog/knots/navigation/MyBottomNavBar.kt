package com.molog.knots.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.molog.knots.R
import com.molog.knots.models.MyBottomNavItem
import com.molog.knots.ui.theme.knotsLightGrey
import com.molog.knots.ui.theme.knotsPrimary
import com.molog.knots.ui.theme.knotsWhite
import com.molog.knots.viewModels.MainViewModel

val bottomNavItemList = mutableListOf(
    MyBottomNavItem(title = "Home", screen = Routes.HomeScreen, R.drawable.home),
    MyBottomNavItem(title = "Search", screen = Routes.SearchScreen, R.drawable.search),
    MyBottomNavItem(title = "Add Thread", screen = Routes.AddThreadScreen, R.drawable.add),
    MyBottomNavItem(title = "Profile", screen = Routes.ProfileScreen, R.drawable.profile),
)

@Composable
fun MyBottomNavBar(navController: NavHostController, mainViewModel: MainViewModel){
    val selectedIndex = remember { mutableIntStateOf(0) }
    //NavigationBar {
        AnimatedNavigationBar(
            modifier = Modifier.padding(8.dp).systemBarsPadding(),
            selectedIndex = selectedIndex.intValue,
            barColor = knotsPrimary,
            ballColor = knotsPrimary,
            cornerRadius = shapeCornerRadius(50f)
        ){
            bottomNavItemList.forEachIndexed { index, item ->
                IconButton(modifier = Modifier.padding(8.dp), onClick = {
                    selectedIndex.intValue = index
                    mainViewModel.currentScreen.value = item.screen
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Icon(painter = painterResource(id = item.icon),
                        modifier = Modifier.padding(8.dp).size(24.dp),
                        tint = knotsWhite,
                        contentDescription = null)
                }
            }
        }
    //}
}