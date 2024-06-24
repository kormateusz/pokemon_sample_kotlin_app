package pl.kormateusz.pokemon.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinApplication
import pl.kormateusz.pokemon.modules.repositoriesModule
import pl.kormateusz.pokemon.modules.useCaseModule
import pl.kormateusz.pokemon.modules.viewModelModule
import pl.kormateusz.pokemon.ui.navigation.AppNavHost
import pl.kormateusz.pokemon.ui.theme.PokemonTheme

@Composable
fun MainApp() {
    val navController = rememberNavController()
    PokemonTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            KoinApplication(application = {
                modules(
                    viewModelModule,
                    useCaseModule,
                    repositoriesModule,
                )
            }) {
                AppNavHost(navController)
            }
        }
    }
}