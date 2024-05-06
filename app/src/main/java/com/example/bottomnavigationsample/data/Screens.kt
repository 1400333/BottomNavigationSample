package com.example.bottomnavigationsample.data

sealed class Screens(val route: String) {
    object Home : Screens("home_screen")        //Root
    object Home1 : Screens("home_screen1")
    object Home2 : Screens("home_screen2")
    object Search : Screens("search_screen")    //Root
    object Search1 : Screens("search_screen1")
    object Search2 : Screens("search_screen2")
    object Search3 : Screens("search_screen3")
    object Profile : Screens("profile_screen")  //Root
}