package pl.kormateusz.pokemon.ui.screens.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.kormateusz.pokemon.domain.usecases.GetPokemonsUseCase

class PokedexViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<PokedexState>(PokedexState.Loading)
    val state: StateFlow<PokedexState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _state.emit(PokedexState.Loaded(getPokemonsUseCase.execute()))
            } catch (exception: Exception) {
                _state.emit(PokedexState.Error("Cannot load Pokemons list"))
            }
        }
    }
}