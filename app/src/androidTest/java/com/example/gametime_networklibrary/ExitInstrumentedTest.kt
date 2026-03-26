package com.example.gametime_networklibrary

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gametime_networklibrary.data.dataSources.Preferences
import com.example.gametime_networklibrary.data.repositoryImpl.AuthRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.AuthRepository

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExitInstrumentedTest {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var repository: AuthRepository

    @Test
    fun exitPositiveTest() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sharedPreferences = Preferences.get(appContext)

        repository = AuthRepositoryImpl(sharedPreferences)

        repository.saveToken("123")
        repository.deleteToken()

        val result =repository.getToken()
        assertEquals(null, result)
    }
}