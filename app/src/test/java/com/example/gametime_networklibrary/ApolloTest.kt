package com.example.gametime_networklibrary

import com.apollographql.apollo.ApolloClient
import com.apollographql.mockserver.MockServer
import com.apollographql.mockserver.enqueueString
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.repositoryImpl.ApolloRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ApolloTest {
    private lateinit var mockServer: MockServer
    private lateinit var repository: ApolloRepositoryImpl

    @Before
    fun setup() = runTest { // Используйте runTest для корутин в тестах
        mockServer = MockServer()
        val url =
            mockServer.url() // Теперь это suspend функция или свойство в зависимости от версии

        val apolloClient = ApolloClient.Builder()
            .serverUrl(url)
            .build()
        repository = ApolloRepositoryImpl(apolloClient)
    }

    @After
    fun tearDown() = runTest {
        mockServer.close() // Используйте close() вместо stop()
    }

    @Test
    fun `getStatistic test`() = runTest {
        val jsonResponse = """{
              "data": {
                "statisticCollection": {
                  "edges": [
                    {
                      "node": {
                        "nodeId": "101",
                        "winimage": 5,
                        "wincircle": 3,
                        "total": 8
                      }
                    }
                  ]
                }
              }
            }"""

        mockServer.enqueueString(jsonResponse)

        val result = repository.getStatistic("token123")

        assertTrue(result is NetworkResult.Success)
        val firstItem = (result as NetworkResult.Success).data.firstOrNull()

        assertEquals("101", firstItem?.id)
        assertEquals(8, firstItem?.total)
        assertEquals(5, firstItem?.winImage)
        assertEquals(3, firstItem?.winCircle)
    }
}