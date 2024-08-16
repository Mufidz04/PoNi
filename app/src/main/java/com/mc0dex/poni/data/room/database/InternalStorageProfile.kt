package com.mc0dex.poni.data.room.database

import android.content.Context
import com.mc0dex.poni.R


data class DefaultProfile(val name: String, val email: String, val password: String, val kelas: String) {
    companion object {
        fun createDefaultProfile(context: Context) : DefaultProfile {
            return DefaultProfile(
                context.getString(R.string.nama),
                context.getString(R.string.email),
                context.getString(R.string.password),
                context.getString(R.string.kelas)
            )
        }
    }
}

data class ProfileInternal(val name: String, val email: String, val password: String, val kelas: String)

class ProfileStorage(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private val defaultProfile = DefaultProfile.createDefaultProfile(context)

    fun saveProfile(profile: ProfileInternal) {
        with(editor) {
            putString("name", profile.name)
            putString("email", profile.email)
            // **Never store password in plain text!** Use secure storage or encryption.
            putString("password", profile.password)
            putString("kelas", profile.kelas)
            apply()
        }
    }

    fun loadProfile(): ProfileInternal {
        val name = sharedPreferences.getString("name", defaultProfile.name)!!
        val email = sharedPreferences.getString("email", defaultProfile.email)!!
        val password = sharedPreferences.getString("password", defaultProfile.password)!!
        val kelas = sharedPreferences.getString("kelas", defaultProfile.kelas)!!
        return ProfileInternal(name, email, password, kelas)
    }

    // New function for validation (basic, not secure)
    fun validateLogin(email: String, password: String): Boolean {
        val storedProfile = loadProfile()
        return storedProfile.email == email && storedProfile.password == password
    }

    // New function for logout
    fun logout() {
        with(editor) {
            clear() // This will remove all saved data
            apply()
        }
    }
}