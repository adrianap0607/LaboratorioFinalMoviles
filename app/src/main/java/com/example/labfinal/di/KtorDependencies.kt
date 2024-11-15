package com.example.labfinal.di

import com.example.labfinal.data.HttpClientFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

object KtorDependencies {
    private var httpClient: HttpClient? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()
    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}