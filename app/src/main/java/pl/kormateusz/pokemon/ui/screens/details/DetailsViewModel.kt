package pl.kormateusz.pokemon.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.kormateusz.pokemon.domain.usecases.GetPokemonDetailsUseCase

class DetailsViewModel(
    private val pokemonId: String,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _state.emit(DetailsState.Loaded(getPokemonDetailsUseCase.execute(pokemonId)))
            } catch (exception: Exception) {
                _state.emit(DetailsState.Error("Cannot load Pokemon details"))
            }
        }
    }
}