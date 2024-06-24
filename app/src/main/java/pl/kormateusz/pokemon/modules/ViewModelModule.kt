package pl.kormateusz.pokemon.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.kormateusz.pokemon.ui.details.DetailsViewModel
import pl.kormateusz.pokemon.ui.pokedex.PokedexViewModel

val viewModelModule = module {
    viewModel { PokedexViewModel() }
    viewModel { DetailsViewModel() }
}