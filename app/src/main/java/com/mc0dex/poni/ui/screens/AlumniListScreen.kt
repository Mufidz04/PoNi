package com.mc0dex.poni.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mc0dex.poni.data.room.database.PoniDatabase
import com.mc0dex.poni.data.room.models.AlumniItem
import com.mc0dex.poni.data.room.repository.AlumniRepository
import com.mc0dex.poni.ui.viewmodel.AlumniViewModel
import com.mc0dex.poni.ui.viewmodel.AlumniViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlumniListScreen(
    navController: NavController,
    viewModel: AlumniViewModel = viewModel(
        factory = AlumniViewModelFactory(
            AlumniRepository(PoniDatabase.getDatabase(navController.context).alumniDao())
        )
    )
) {
    // Observe the list of alumni from the ViewModel
    val alumniList = viewModel.allAlumni.observeAsState(emptyList())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Alumni List") }
            )
        },
        content = { innerPadding ->
            // Check if the alumni list is empty
            if (alumniList.value.isEmpty()) {
                // Display "Data not available" message in the center
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding), // Use padding provided by Scaffold
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        text = "Data not available",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else {
                // Display the list of alumni in a LazyColumn
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = innerPadding.calculateTopPadding(), // Use the top padding provided by Scaffold
                        bottom = innerPadding.calculateBottomPadding() // Use the bottom padding provided by Scaffold
                    ),
                ) {
                    items(alumniList.value) { alumni ->
                        AlumniItemRow(alumni) {
                            navController.navigate("alumniDetail/${alumni.id}")
                        }
                        // Add a Divider below each item except the last one
                        HorizontalDivider()
                    }
                }
            }
        }
    )
}

@Composable
fun AlumniItemRow(alumni: AlumniItem, onItemClick: () -> Unit) {
    // Display each alumni item as a clickable row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(vertical = 8.dp) // Add vertical padding between rows
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp) // Add padding for text
        ) {
            Text(
                text = alumni.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "NIM: ${alumni.nim}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun HorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Add padding around the divider
    )
}