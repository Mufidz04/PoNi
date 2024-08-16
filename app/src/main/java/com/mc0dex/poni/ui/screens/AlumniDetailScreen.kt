package com.mc0dex.poni.ui.screens

import com.mc0dex.poni.data.room.database.PoniDatabase
import com.mc0dex.poni.ui.viewmodel.AlumniViewModel
import com.mc0dex.poni.ui.viewmodel.AlumniViewModelFactory

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavBackStackEntry
import com.mc0dex.poni.data.room.repository.AlumniRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlumniDetailScreen(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    viewModel: AlumniViewModel = viewModel(
        factory = AlumniViewModelFactory(
            AlumniRepository(PoniDatabase.getDatabase(navController.context).alumniDao())
        )
    )
) {

    // Get the alumni ID from the arguments
    val alumniId = backStackEntry.arguments?.getString("alumniId")?.toIntOrNull() ?: return
    // Observe the alumni details from the ViewModel
    val alumni = viewModel.getAlumniById(alumniId).observeAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Data Alumni") }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp) // Add padding to the content
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 60.dp) // Reserve space for buttons
                ) {
                    alumni.value?.let { alumniItem ->
                        Column(
                            modifier = Modifier.weight(1f) // Use weight to push buttons to the bottom
                        ) {
                            Text(
                                text = alumniItem.name,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the name
                            )
                            Text(
                                text = "NIM: ${alumniItem.nim}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the NIM
                            )
                            Text(
                                text = "Tempat Lahir: ${alumniItem.tempatLahir}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the place of birth
                            )
                            Text(
                                text = "Tanggal Lahir: ${alumniItem.tanggalLahir}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the date of birth
                            )
                            Text(
                                text = "Alamat: ${alumniItem.alamat}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the address
                            )
                            Text(
                                text = "Agama: ${alumniItem.agama}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the religion
                            )
                            Text(
                                text = "No. HP: ${alumniItem.noHp}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the phone number
                            )
                            Text(
                                text = "Tahun Masuk: ${alumniItem.tahunMasuk}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the description
                            )
                            Text(
                                text = "Tahun Lulus: ${alumniItem.tahunLulus}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the education
                            )
                            Text(
                                text = "Pekerjaan: ${alumniItem.pekerjaan}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the job
                            )
                            Text(
                                text = "Jabatan: ${alumniItem.jabatan}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below the position
                            )
                        }
                    } ?: run {
                        // Optional: Display a placeholder or error message if no data is found
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Alumni details not found",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                // Add buttons
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Align buttons to the bottom
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp), // Add padding around the buttons
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            // Handle edit action here
                            navController.navigate("editAlumni/${alumniId}")
                        },
                        modifier = Modifier.weight(1f) // Make buttons take equal space
                    ) {
                        Text("Edit")
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between buttons
                    Button(
                        onClick = {
                            alumni.value?.let { item ->
                                // Handle delete action here
                                viewModel.delete(item) // Call delete method from ViewModel
                                navController.popBackStack() // Navigate back to previous screen
                            }
                        },
                        modifier = Modifier.weight(1f) // Make buttons take equal space
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    )
}