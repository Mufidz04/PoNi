package com.mc0dex.poni.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mc0dex.poni.R
import kotlinx.coroutines.delay
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000) // Short delay before navigating to LoginScreen
        navController.navigate("login_screen") {
            // Ensure splash screen is removed from the back stack
            popUpTo("splash_screen") { inclusive = true }
        }
    }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logoponi), contentDescription = ("logo"))
            Text(text = "Final Project Digitalent Kominfo", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Selamat datang di Portal Alumni!", style = MaterialTheme.typography.bodyLarge)
        }
    }
}