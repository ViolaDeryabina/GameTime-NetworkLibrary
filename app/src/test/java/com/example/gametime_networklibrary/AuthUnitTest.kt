package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.AuthRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.repositoryImpl.UserRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import jakarta.inject.Inject
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class AuthUnitTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userRepository: UserRepositoryImpl

    @Before
    fun start() {
        hiltRule.inject()
    }

    @Test
    fun AuthUnitTestPositive() = runTest {

        val email = "test_${System.currentTimeMillis()}@gmail.com"
        val request = RegisterRequest(
            "Виолетта",
            email,
            "password",
            "password",
            "Violetta",
            "Deryabina",
            phone = "+79201234556"
        )

        val resultRegister = userRepository.registration(request)

        assertTrue(resultRegister is NetworkResult.Success)

        val dataRegister = (resultRegister as NetworkResult.Success).data
        assertEquals("Виолетта", dataRegister.user.name)

        val auth = AuthRequest(
            email = email,
            password = "password"
        )

        val result = userRepository.authorization(auth)

        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals(
            email,
            data.user.email
        )


    }

}