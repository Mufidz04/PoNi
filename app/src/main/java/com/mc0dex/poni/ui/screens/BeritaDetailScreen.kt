package com.mc0dex.poni.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

import com.mc0dex.poni.R
import com.mc0dex.poni.data.room.repository.BeritaRepository
import com.mc0dex.poni.data.room.database.PoniDatabase
import com.mc0dex.poni.data.room.database.ProfileStorage
import com.mc0dex.poni.ui.viewmodel.BeritaViewModel
import com.mc0dex.poni.ui.viewmodel.BeritaViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeritaDetailScreen(
    navController: NavController,
    beritaId: Int,
    viewModel: BeritaViewModel = viewModel(
        factory = BeritaViewModelFactory(
            BeritaRepository(PoniDatabase.getDatabase(navController.context).beritaDao())
        )
    )
) {
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

    // Observe the data
    val beritaItem by viewModel.getBeritaById(beritaId).observeAsState()

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
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Tambah Data") },
                            onClick = { navController.navigate("add_alumni_data") }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Data Alumni") },
                            onClick = { navController.navigate("alumniList") }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Logout") },
                            onClick = handleLogout,
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                contentColor = MaterialTheme.colorScheme.primary,
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = { navController.navigate("home_screen") }) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        }
                        Spacer(modifier = Modifier.width(32.dp))
                        IconButton(onClick = { navController.navigate("berita_list_screen") }) {
                            Icon(Icons.Default.Email, contentDescription = "Berita")
                        }
                        Spacer(modifier = Modifier.width(32.dp))
                        IconButton(onClick = { navController.navigate("profile_screen") }) {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        beritaItem?.let { item ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = item.content,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } ?: run {
            // Show a loading indicator or placeholder while fetching data
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}
