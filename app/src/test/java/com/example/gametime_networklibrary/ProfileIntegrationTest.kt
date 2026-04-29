package com.example.gametime_networklibrary

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.repositoryImpl.ProfileRepositoryImpl
import com.example.gametime_networklibrary.data.repositoryImpl.UserRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import jakarta.inject.Inject
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class ProfileIntegrationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userRepository: UserRepositoryImpl

    lateinit var testEmail: String

    @Inject
    lateinit var profileRepository: ProfileRepositoryImpl

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testAuthorizationAndProfile() = runTest {
        testEmail = "test_${System.currentTimeMillis()}@email.ru"
        val authRequest = RegisterRequest(
            name = "name",
            email = testEmail,
            password = "password",
            password_confirmation = "password",
            firstname = "firstname",
            lastname = "lastname",
            phone = "phone"
        )
        val authResponse = userRepository.registration(authRequest)

        assertTrue(authResponse is NetworkResult.Success)

        val authData = (authResponse as NetworkResult.Success).data
        val authToken = authData.token

        val profileResponse = profileRepository.getProfile(authToken)

        assertTrue(profileResponse is NetworkResult.Success)

        val profile = (profileResponse as NetworkResult.Success).data

        assertEquals(testEmail, profile.email)
        assertEquals("name", profile.name)
        assertEquals(null, profile.avatarUrl)
    }
}