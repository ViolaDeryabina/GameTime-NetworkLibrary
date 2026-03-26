package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.UpdateProfileRequest
import com.example.gametime_networklibrary.data.dto.auth.AuthRequest
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

class UpdateProfileUnitTest {
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
    fun UpdateProfileUnitTestPositive() = runTest {
        val jsonResponse = """{
  "collectionId": "pbc_3724789644",
  "collectionName": "players",
  "created": "2026-03-25 19:22:06.044Z",
  "email": "user@example.com",
  "emailVisibility": false,
  "fullName": "test2",
  "id": "o7zymg6sv8shmgw",
  "phone": "test2",
  "photo": "",
  "updated": "2026-03-26 07:39:03.226Z",
  "userName": "test2",
  "verified": false
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val request = UpdateProfileRequest("test2", "test2","test2","")
        val result = userRepository.updateProfile("JWT_Token","o7zymg6sv8shmgw",request)

        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data


        assertEquals("test2", data.userName)
    }
}