package com.mc0dex.poni.ui.viewmodel

import androidx.lifecycle.*
import com.mc0dex.poni.data.room.models.AlumniItem
import com.mc0dex.poni.data.room.repository.AlumniRepository
import kotlinx.coroutines.launch

class AlumniViewModel(private val repository: AlumniRepository) : ViewModel() {

    val allAlumni: LiveData<List<AlumniItem>> = repository.allAlumni

    fun insert(alumni: AlumniItem) = viewModelScope.launch {
        repository.insert(alumni)
    }

    fun update(alumni: AlumniItem) = viewModelScope.launch {
        repository.update(alumni)
    }

    fun delete(alumni: AlumniItem) = viewModelScope.launch {
        repository.delete(alumni)
    }

    fun getAlumniById(id: Int): LiveData<AlumniItem> {
        return repository.getAlumniById(id)
    }
}
