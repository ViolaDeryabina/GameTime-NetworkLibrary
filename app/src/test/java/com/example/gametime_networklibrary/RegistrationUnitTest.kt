package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
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
  "message": "Пользователь успешно зарегистрирован",
  "user": {
    "name": "Виолетта",
    "email": "violaDer@gmail.com",
    "firstname": "Violetta",
    "lastname": "Deryabina",
    "phone": "+79201234556",
    "updated_at": "2026-04-21T06:02:31.000000Z",
    "created_at": "2026-04-21T06:02:31.000000Z",
    "id": 362,
    "is_online": false
  },
  "token": "860|P2aHGMWcTbobtdvyyktIpbfUTadTmMlNxA6dQBSDc6b0ea5c"
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val request = RegisterRequest(
            "Виолетта",
            "violaDer@gmail.com",
            "password",
            "password",
            "Violetta",
            "Deryabina",
            phone = "+79201234556"
        )

        val result = userRepository.registration(request)

        assertTrue(result is NetworkResult.Success)

        val data = (result as NetworkResult.Success).data
        assertEquals("Виолетта", data.user.name)
        assertEquals("violaDer@gmail.com", data.user.email)
        assertEquals(false, data.user.isOnline)

        val res = mockWebServer.takeRequest()
        assertEquals("/register", res.path)

        //assertEquals("Bearer secret-token", res.headers["Authorization"])
    }
}