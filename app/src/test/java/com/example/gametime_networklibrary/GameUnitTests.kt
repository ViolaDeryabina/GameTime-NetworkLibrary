package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.GameService
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.game.CreateGameRequest
import com.example.gametime_networklibrary.data.repositoryImpl.GameRepositoryImpl
import com.example.gametime_networklibrary.data.repositoryImpl.UserRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.GameRepository
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

class GameUnitTests {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var gameService: GameService
    private lateinit var gameRepository: GameRepositoryImpl

    @Before
    fun start() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gameService = retrofit.create(GameService::class.java)
        gameRepository = GameRepositoryImpl(gameService)
    }

    @After
    fun stop() {
        mockWebServer.shutdown()
    }

    @Test
    fun GetCategoryPositiveTest() = runTest {
        val jsonResponse = """{
  "items": [
    {
      "collectionId": "pbc_1174553048",
      "collectionName": "category",
      "created": "2026-03-25 15:17:31.739Z",
      "id": "hf9yve2sqvkey21",
      "title": "Image",
      "updated": "2026-03-25 15:17:31.739Z"
    },
    {
      "collectionId": "pbc_1174553048",
      "collectionName": "category",
      "created": "2026-03-25 15:17:40.638Z",
      "id": "ccizut5ouju9ge6",
      "title": "Circle",
      "updated": "2026-03-25 15:17:40.638Z"
    }
  ],
  "page": 1,
  "perPage": 30,
  "totalItems": 2,
  "totalPages": 1
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = gameRepository.getListCategory("JWT_Token")
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data
        assertEquals("Image", data.items[0].title)
        assertEquals("pbc_1174553048", data.items[0].collectionId)
        assertEquals("category", data.items[0].collectionName)
        assertEquals("hf9yve2sqvkey21", data.items[0].id)
    }

    @Test
    fun GetGamePositiveTest() = runTest {
        val jsonResponse = """{
  "items": [
    {
      "category": "hf9yve2sqvkey21",
      "collectionId": "pbc_443959778",
      "collectionName": "games",
      "created": "2026-03-25 15:20:36.822Z",
      "description": "Это первая тестовая игра",
      "from": "2026-03-30 12:00:00.000Z",
      "gameName": "Image game",
      "host": "o65f9a68ithrd7n",
      "id": "x7tpsnif2gcuzvs",
      "notification": false,
      "to": "2026-03-31 12:00:00.000Z",
      "updated": "2026-03-25 15:24:38.737Z",
      "winningPrice": 9000
    }
  ],
  "page": 1,
  "perPage": 30,
  "totalItems": 3,
  "totalPages": 1
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = gameRepository.getListGame("JWT_Token")
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data
        assertEquals("hf9yve2sqvkey21", data.items[0].category)
        assertEquals("pbc_443959778", data.items[0].collectionId)
        assertEquals("games", data.items[0].collectionName)
        assertEquals("x7tpsnif2gcuzvs", data.items[0].id)
    }

    @Test
    fun CreateGamePositiveTest() = runTest {
        val jsonResponse = """{
  "category": "hf9yve2sqvkey21",
  "collectionId": "pbc_443959778",
  "collectionName": "games",
  "created": "2026-03-26 08:33:40.350Z",
  "description": "test",
  "from": "2022-01-01 10:00:00.123Z",
  "gameName": "test",
  "host": "o7zymg6sv8shmgw",
  "id": "bls1g4bvh28ej6y",
  "notification": true,
  "to": "2022-01-01 10:00:00.123Z",
  "updated": "2026-03-26 08:33:40.350Z",
  "winningPrice": 123
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val request = CreateGameRequest(
            gameName = "test",
            category = "hf9yve2sqvkey21",
            winningPrice = 123,
            from = "2022-01-01 10:00:00.123Z",
            to = "2022-01-01 10:00:00.123Z",
            description = "test",
            notification = true,
            host = "o7zymg6sv8shmgw"
        )
        val result = gameRepository.createGame("JWT_Token", request)
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data
        assertEquals("hf9yve2sqvkey21", data.category)
        assertEquals("games", data.collectionName)

        assertEquals("test", data.gameName)
        assertEquals(123, data.winningPrice)
    }

    @Test
    fun GetIngormtionGameById() = runTest {
        val jsonResponse = """{
  "category": "hf9yve2sqvkey21",
  "collectionId": "pbc_443959778",
  "collectionName": "games",
  "created": "2026-03-25 15:20:36.822Z",
  "description": "Это первая тестовая игра",
  "expand": {
    "category": {
      "collectionId": "pbc_1174553048",
      "collectionName": "category",
      "created": "2026-03-25 15:17:31.739Z",
      "id": "hf9yve2sqvkey21",
      "title": "Image",
      "updated": "2026-03-25 15:17:31.739Z"
    },
    "host": {
      "collectionId": "pbc_3724789644",
      "collectionName": "players",
      "created": "2026-03-25 15:24:21.176Z",
      "emailVisibility": false,
      "fullName": "Olga",
      "id": "o65f9a68ithrd7n",
      "phone": "+2438987654534",
      "photo": "",
      "updated": "2026-03-25 15:24:21.176Z",
      "userName": "olga",
      "verified": false
    }
  },
  "from": "2026-03-30 12:00:00.000Z",
  "gameName": "Image game",
  "host": "o65f9a68ithrd7n",
  "id": "x7tpsnif2gcuzvs",
  "notification": false,
  "to": "2026-03-31 12:00:00.000Z",
  "updated": "2026-03-25 15:24:38.737Z",
  "winningPrice": 9000
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = gameRepository.getInformationGameByiId("JWT_Token", "x7tpsnif2gcuzvs")
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals("Image", data.expand?.category?.title)
        assertEquals("games", data.collectionName)
        assertEquals("hf9yve2sqvkey21", data.category)
    }
}