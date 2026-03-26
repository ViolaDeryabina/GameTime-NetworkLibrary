package com.example.gametime_networklibrary.data.dataSources

import android.content.Context
import android.content.SharedPreferences
import com.example.gametime_networklibrary.domain.repository.AuthRepository

object Preferences {
    private const val PREF_NAME = "app_prefs"

    fun get(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
}