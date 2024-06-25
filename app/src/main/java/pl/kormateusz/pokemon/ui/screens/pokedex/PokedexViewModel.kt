package pl.kormateusz.pokemon.ui.screens.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.kormateusz.pokemon.R
import pl.kormateusz.pokemon.domain.usecases.GetPokemonsUseCase
import pl.kormateusz.pokemon.ui.common.ResourceProvider

class PokedexViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {
    private val _state = MutableStateFlow<PokedexState>(PokedexState.Loading)
    val state: StateFlow<PokedexState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonsUseCase.execute()
                .onSuccess { _state.emit(PokedexState.Loaded(it)) }
                .onFailure {
                    _state.emit(
                        PokedexState.Error(it.message ?: resourceProvider.getString(R.string.pokedex_screen_error))
                    )
                }
        }
    }
}