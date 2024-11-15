package com.example.labfinal.data.dto

import com.example.labfinal.domain.model.CryptoAsset
import kotlinx.serialization.Serializable

@Serializable
data class EntryDto(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val explorerUrl: String?= null,
    val marketCapUsd: String?,
    val maxSupply: String?= null,
    val supply: String?,
    val volumeUsd24Hr: String?
)

fun EntryDto.mapToCryptoAsset(): CryptoAsset {
    return CryptoAsset(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        explorerUrl = explorerUrl,
        marketCapUsd = marketCapUsd,
        maxSupply = maxSupply,
        supply = supply,
        volumeUsd24Hr = volumeUsd24Hr
    )
}