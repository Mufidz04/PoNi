package com.mc0dex.poni.data.room.repository

import com.mc0dex.poni.data.dummy.DummyData

import com.mc0dex.poni.data.room.dao.BeritaDao
import com.mc0dex.poni.data.room.models.BeritaItem

class BeritaRepository(private val beritaDao: BeritaDao) {

    // Replace this with actual database call when not using dummy data
    suspend fun getAllBerita(): List<BeritaItem> {
        return DummyData.getDummyBeritaItems() // Use dummy data for testing
    }

    // Replace this with actual database call when not using dummy data
    suspend fun getBeritaById(id: Int): BeritaItem? {
        return DummyData.getDummyBeritaItems().find { it.id == id }
    }

    suspend fun insertBerita(beritaItem: BeritaItem) {
        // This method would interact with the database in a real implementation
    }
}
