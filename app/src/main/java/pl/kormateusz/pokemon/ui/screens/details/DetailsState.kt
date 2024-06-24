package pl.kormateusz.pokemon.ui.screens.details

import androidx.compose.runtime.Immutable
import pl.kormateusz.pokemon.domain.models.PokemonDetails

@Immutable
sealed class DetailsState {
    @Immutable
    data object Loading : DetailsState()

    @Immutable
    data class Loaded(val details: PokemonDetails) : DetailsState()

    @Immutable
    data class Error(val error: String) : DetailsState()
}