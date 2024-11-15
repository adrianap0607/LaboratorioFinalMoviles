package com.example.labfinal.presentation.monsterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.labfinal.data.KtorCryptoApi
import com.example.labfinal.data.dto.mapToCryptoAsset

import com.example.labfinal.di.KtorDependencies
import com.example.labfinal.domain.network.CryptoApi

import com.example.labfinal.domain.network.util.map
import com.example.labfinal.domain.network.util.onError
import com.example.labfinal.domain.network.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoListViewModel(
    private val cryptoApi: CryptoApi
): ViewModel() {

    private val _state = MutableStateFlow(CryptoListState())
    val state = _state.asStateFlow()

    init {
        getAssets()
    }

    fun getAssets() {
        viewModelScope.launch {
            cryptoApi
                .getAllAssets()
                .map { response -> response.data.map { it.mapToCryptoAsset() } }
                .onSuccess { assets ->
                    _state.update { it.copy(
                        isLoading = false,
                        data = assets
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        isError = true,
                    ) }

                    println(error)
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val api = KtorCryptoApi(KtorDependencies.provideHttpClient())
                CryptoListViewModel(api)
            }
        }
    }

}