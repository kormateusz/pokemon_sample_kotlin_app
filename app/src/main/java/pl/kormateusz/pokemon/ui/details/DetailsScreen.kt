package pl.kormateusz.pokemon.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable
import pl.kormateusz.pokemon.ui.pokedex.PokedexScreen

@Serializable
data class DetailsScreen(val id: String)

@Composable
fun DetailsScreen(pokemonId: String, navController: NavHostController) {
    Column {
        Text("Details screen: $pokemonId")
        Button(onClick = { navController.popBackStack<PokedexScreen>(inclusive = false) }) {
            Text("Back")
        }
    }
}