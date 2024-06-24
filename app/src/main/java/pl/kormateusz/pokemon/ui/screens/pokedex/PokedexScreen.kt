package pl.kormateusz.pokemon.ui.screens.pokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlinx.serialization.Serializable
import pl.kormateusz.pokemon.R
import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.ui.screens.details.DetailsScreen
import pl.kormateusz.pokemon.ui.widgets.FullScreenError
import pl.kormateusz.pokemon.ui.widgets.FullScreenLoader

@Serializable
object PokedexScreen

@Composable
fun PokedexScreen(viewModel: PokedexViewModel, navController: NavHostController) {
    when (val state = viewModel.state.collectAsState().value) {
        PokedexState.Loading -> FullScreenLoader()
        is PokedexState.Error -> FullScreenError(errorMessage = state.error)
        is PokedexState.Loaded -> Body(
            pokemons = state.value,
            onItemClick = {
                navController.navigate(DetailsScreen(it.id))
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Body(pokemons: List<Pokemon>, onItemClick: (Pokemon) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.pokedex_screen_title)) })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
            ) {
                LazyColumn {
                    items(pokemons) { pokemon ->
                        ListItem(pokemon, onItemClick)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    )
}

@Composable
private fun ListItem(
    pokemon: Pokemon,
    onItemClick: (Pokemon) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = { onItemClick(pokemon) }
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp),
                model = pokemon.imageUrl,
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = pokemon.name
            )
        }
    }
}