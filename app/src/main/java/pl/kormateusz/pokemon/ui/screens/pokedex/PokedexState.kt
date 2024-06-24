package pl.kormateusz.pokemon.ui.screens.pokedex

import androidx.compose.runtime.Immutable
import pl.kormateusz.pokemon.domain.models.Pokemon

@Immutable
sealed class PokedexState {
    @Immutable
    data object Loading : PokedexState()

    @Immutable
    data class Loaded(val value: List<Pokemon>) : PokedexState()

    @Immutable
    data class Error(val error: String) : PokedexState()
}