package com.example.gametime_networklibrary.domain.repository

interface AuthRepository {
    fun getToken(): String?
    fun saveToken(token: String)
    fun deleteToken()

    fun getPhone(): String?
    fun savePhone(phone: String)
    fun deleteData()
}