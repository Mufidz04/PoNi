package com.mc0dex.poni.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mc0dex.poni.R
import com.mc0dex.poni.data.room.database.ProfileStorage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    // Access the current context
    val context = LocalContext.current

    // Create an instance of ProfileStorage
    val profileStorage = remember { ProfileStorage(context) }

    // Load profile data
    val profile = remember { profileStorage.loadProfile() }

    // Dropdown menu state
    var expanded by remember { mutableStateOf(false) }

    // Function to handle logout
    val handleLogout = {
        profileStorage.logout()
        navController.navigate("login_screen") {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("") // Empty title
                },

                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false  })
                    {
                        // Add dropdown items here
                        DropdownMenuItem(text = { Text(text = "Tambah Data") }, onClick = { navController.navigate("add_alumni_data") })
                        DropdownMenuItem(text = { Text(text = "Data Alumni") }, onClick = { navController.navigate("alumniList") })
                        DropdownMenuItem(text = { Text(text = "Logout") }, onClick = handleLogout)
                    }
                },

                )
        },
        bottomBar = {
            BottomAppBar(
                contentColor = MaterialTheme.colorScheme.primary,
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp), // Adjust horizontal padding
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Adjust spacing between icons with Spacer
                        IconButton(onClick = { navController.navigate("home_screen") }) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        }
                        Spacer(modifier = Modifier.width(32.dp)) // Space between icons
                        IconButton(onClick = { navController.navigate("berita_list_screen") }) {
                            Icon(Icons.Default.Email, contentDescription = "Berita")
                        }
                        Spacer(modifier = Modifier.width(32.dp)) // Space between icons
                        IconButton(onClick = { navController.navigate("profile_screen") }) {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        }
                    }
                }
            )
        },
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(painter = painterResource(id = R.drawable.logoponi), contentDescription = ("logo"))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                textAlign = TextAlign.Center
            )
        }
    }
}