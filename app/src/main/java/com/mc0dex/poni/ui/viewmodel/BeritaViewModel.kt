package com.mc0dex.poni.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mc0dex.poni.data.room.models.BeritaItem
import com.mc0dex.poni.data.room.repository.BeritaRepository
import kotlinx.coroutines.launch

class BeritaViewModel(private val repository: BeritaRepository) : ViewModel() {

    private val _beritaList = MutableLiveData<List<BeritaItem>>()
    val beritaList: LiveData<List<BeritaItem>> = _beritaList

    init {
        fetchAllBerita()
    }

    private fun fetchAllBerita() {
        viewModelScope.launch {
            _beritaList.value = repository.getAllBerita()
        }
    }

    fun getBeritaById(id: Int): LiveData<BeritaItem?> {
        val berita = MutableLiveData<BeritaItem?>()
        viewModelScope.launch {
            berita.value = repository.getBeritaById(id)
        }
        return berita
    }

    fun addBerita(beritaItem: BeritaItem) {
        viewModelScope.launch {
            repository.insertBerita(beritaItem)
            fetchAllBerita() // Refresh the list after inserting
        }
    }
}
