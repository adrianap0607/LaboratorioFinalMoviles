package com.example.labfinal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


import com.example.labfinal.presentation.monsterList.CryptoListDestination
import com.example.labfinal.presentation.monsterList.cryptoList
import com.example.labfinal.presentation.monsterProfile.CryptoProfileDestination
import com.example.labfinal.presentation.monsterProfile.cryptoProfile
import com.example.labfinal.presentation.monsterProfile.navigateToCryptoProfile

@Composable
fun CryptoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CryptoListDestination,
        modifier = modifier
    ) {
        cryptoList(
            navigateToCryptoProfile = { cryptoId ->
                navController.navigateToCryptoProfile(
                    destination = CryptoProfileDestination(id = cryptoId)
                )
            }
        )
        cryptoProfile(onNavigateBack = navController::navigateUp)
    }
}
