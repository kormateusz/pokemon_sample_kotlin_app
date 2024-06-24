package pl.kormateusz.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.core.context.GlobalContext.startKoin
import pl.kormateusz.pokemon.modules.viewModelModule
import pl.kormateusz.pokemon.ui.details.DetailsScreen
import pl.kormateusz.pokemon.ui.pokedex.PokedexScreen
import pl.kormateusz.pokemon.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            modules(viewModelModule)
        }

        setContent {
            val navController = rememberNavController()
            PokemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navController)
                }
            }
        }
    }
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PokedexScreen,
    ) {
        composable<PokedexScreen> { PokedexScreen(navController) }
        composable<DetailsScreen> {
            val detailsScreen: DetailsScreen = it.toRoute()
            DetailsScreen(pokemonId = detailsScreen.id, navController)
        }
    }
}