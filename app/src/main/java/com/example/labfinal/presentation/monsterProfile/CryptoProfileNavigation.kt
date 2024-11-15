package com.example.labfinal.presentation.monsterProfile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class CryptoProfileDestination(val id: String)

fun NavController.navigateToCryptoProfile(destination: CryptoProfileDestination, navOptions: NavOptions? = null) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.cryptoProfile(onNavigateBack: () -> Unit) {
    composable<CryptoProfileDestination> { backStackEntry ->
        val destination: CryptoProfileDestination = backStackEntry.toRoute()
        CryptoProfileRoute(assetId = destination.id, onNavigateBack = onNavigateBack)
    }
}
