package com.example.labfinal.presentation.monsterList

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CryptoListDestination

fun NavGraphBuilder.cryptoList(
    navigateToCryptoProfile: (String) -> Unit
) {
    composable<CryptoListDestination> {
        CryptoListRoute(
            onCryptoClick = navigateToCryptoProfile
        )
    }
}
