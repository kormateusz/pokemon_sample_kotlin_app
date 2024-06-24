package pl.kormateusz.pokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.kormateusz.pokemon.ui.screens.details.DetailsScreen
import pl.kormateusz.pokemon.ui.screens.details.DetailsViewModel
import pl.kormateusz.pokemon.ui.screens.pokedex.PokedexScreen
import pl.kormateusz.pokemon.ui.screens.pokedex.PokedexViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PokedexScreen,
    ) {
        composable<PokedexScreen> {
            val viewModel = koinViewModel<PokedexViewModel>()
            PokedexScreen(viewModel, navController)
        }
        composable<DetailsScreen> {
            val detailsScreen: DetailsScreen = it.toRoute()
            val viewModel = koinViewModel<DetailsViewModel> { parametersOf(detailsScreen.pokemonId) }
            DetailsScreen(viewModel, navController)
        }
    }
}