package pl.kormateusz.pokemon.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.ui.screens.details.DetailsViewModel
import pl.kormateusz.pokemon.ui.screens.pokedex.PokedexViewModel

val viewModelModule = module {
    viewModel { PokedexViewModel(get(), get()) }
    viewModel { (pokemonId: String) -> DetailsViewModel(pokemonId, get(), get()) }
}