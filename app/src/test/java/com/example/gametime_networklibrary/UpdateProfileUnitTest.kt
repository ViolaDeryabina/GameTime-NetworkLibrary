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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class UpdateProfileUnitTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userRepository: UserRepositoryImpl

    @Inject
    lateinit var profileRepository: ProfileRepositoryImpl

    @Before
    fun start() {
        hiltRule.inject()
    }

    @Test
    fun updateProfile() = runTest {

        val email = "test_${System.currentTimeMillis()}@gmail.com"

        val registration = RegisterRequest(
            name = "TEST Test",
            email = email,
            password = "123456789",
            password_confirmation = "123456789",
            firstname = "TEST",
            lastname = "Test",
            phone = "123456789"
        )

        val resultRegister = userRepository.registration(registration)

        assertTrue(resultRegister is NetworkResult.Success)
        val dataRegister = (resultRegister as NetworkResult.Success).data

        assertEquals(email, dataRegister.user.email)
        assertEquals("TEST Test", dataRegister.user.name)
        assertEquals(null, dataRegister.user.avatar)

        val token = dataRegister.token
        val nameString = "Иван Иванов"
        val nameRequestBody = nameString.toRequestBody("text/plain".toMediaTypeOrNull())
        val resultUpdate = profileRepository.updateProfile(token, name=nameRequestBody, null)
        assertTrue(resultUpdate is NetworkResult.Success)
        val dataUpdate = (resultUpdate as NetworkResult.Success).data

        assertEquals(email, dataUpdate.user.email)
        assertEquals("Иван Иванов", dataUpdate.user.name)
        assertEquals(null, dataUpdate.user.avatar)

    }


}