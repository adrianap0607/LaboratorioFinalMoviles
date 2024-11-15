package com.example.labfinal.presentation.monsterProfile
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labfinal.domain.model.CryptoAsset
import com.example.labfinal.presentation.common.LoadingLayout

@Composable
fun CryptoProfileRoute(
    assetId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CryptoProfileViewModel = viewModel(factory = CryptoProfileViewModel.provideFactory(assetId))
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CryptoProfileScreen(
        state = state,
        onNavigateBack = onNavigateBack,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CryptoProfileScreen(
    state: CryptoProfileState,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Asset Profile", color = MaterialTheme.colorScheme.onPrimary) },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
        if (state.isLoading) {
            LoadingLayout(modifier = Modifier.fillMaxSize())
        } else {
            state.data?.let { asset ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = asset.name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Symbol: ${asset.symbol}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Price: ${asset.priceUsd} USD", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Market Cap: ${asset.marketCapUsd}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Supply: ${asset.supply}", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Max Supply: ${asset.maxSupply ?: "N/A"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCryptoProfileScreen() {
    CryptoProfileScreen(
        state = CryptoProfileState(
            isLoading = false,
            data = CryptoAsset(
                id = "1",
                name = "Bitcoin",
                symbol = "BTC",
                priceUsd = "45000",
                changePercent24Hr = "2.5",
                marketCapUsd = "850000000000",
                volumeUsd24Hr = "4500000000",
                supply = "19000000",
                maxSupply = "21000000",
                explorerUrl = "https://blockchain.info/"
            )
        ),
        onNavigateBack = {}
    )
}
