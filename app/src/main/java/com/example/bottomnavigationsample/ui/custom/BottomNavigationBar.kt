package com.example.bottomnavigationsample.ui.custom

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationsample.data.BottomNavigationItem
import com.example.bottomnavigationsample.data.Screens
import com.example.bottomnavigationsample.ui.screen.HomeScreen
import com.example.bottomnavigationsample.ui.screen.ProfileScreen
import com.example.bottomnavigationsample.ui.screen.SearchScreen
import com.example.bottomnavigationsample.ui.screen.SubScreen
import kotlinx.coroutines.flow.forEach

const val HOME_GRAPH_ROUTE = "Home"
const val SEARCH_GRAPH_ROUTE = "Search"
const val PRODILE_GRAPH_ROUTE = "Profile"

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var iID by remember { mutableStateOf(0) }
    var strRoute by remember { mutableStateOf("") }
    var iTargetId by remember { mutableStateOf(0) }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar {
            BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                NavigationBarItem(
                    selected = currentDestination?.route?.contains(navigationItem.route)?:false,
                    label = {
                        Text(navigationItem.label)
                    },
                    icon = {
                        Icon(navigationItem.icon, contentDescription = navigationItem.label)
                    },
                    onClick = {
                        if (navigationItem.route == navController.graph.findStartDestination().route){
                            iID = navController.graph.findStartDestination().id
                        } else{
                            for(navBackStackEntry in navController.visibleEntries.value){
                                if (navBackStackEntry.destination.route?.contains(navController.graph.findStartDestination()?.route?:"")?:false){
                                    iID = navBackStackEntry.destination.id
                                }
                            }
                        }

                        var strTargetRoute = navigationItem.route
                        if (strRoute.contains(Screens.Home.route) &&  navigationItem.route.contains(Screens.Home.route)){
                            iID =  iTargetId
                            strTargetRoute =strRoute
                        }
                        Log.d("RDLog","[route]${strRoute} [strTargetRoute] ${strTargetRoute}")

                        navController.navigate(strTargetRoute) {
                            popUpTo(iID) {
                                saveState = true
                            }

                            for(navBackStackEntry in navController.visibleEntries.value){
                                if (navBackStackEntry.destination.route?.contains(navController.graph.findStartDestination()?.route?:"")?:false){
                                    strRoute = navBackStackEntry.destination.route?:""
                                    iTargetId = navBackStackEntry.destination.id
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }) { paddingValues ->
        NavHost(navController = navController,
                startDestination = Screens.Home.route,
                modifier = Modifier.padding(paddingValues = paddingValues)) {
//            //Home
//            homeNavGraph(navController = navController)
            composable(Screens.Home.route) {
                HomeScreen(navController)
            }
            composable(Screens.Home1.route) {
                SubScreen(navController, Screens.Home2.route)
            }
            composable(Screens.Home2.route) {
                SubScreen(navController, null)
            }
//            //Search
//            searchNavGraph(navController = navController)
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
//            //Profile
//            profileNavGraph(navController = navController)
            composable(Screens.Profile.route) {
                ProfileScreen(navController)
            }
        }
    }
}


fun NavGraphBuilder.homeNavGraph(navController: NavHostController){
    navigation(
        startDestination = Screens.Home.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.Home1.route) {
            SubScreen(navController, Screens.Home2.route)
        }
        composable(Screens.Home2.route) {
            SubScreen(navController, null)
        }
    }
}

fun NavGraphBuilder.searchNavGraph(navController: NavHostController){
    navigation(
        startDestination = Screens.Search.route,
        route = SEARCH_GRAPH_ROUTE
    ) {
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
    }
}

fun NavGraphBuilder.profileNavGraph(navController: NavHostController){
    navigation(
        startDestination = Screens.Profile.route,
        route = PRODILE_GRAPH_ROUTE
    ) {
        composable(Screens.Profile.route) {
            ProfileScreen(navController)
        }
    }
}