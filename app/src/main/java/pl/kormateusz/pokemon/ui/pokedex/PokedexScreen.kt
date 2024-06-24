package pl.kormateusz.pokemon.ui.pokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable
import pl.kormateusz.pokemon.ui.details.DetailsScreen

@Serializable
object PokedexScreen

@Composable
fun PokedexScreen(navController: NavHostController) {
    Column {
        Text("Pokedex screen")
        Button(onClick = { navController.navigate(DetailsScreen("123")) }) {
            Text("Go to details")
        }
    }
}