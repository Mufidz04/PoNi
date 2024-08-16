package com.mc0dex.poni.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mc0dex.poni.data.room.repository.AlumniRepository

class AlumniViewModelFactory(private val repository: AlumniRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlumniViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlumniViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
