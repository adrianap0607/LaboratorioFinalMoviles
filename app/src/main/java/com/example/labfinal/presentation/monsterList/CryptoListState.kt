package com.example.labfinal.presentation.monsterList

import com.example.labfinal.domain.model.CryptoAsset

data class CryptoListState(
    val isLoading: Boolean = true,
    val data: List<CryptoAsset> = emptyList(),
    val isError: Boolean = false,
)