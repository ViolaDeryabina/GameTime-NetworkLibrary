package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.GameService
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameRequest
import com.example.gametime_networklibrary.data.repositoryImpl.GameRepositoryImpl
import com.example.gametime_networklibrary.data.repositoryImpl.UserRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
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
import javax.inject.Inject


@HiltAndroidTest()
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class GameUnitTests {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gameRepository: GameRepositoryImpl

    @Inject
    lateinit var userRepository: UserRepositoryImpl

    @Before
    fun start() {
        hiltRule.inject()
    }




    @Test
    fun GetGamePositiveTest() = runTest {
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

        val result = userRepository.registration(request)

        assertTrue(result is NetworkResult.Success)

        val data = (result as NetworkResult.Success).data
        assertEquals("Виолетта", data.user.name)
        assertEquals(email, data.user.email)
        assertEquals(false, data.user.isOnline)

        val token = data.token
        val resultGetGame = gameRepository.getListGame(token, status = "scheduled", type = "image_puzzle")

        assertTrue(resultGetGame is NetworkResult.Success)

        val dataGame = (resultGetGame as NetworkResult.Success).data
        assertEquals("scheduled", dataGame.data[0].status)
        assertEquals("image_puzzle", dataGame.data[0].type)

    }

    @Test
    fun CreateGamePositiveTest() = runTest {

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
        assertEquals(email, dataRegister.user.email)
        assertEquals(false, dataRegister.user.isOnline)

        val token = dataRegister.token
        val requestCreateGame = CreateGameRequest(
            type = "image_puzzle",
            scheduled_at = "30.04.2026",
            title = "title",
            description = "description",
            points = 150,
            max_players = 4,
            difficulty = 4
        )
        val resultGetGame = gameRepository.createGame(token, requestCreateGame)

        assertTrue(resultGetGame is NetworkResult.Success)
        val data = (resultGetGame as NetworkResult.Success).data
        assertEquals("Игра запланирована", data.message)
        assertEquals("image_puzzle", data.game.type)
        assertEquals(4, data.game.maxPlayers)
        assertEquals(4, data.game.difficulty)
        assertEquals("2026-04-30T00:00:00.000000Z", data.game.scheduledAt)
    }

    @Test
    fun GetIngormtionGameById() = runTest {
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
        assertEquals(email, dataRegister.user.email)
        assertEquals(false, dataRegister.user.isOnline)

        val token = dataRegister.token

        val result = gameRepository.getInformationGameByiId(token, 104)
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals(104, data.id)
        assertEquals("image_puzzle", data.type)
        assertEquals("finished", data.status)
    }

    @Test
    fun LoginGamePositiveTest() = runTest {
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
        assertEquals(email, dataRegister.user.email)
        assertEquals(false, dataRegister.user.isOnline)

        val token = dataRegister.token

        val result = gameRepository.loginGame(token, 102)
        assertTrue(result is NetworkResult.Success)
        val data = (result as NetworkResult.Success).data

        assertEquals("Вы вошли в игру", data.message)
        assertEquals(102, data.game.id)
    }


//    @Test
//    fun SafeResultGamePositiveTest() = runTest {
//        val email = "test_${System.currentTimeMillis()}@gmail.com"
//        val request = RegisterRequest(
//            "Виолетта",
//            email,
//            "password",
//            "password",
//            "Violetta",
//            "Deryabina",
//            phone = "+79201234556"
//        )
//
//        val resultRegister = userRepository.registration(request)
//
//        assertTrue(resultRegister is NetworkResult.Success)
//
//        val dataRegister = (resultRegister as NetworkResult.Success).data
//        assertEquals("Виолетта", dataRegister.user.name)
//        assertEquals(email, dataRegister.user.email)
//        assertEquals(false, dataRegister.user.isOnline)
//
//        val token = dataRegister.token
//
//        val result = gameRepository.safeResultGame(token, 102,FinishGameRequest(time=45, moves = 12))
//        assertTrue(result is NetworkResult.Success)
//        val data = (result as NetworkResult.Success).data
//
//        assertEquals("Результат сохранён", data.message)
//        assertEquals(1, data.game.id)
//    }
}