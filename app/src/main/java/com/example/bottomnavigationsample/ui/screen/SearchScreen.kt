package com.example.bottomnavigationsample.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationsample.R
import com.example.bottomnavigationsample.data.Screens
import com.example.bottomnavigationsample.ui.custom.NavigationButton
import com.example.bottomnavigationsample.ui.theme.BottomNavigationSampleTheme

@Composable
fun SearchScreen(navController: NavController) {
    BottomNavigationSampleTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clip(MaterialTheme.shapes.large)) {
                    Image(painter = painterResource(R.drawable.two),
                          contentDescription = "search_screen_bg",
                          contentScale = ContentScale.Crop,
                          modifier = Modifier.fillMaxSize())
                }
                Text("Search Screen",
                     style = MaterialTheme.typography.titleLarge,
                     modifier = Modifier.padding(vertical = 20.dp))

                NavigationButton(navController = navController, destination = Screens.Search1.route)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    val navController = rememberNavController()

    SearchScreen(navController)
}