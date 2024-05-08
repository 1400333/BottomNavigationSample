package com.example.bottomnavigationsample.ui.custom

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationsample.data.BottomNavigationItem
import com.example.bottomnavigationsample.data.Screens
import com.example.bottomnavigationsample.ui.screen.HomeScreen
import com.example.bottomnavigationsample.ui.screen.ProfileScreen
import com.example.bottomnavigationsample.ui.screen.SearchScreen
import com.example.bottomnavigationsample.ui.screen.SubScreen

@Composable
fun createBottomNavigationBar() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomNavigationBar(navController = navController)
    }) { paddingValues ->
        Navigation(navController = navController, paddingValues = paddingValues)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var strRootTopRoute by remember { mutableStateOf("") }
    var iRootTopId by remember { mutableStateOf(0) }

    NavigationBar {
        BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
            NavigationBarItem(
                    selected = currentDestination?.route?.contains(navigationItem.route) ?: false,
                    label = {
                        Text(navigationItem.label)
                    },
                    icon = {
                        Icon(navigationItem.icon, contentDescription = navigationItem.label)
                    },
                    onClick = {
                        val findStartDestination: NavDestination = navController.graph.findStartDestination()
                        val strFindStartRoute = findStartDestination.route ?: ""

                        var strNavigateRoute = navigationItem.route
                        var iPopUpToId = 0

                        if (navigationItem.route == strFindStartRoute) {
                            //原本在Search或Profile，點Home：要切換的頁面為Home最上層的畫面，即strRootTopRoute
                            strNavigateRoute = strRootTopRoute
                        } else {
                            for (navBackStackEntry in navController.visibleEntries.value) {
                                navBackStackEntry.destination.route?.let { strEntryRoute ->
                                    if (strEntryRoute.contains(strFindStartRoute)) {
                                        //原本在Home，點Search或Profile：返回時到Home的最上層畫面
                                        iPopUpToId = navBackStackEntry.destination.id
                                    }
                                }
                            }
                        }

                        if (iPopUpToId == 0) {
                            //返回一律回到Home的最上層畫面
                            iPopUpToId = iRootTopId
                        }

                        //Log.d("RDLog","[NavigateRoute]${strNavigateRoute}[iPopUpToId]${iPopUpToId}[iRootTopId]${iRootTopId}")

                        if (strNavigateRoute.isNotEmpty() && iPopUpToId != 0 ){
                            navController.navigate(strNavigateRoute) {
                                popUpTo(iPopUpToId) {
                                    saveState = true
                                }

                                //取得起始頁面(home_screen)最上層的screen
                                for (navBackStackEntry in navController.visibleEntries.value) {
                                    navBackStackEntry.destination.route?.let { strEntryRoute ->
                                        if (strEntryRoute.contains(strFindStartRoute)) {
                                            //判斷是findStartDestination，即home_screen層的screen，才紀錄
                                            strRootTopRoute = navBackStackEntry.destination.route ?: ""
                                            iRootTopId = navBackStackEntry.destination.id
                                        }
                                    }

                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

                    },
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Screens.Home.route, modifier = Modifier.padding(paddingValues = paddingValues)) { //Home
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.Home1.route) {
            SubScreen(navController, Screens.Home2.route)
        }
        composable(Screens.Home2.route) {
            SubScreen(navController, null)
        }

        //Search
        composable(Screens.Search.route) {
            SearchScreen(navController)
        }
        composable(Screens.Search1.route) {
            SubScreen(navController, Screens.Search2.route)
        }
        composable(Screens.Search2.route) {
            SubScreen(navController, Screens.Search3.route)
        }
        composable(Screens.Search3.route) {
            SubScreen(navController, null)
        }

        //Profile
        composable(Screens.Profile.route) {
            ProfileScreen(navController)
        }
    }
}
