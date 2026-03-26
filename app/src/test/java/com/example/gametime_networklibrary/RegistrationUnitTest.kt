package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.registration.RegistrationRequest
import com.example.gametime_networklibrary.data.repositoryImpl.UserRepositoryImpl
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationUnitTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userService: UserService
    private lateinit var userRepository: UserRepositoryImpl

    @Before
    fun start() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(UserService::class.java)
        userRepository = UserRepositoryImpl(userService)
    }

    @After
    fun stop() {
        mockWebServer.shutdown()
    }

    @Test
    fun AuthUnitTestPositive() = runTest {
        val jsonResponse = """{
  "collectionId": "pbc_3724789644",
  "collectionName": "players",
  "created": "2026-03-26 07:24:22.034Z",
  "emailVisibility": false,
  "fullName": "test",
  "id": "4c2vymvnep7h8yl",
  "phone": "test",
  "photo": "",
  "updated": "2026-03-26 07:24:22.034Z",
  "userName": "test",
  "verified": false
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val request = RegistrationRequest("viola@test.ru", "password", "password","test", "test", "test")
        val result = userRepository.registration(request)

        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals("4c2vymvnep7h8yl", data.id)
        assertEquals("test", data.userName)
    }
}