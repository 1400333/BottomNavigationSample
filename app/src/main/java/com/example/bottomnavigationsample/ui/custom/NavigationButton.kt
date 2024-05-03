package com.example.bottomnavigationsample.ui.custom

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun NavigationButton(navController: NavController,
                     destination: String?,
                     modifier: Modifier = Modifier) {
    destination?.let {
        Button(onClick = {
            //Log.d("RDLog", "Navigate to screen ${it}")
            navController.navigate(it)
        }, modifier = modifier.fillMaxWidth()) {
            Text(text = "Go to ${it}")
        }
    }
}