package com.example.labfinal.data

import com.example.labfinal.data.dto.EntryListDto
import com.example.labfinal.data.dto.EntryProfileDto
import com.example.labfinal.data.util.safeCall
import com.example.labfinal.domain.network.CryptoApi
import com.example.labfinal.domain.network.util.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import com.example.labfinal.domain.network.util.Result

class KtorCryptoApi(
    private val httpClient: HttpClient
) : CryptoApi {
    override suspend fun getAllAssets(): Result<EntryListDto, NetworkError> {
        return safeCall<EntryListDto> {
            httpClient.get("https://api.coincap.io/v2/assets")
        }
    }

    override suspend fun getAssetProfile(id: String): Result<EntryProfileDto, NetworkError> {
        return safeCall<EntryProfileDto> {
            httpClient.get("https://api.coincap.io/v2/assets/$id")
        }
    }
}
