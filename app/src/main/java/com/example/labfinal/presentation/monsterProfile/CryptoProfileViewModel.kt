package com.example.labfinal.presentation.monsterProfile

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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoProfileViewModel(
    private val cryptoApi: CryptoApi,
    private val assetId: String
) : ViewModel() {

    private val _state = MutableStateFlow(CryptoProfileState())
    val state: StateFlow<CryptoProfileState> = _state

    init {
        getAssetProfile()
    }

    private fun getAssetProfile() {
        viewModelScope.launch {
            cryptoApi.getAssetProfile(assetId)
                .map { it.data.mapToCryptoAsset() }
                .onSuccess { asset ->
                    _state.update { it.copy(isLoading = false, data = asset) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false, isError = true) }
                }
        }
    }

    companion object {
        fun provideFactory(assetId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val api = KtorCryptoApi(KtorDependencies.provideHttpClient())
                CryptoProfileViewModel(api, assetId)
            }
        }
    }
}
