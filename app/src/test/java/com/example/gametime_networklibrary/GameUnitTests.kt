package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.GameService
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameRequest
import com.example.gametime_networklibrary.data.repositoryImpl.GameRepositoryImpl
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
    fun GetGamePositiveTest() = runTest {
        val jsonResponse = """"current_page": 1,
              "data": [
                {
                  "id": 1,
                  "type": "image_puzzle",
                  "status": "scheduled",
                  "creator_id": 5,
                  "max_players": 4,
                  "difficulty": 4,
                  "image_url": "string",
                  "target_avatar_url": "string",
                  "config": {
                    "pieces": 16,
                    "difficulty": 4,
                    "grid": {
                      "rows": 4,
                      "cols": 4
                    }
                  },
                  "winner_id": 0,
                  "winner_time": 0,
                  "scheduled_at": "2026-04-21T08:29:40.822Z",
                  "started_at": "2026-04-21T08:29:40.822Z",
                  "finished_at": "2026-04-21T08:29:40.822Z",
                  "created_at": "2026-04-21T08:29:40.822Z",
                  "updated_at": "2026-04-21T08:29:40.822Z",
                  "creator": {
                    "id": 0,
                    "name": "string",
                    "email": "string",
                    "avatar": "string",
                    "is_online": true
                  },
                  "players": [
                    {
                      "id": 0,
                      "name": "string",
                      "email": "string",
                      "avatar": "string",
                      "is_online": true
                    }
                  ]
                }
              ],
              "first_page_url": "string",
              "from": 1,
              "last_page": 1,
              "last_page_url": "string",
              "per_page": 20,
              "to": 1,
              "total": 1"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

    }

    @Test
    fun CreateGamePositiveTest() = runTest {
        val jsonResponse = """{
  "message": "Игра запланирована",
  "game": {
    "id": 1,
    "type": "image_puzzle",
    "title": "Загадка дня",
    "description": "Описание игры",
    "points": 100,
    "status": "scheduled",
    "creator_id": 0,
    "max_players": 0,
    "difficulty": 0,
    "image_url": "string",
    "target_avatar_url": "string",
    "config": {
      "pieces": 0,
      "difficulty": 0,
      "grid": {
        "rows": 0,
        "cols": 0
      }
    },
    "scheduled_at": "2026-04-21T08:31:19.138Z",
    "created_at": "2026-04-21T08:31:19.138Z",
    "updated_at": "2026-04-21T08:31:19.138Z",
    "creator": {
      "id": 0,
      "name": "string",
      "email": "string",
      "avatar": "string"
    },
    "players": [
      {
        "id": 0,
        "name": "string",
        "email": "string",
        "avatar": "string"
      }
    ]
  }
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val request = CreateGameRequest(
            type = "image_puzzle",
            scheduled_at = "2026-04-01T15:00:00Z",
            title = "Загадка дня",
            description = "Описание игры",
            points = 100,
            max_players = 4,
            difficulty = 4
        )
        val result = gameRepository.createGame("JWT_Token", request)
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data
        assertEquals("Игра запланирована", data.message)

        val res = mockWebServer.takeRequest()
        assertEquals("/games", res.path)
        assertEquals("Bearer JWT_Token", res.headers["Authorization"])

    }

    @Test
    fun GetIngormtionGameById() = runTest {
        val jsonResponse = """{
  "id": 1,
  "type": "image_puzzle",
  "status": "scheduled",
  "creator_id": 5,
  "max_players": 4,
  "difficulty": 4,
  "image_url": "string",
  "target_avatar_url": "string",
  "config": {
    "pieces": 16,
    "difficulty": 4,
    "grid": {
      "rows": 4,
      "cols": 4
    }
  },
  "winner_id": 0,
  "winner_time": 0,
  "scheduled_at": "2026-04-21T08:32:54.445Z",
  "started_at": "2026-04-21T08:32:54.445Z",
  "finished_at": "2026-04-21T08:32:54.445Z",
  "created_at": "2026-04-21T08:32:54.445Z",
  "updated_at": "2026-04-21T08:32:54.445Z",
  "creator": {
    "id": 0,
    "name": "string",
    "email": "string",
    "avatar": "string",
    "is_online": true
  },
  "players": [
    {
      "id": 0,
      "name": "string",
      "email": "string",
      "avatar": "string",
      "is_online": true
    }
  ]
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = gameRepository.getInformationGameByiId("JWT_Token", 1)
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals(1, data.id)
        assertEquals("image_puzzle", data.type)
        assertEquals("scheduled", data.status)

        val res = mockWebServer.takeRequest()
        assertEquals("/games/1", res.path)
        assertEquals("Bearer JWT_Token", res.headers["Authorization"])
    }

    @Test
    fun LoginGamePositiveTest() = runTest {
        val jsonResponse = """{
  "message": "Вы вошли в игру",
  "game": {
    "id": 1,
    "type": "string",
    "status": "string",
    "max_players": 0,
    "image_url": "string",
    "target_avatar_url": "string",
    "config": {},
    "scheduled_at": "2026-04-21T08:38:33.748Z",
    "created_at": "2026-04-21T08:38:33.748Z",
    "updated_at": "2026-04-21T08:38:33.748Z",
    "players": [
      {
        "id": 0,
        "name": "string",
        "email": "string",
        "avatar": "string",
        "is_online": true
      }
    ]
  }
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = gameRepository.loginGame("JWT_Token", 1)
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals("Вы вошли в игру", data.message)
        assertEquals(1, data.game.id)

        val res = mockWebServer.takeRequest()
        assertEquals("/games/1/join", res.path)
        assertEquals("Bearer JWT_Token", res.headers["Authorization"])
    }


    @Test
    fun SafeResultGamePositiveTest() = runTest {
        val jsonResponse = """{
  "message": "Результат сохранён",
  "game": {
    "id": 1,
    "type": "string",
    "status": "string",
    "image_url": "string",
    "target_avatar_url": "string",
    "config": {},
    "players": [
      {}
    ]
  },
  "your_time": 45,
  "waiting_for_players": true
}"""

        val mockResponse = MockResponse().setBody(jsonResponse).setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = gameRepository.safeResultGame("JWT_Token", 1,FinishGameRequest(time=45, moves = 12))
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals("Результат сохранён", data.message)
        assertEquals(1, data.game.id)

        val res = mockWebServer.takeRequest()
        assertEquals("/games/1/finish", res.path)
        assertEquals("Bearer JWT_Token", res.headers["Authorization"])
    }
}