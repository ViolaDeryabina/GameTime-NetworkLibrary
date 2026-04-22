package com.example.gametime_networklibrary.data.repositoryImpl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.gametime_networklibrary.domain.repository.AuthRepository

class AuthRepositoryImpl(private val prefs: SharedPreferences): AuthRepository {
    companion object{
        private const val KEY_TOKEN = "user_token"
        private const val KEY_DATA = "user_phone"
    }

    override fun saveToken(token: String){
        prefs.edit { putString(KEY_TOKEN, token) }
    }
    override fun deleteToken() {
        prefs.edit { remove(KEY_TOKEN) }
    }

    override fun getPhone(): String? = prefs.getString(KEY_DATA, null)

    override fun savePhone(phone: String) {
        prefs.edit { putString(KEY_DATA, phone) }
    }

    override fun deleteData() {
        prefs.edit { remove(KEY_DATA) }
    }

    override fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

}