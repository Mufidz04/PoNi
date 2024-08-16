package com.mc0dex.poni.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mc0dex.poni.data.room.dao.AlumniDao
import com.mc0dex.poni.data.room.dao.BeritaDao
import com.mc0dex.poni.data.room.models.AlumniItem
import com.mc0dex.poni.data.room.models.BeritaItem

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Example migration SQL
        // database.execSQL("ALTER TABLE AlumniItem ADD COLUMN new_column_name TEXT")
    }
}

@Database(entities = [AlumniItem::class, BeritaItem::class], version = 2, exportSchema = false)
abstract class PoniDatabase : RoomDatabase() {

    abstract fun alumniDao(): AlumniDao
    abstract fun beritaDao(): BeritaDao


    companion object {
        @Volatile
        private var INSTANCE: PoniDatabase? = null

        fun getDatabase(context: Context): PoniDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PoniDatabase::class.java,
                    "poni_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
