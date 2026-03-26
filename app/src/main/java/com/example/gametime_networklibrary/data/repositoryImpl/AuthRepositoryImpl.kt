package com.example.gametime_networklibrary.data.repositoryImpl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.gametime_networklibrary.domain.repository.AuthRepository

class AuthRepositoryImpl(private val prefs: SharedPreferences): AuthRepository {
    companion object{
        private const val KEY_TOKEN = "user_token"
    }

    override fun saveToken(token: String){
        prefs.edit { putString(KEY_TOKEN, token) }
    }
    override fun deleteToken() {
        prefs.edit { remove(KEY_TOKEN) }
    }
    override fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

}