package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.AuthRequest
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

class AuthUnitTest {

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
  "message": "Вход выполнен успешно",
  "user": {
    "id": 362,
    "name": "Иван Иванов",
    "email": "useruser@example.com",
    "email_verified_at": null,
    "created_at": "2026-04-21T06:02:31.000000Z",
    "updated_at": "2026-04-21T06:02:31.000000Z",
    "avatar": null,
    "firstname": "Иван",
    "lastname": "Иванов",
    "phone": "+79991234567",
    "is_online": false
  },
  "token": "862|04cNYW7SugLAWl8k3Z3WSWIQx9SLy7D3WLtlBop6ee662270"
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val request = AuthRequest("useruser@example.com", "password")
        val result = userRepository.authorization(request)

        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb2xsZWN0aW9uSWQiOiJwYmNfMzcyNDc4OTY0NCIsImV4cCI6MTc3NTExMzY2NCwiaWQiOiJvN3p5bWc2c3Y4c2htZ3ciLCJyZWZyZXNoYWJsZSI6dHJ1ZSwidHlwZSI6ImF1dGgifQ.ZPwPr30IgA37du25trBbJVmBDIgV2dRNnyzA3Dlarkc",
            data.token
        )


    }

}