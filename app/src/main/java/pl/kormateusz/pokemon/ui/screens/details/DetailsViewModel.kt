package pl.kormateusz.pokemon.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.kormateusz.pokemon.R
import pl.kormateusz.pokemon.domain.usecases.GetPokemonDetailsUseCase
import pl.kormateusz.pokemon.ui.common.ResourceProvider

class DetailsViewModel(
    private val pokemonId: String,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {
    private val _state = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailsUseCase.execute(pokemonId)
                .onSuccess { _state.emit(DetailsState.Loaded(it)) }
                .onFailure {
                    _state.emit(
                        DetailsState.Error(it.message ?: resourceProvider.getString(R.string.details_screen_error))
                    )
                }
        }
    }
}