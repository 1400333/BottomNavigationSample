package com.example.bottomnavigationsample.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bottomnavigationsample.ui.custom.NavigationButton
import com.example.bottomnavigationsample.ui.theme.BottomNavigationSampleTheme

@Composable
fun SubScreen(navController: NavController, destination: String?) {
    BottomNavigationSampleTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier
                .fillMaxHeight()) {
                MyScreenTitle(title = "前往${destination}")
                NavigationButton(navController = navController, destination = destination)
            }
        }
    }
}

@Composable
fun MyScreenTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title,
         modifier = modifier
             .fillMaxWidth()
             .padding(top = 16.dp, bottom = 16.dp),
         textAlign = TextAlign.Center)
}