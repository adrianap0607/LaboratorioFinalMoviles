package com.example.labfinal.presentation.monsterProfile

import com.example.labfinal.domain.model.CryptoAsset

data class CryptoProfileState(
    val isLoading: Boolean = true,
    val data: CryptoAsset? = null,
    val isError: Boolean = false
)