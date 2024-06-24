package pl.kormateusz.pokemon.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlinx.serialization.Serializable
import pl.kormateusz.pokemon.R
import pl.kormateusz.pokemon.domain.models.PokemonDetails
import pl.kormateusz.pokemon.ui.widgets.FullScreenError
import pl.kormateusz.pokemon.ui.widgets.FullScreenLoader

@Serializable
data class DetailsScreen(val pokemonId: String)

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, navController: NavHostController) {
    when (val state = viewModel.state.collectAsState().value) {
        DetailsState.Loading -> FullScreenLoader()
        is DetailsState.Error -> FullScreenError(errorMessage = state.error)
        is DetailsState.Loaded -> Body(
            details = state.details,
            onBackClick = navController::navigateUp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Body(details: PokemonDetails, onBackClick: () -> Unit) {
    val typeColor = colorResource(id = details.types.firstOrNull()?.color ?: R.color.normalType)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "#${details.id}") },
                colors = TopAppBarColors(
                    containerColor = typeColor,
                    scrolledContainerColor = typeColor,
                    navigationIconContentColor = colorResource(id = R.color.white),
                    titleContentColor = colorResource(id = R.color.white),
                    actionIconContentColor = colorResource(id = R.color.white),
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = colorResource(id = R.color.white),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .background(typeColor)
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp),
                    model = details.imageUrl,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = details.name,
                    color = colorResource(id = R.color.white),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    DetailsCard(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(weight = 1f),
                        title = stringResource(id = R.string.details_screen_height_header),
                        subtitle = "${details.height}"
                    )
                    DetailsCard(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(weight = 1f),
                        title = stringResource(id = R.string.details_screen_weight_header),
                        subtitle = "${details.weight}"
                    )
                }
            }
        }
    )
}

@Composable
private fun DetailsCard(modifier: Modifier = Modifier, title: String, subtitle: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}