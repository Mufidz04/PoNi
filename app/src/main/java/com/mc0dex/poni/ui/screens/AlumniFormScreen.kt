package com.mc0dex.poni.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.mc0dex.poni.data.room.models.AlumniItem
import com.mc0dex.poni.ui.viewmodel.AlumniViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mc0dex.poni.ui.viewmodel.AlumniViewModelFactory
import com.mc0dex.poni.data.room.repository.AlumniRepository
import com.mc0dex.poni.data.room.database.PoniDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlumniFormScreen(
    navController: NavController,
    alumniId: Int? = null,
    viewModel: AlumniViewModel = viewModel(
        factory = AlumniViewModelFactory(
            AlumniRepository(PoniDatabase.getDatabase(navController.context).alumniDao())
        )
    )
) {
    // State variables to hold the input data
    val alumni = alumniId?.let { viewModel.getAlumniById(it).observeAsState() }?.value
    var nim by remember { mutableStateOf("") }
    var namaAlumni by remember { mutableStateOf("") }
    var tempatLahir by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var agama by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var tahunMasuk by remember { mutableStateOf("") }
    var tahunLulus by remember { mutableStateOf("") }
    var pekerjaan by remember { mutableStateOf("") }
    var jabatan by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Coroutine scope for launching suspend functions
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Add Data Alumni") }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    OutlinedTextField(
                        value = nim,
                        onValueChange = { nim = it },
                        label = { Text("NIM") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    OutlinedTextField(
                        value = namaAlumni,
                        onValueChange = { namaAlumni = it },
                        label = { Text("Nama Alumni") }
                    )
                }
                item {
                    OutlinedTextField(
                        value = tempatLahir,
                        onValueChange = { tempatLahir = it },
                        label = { Text("Tempat Lahir") }
                    )
                }
                item {
                    OutlinedTextField(
                        value = tanggalLahir,
                        onValueChange = { tanggalLahir = it },
                        label = { Text("Tanggal Lahir") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("Format: yyyyMMdd") }
                    )
                }
                item {
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Address") }
                    )
                }
                item {
                    OutlinedTextField(
                        value = agama,
                        onValueChange = { agama = it },
                        label = { Text("Agama") }
                    )
                }
                item {
                    OutlinedTextField(
                        value = noHp,
                        onValueChange = { noHp = it },
                        label = { Text("No HP") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    OutlinedTextField(
                        value = tahunMasuk,
                        onValueChange = { tahunMasuk = it },
                        label = { Text("Tahun Masuk") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    OutlinedTextField(
                        value = tahunLulus,
                        onValueChange = { tahunLulus = it },
                        label = { Text("Tahun Lulus") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    OutlinedTextField(
                        value = pekerjaan,
                        onValueChange = { pekerjaan = it },
                        label = { Text("Pekerjaan") }
                    )
                }
                item {
                    OutlinedTextField(
                        value = jabatan,
                        onValueChange = { jabatan = it },
                        label = { Text("Jabatan") }
                    )
                }
                item {
                    errorMessage?.let { message ->
                        Text(
                            text = message,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (nim.isBlank() || namaAlumni.isBlank() || tempatLahir.isBlank() ||
                                tanggalLahir.isBlank() || address.isBlank() || agama.isBlank() ||
                                noHp.isBlank() || tahunMasuk.isBlank() || tahunLulus.isBlank() ||
                                pekerjaan.isBlank() || jabatan.isBlank()
                            ) {
                                errorMessage = "All fields must be filled!"
                            } else {
                                val alumniItem = AlumniItem(
                                    nim = nim.toIntOrNull() ?: 0,
                                    name = namaAlumni,
                                    tempatLahir = tempatLahir,
                                    tanggalLahir = (tanggalLahir.toIntOrNull() ?: 0).toString(),
                                    alamat = address,
                                    agama = agama,
                                    noHp = (noHp.toIntOrNull() ?: 0).toString(),
                                    tahunMasuk = tahunMasuk.toIntOrNull() ?: 0,
                                    tahunLulus = tahunLulus.toIntOrNull() ?: 0,
                                    pekerjaan = pekerjaan,
                                    jabatan = jabatan
                                )

                                coroutineScope.launch {
                                    try {
                                        viewModel.insert(alumniItem)
                                        navController.popBackStack()
                                    } catch (e: Exception) {
                                        errorMessage = "Failed to save data: ${e.localizedMessage}"
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    )
}