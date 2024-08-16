package com.mc0dex.poni.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumni")
data class AlumniItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "nim")
    val nim: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "tempat_lahir")
    val tempatLahir: String = "",

    @ColumnInfo(name = "tanggal_lahir")
    val tanggalLahir: String = "",  // Consider using String if this is a date

    @ColumnInfo(name = "alamat")
    val alamat: String = "",

    @ColumnInfo(name = "agama")
    val agama: String = "",

    @ColumnInfo(name = "no_hp")
    val noHp: String = "",  // Changed to String for phone numbers

    @ColumnInfo(name = "email")
    val email: String = "",

    @ColumnInfo(name = "tahun_masuk")
    val tahunMasuk: Int = 0,

    @ColumnInfo(name = "tahun_lulus")
    val tahunLulus: Int = 0,

    @ColumnInfo(name = "pekerjaan")
    val pekerjaan: String = "",

    @ColumnInfo(name = "jabatan")
    val jabatan: String = ""
)
