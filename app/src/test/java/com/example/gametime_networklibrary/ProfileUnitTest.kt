package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.ProfileService
import com.example.gametime_networklibrary.data.repositoryImpl.ProfileRepositoryImpl
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

class ProfileUnitTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var profileService: ProfileService
    private lateinit var profileRepository: ProfileRepositoryImpl

    @Before
    fun start() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        profileService = retrofit.create(ProfileService::class.java)
        profileRepository = ProfileRepositoryImpl(profileService)
    }

    @After
    fun stop() {
        mockWebServer.shutdown()
    }

    @Test
    fun AuthUnitTestPositive() = runTest {
        val jsonResponse = """{
  "id": 1,
  "name": "Иван Иванов",
  "email": "ivan@example.com",
  "avatar": "https://game2026.skillmad.ru/storage/avatars/s7MOoWqWkmOxwMaYrdHyQsTMyU9dhzDoOTogZLKW.png",
  "email_verified_at": "2026-04-21T08:50:38.126Z",
  "created_at": "2026-04-21T08:50:38.126Z",
  "updated_at": "2026-04-21T08:50:38.126Z",
  "is_online": true
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = profileRepository.getProfile("JWT_Token")

        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data


        assertEquals(1, data.id)
        val res = mockWebServer.takeRequest()
        assertEquals("/profile", res.path)
        assertEquals("Bearer JWT_Token", res.headers["Authorization"])
    }
}