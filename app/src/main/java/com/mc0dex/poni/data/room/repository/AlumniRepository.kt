package com.mc0dex.poni.data.room.repository

import androidx.lifecycle.LiveData
import com.mc0dex.poni.data.room.dao.AlumniDao
import com.mc0dex.poni.data.room.models.AlumniItem

class AlumniRepository(private val alumniDao: AlumniDao) {

    val allAlumni: LiveData<List<AlumniItem>> = alumniDao.getAllAlumni()

    suspend fun insert(alumni: AlumniItem) {
        alumniDao.insert(alumni)
    }

    suspend fun update(alumni: AlumniItem) {
        alumniDao.update(alumni)
    }

    suspend fun delete(alumni: AlumniItem) {
        alumniDao.delete(alumni)
    }

    fun getAlumniById(id: Int): LiveData<AlumniItem> {
        return alumniDao.getAlumniById(id)
    }
}
