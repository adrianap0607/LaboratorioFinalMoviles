package com.example.labfinal.domain.network



import com.example.labfinal.data.dto.EntryListDto
import com.example.labfinal.data.dto.EntryProfileDto
import com.example.labfinal.domain.network.util.NetworkError
import com.example.labfinal.domain.network.util.Result

interface CryptoApi {
    suspend fun getAllAssets(): Result<EntryListDto, NetworkError>
    suspend fun getAssetProfile(id: String): Result<EntryProfileDto, NetworkError>
}
