package com.mc0dex.poni.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mc0dex.poni.data.room.models.BeritaItem

@Dao
interface BeritaDao {
    @Query("SELECT * FROM berita")
    fun getAllBerita(): LiveData<List<BeritaItem>>

    @Query("SELECT * FROM berita WHERE id = :id")
    fun getBeritaById(id: Int): LiveData<BeritaItem?>

    @Insert
    suspend fun insertBerita(beritaItem: BeritaItem)
}
