package com.mc0dex.poni.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mc0dex.poni.data.room.models.AlumniItem

@Dao
interface AlumniDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alumni: AlumniItem)

    @Update
    suspend fun update(alumni: AlumniItem)

    @Delete
    suspend fun delete(alumni: AlumniItem)

    @Query("SELECT * FROM alumni")
    fun getAllAlumni(): LiveData<List<AlumniItem>>

    @Query("SELECT * FROM alumni WHERE id = :id")
    fun getAlumniById(id: Int): LiveData<AlumniItem>
}
