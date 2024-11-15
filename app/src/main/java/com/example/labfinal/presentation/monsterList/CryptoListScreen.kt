package com.example.labfinal.presentation.monsterList
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labfinal.domain.model.CryptoAsset
import com.example.labfinal.presentation.common.ErrorLayout
import com.example.labfinal.presentation.common.LoadingLayout

@Composable
fun CryptoListRoute(
    onCryptoClick: (String) -> Unit,
    viewModel: CryptoListViewModel = viewModel(factory = CryptoListViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CryptoListScreen(
        state = state,
        onRetryClick = viewModel::getAssets,
        onCryptoClick = onCryptoClick,
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CryptoListScreen(
    state: CryptoListState,
    onRetryClick: () -> Unit,
    onCryptoClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crypto List", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            when {
                state.isLoading -> LoadingLayout(modifier = Modifier.align(Alignment.Center))
                state.isError -> ErrorLayout(onRetryClick = onRetryClick, modifier = Modifier.align(Alignment.Center))
                else -> {
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(state.data) { asset ->
                            CryptoItem(
                                asset = asset,
                                onClick = { onCryptoClick(asset.id) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CryptoItem(
    asset: CryptoAsset,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = asset.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = asset.symbol, style = MaterialTheme.typography.bodySmall)
                Text(text = "Price: ${asset.priceUsd} USD", style = MaterialTheme.typography.bodyMedium)
            }
            val changeColor = if (asset.changePercent24Hr.toFloat() > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            Text(
                text = "${asset.changePercent24Hr}%",
                color = changeColor,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCryptoListScreen() {
    CryptoListScreen(
        state = CryptoListState(
            data = listOf(
                CryptoAsset(
                    id = "1",
                    name = "Bitcoin",
                    symbol = "BTC",
                    priceUsd = "45000",
                    changePercent24Hr = "2.5",
                    marketCapUsd = "",
                    volumeUsd24Hr = "",
                    supply = "",
                    maxSupply = "",
                    explorerUrl = ""
                ),
                CryptoAsset(
                    id = "2",
                    name = "Ethereum",
                    symbol = "ETH",
                    priceUsd = "3000",
                    changePercent24Hr = "-1.2",
                    marketCapUsd = "",
                    volumeUsd24Hr = "",
                    supply = "",
                    maxSupply = "",
                    explorerUrl = ""
                )
            )
        ),
        onRetryClick = {},
        onCryptoClick = {}
    )
}
