package pl.kormateusz.pokemon.ui.screens

import app.cash.turbine.turbineScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.domain.usecases.GetPokemonsUseCase
import pl.kormateusz.pokemon.ui.common.ResourceProvider
import pl.kormateusz.pokemon.ui.screens.pokedex.PokedexState
import pl.kormateusz.pokemon.ui.screens.pokedex.PokedexViewModel

@RunWith(MockitoJUnitRunner::class)
class PokedexViewModelTests {

    @Mock
    private lateinit var getPokemonsUseCase: GetPokemonsUseCase

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private lateinit var viewModel: PokedexViewModel

    private fun setupViewModel() {
        viewModel = PokedexViewModel(getPokemonsUseCase, resourceProvider)
    }

    @Test
    fun `loads pokemons list on init`() = runTest {
        // given
        val result = listOf(
            Pokemon(id = "eloquentiam", name = "Vicente Green", imageUrl = "http://www.bing.com/search?q=ut"),
            Pokemon(id = "agam", name = "Ramiro Weiss", imageUrl = "http://www.bing.com/search?q=vehicula")
        )
        whenever(getPokemonsUseCase.execute()).thenAnswer { Result.success(result) }

        // when
        setupViewModel()

        // then
        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)
            assertEquals(PokedexState.Loaded(result), state.awaitItem())
        }
    }

    @Test
    fun `shows error message on fail`() = runTest {
        // given
        whenever(getPokemonsUseCase.execute()).thenAnswer { Result.failure<List<Pokemon>>(Exception()) }
        whenever(resourceProvider.getString(any())).thenReturn("error message")

        // when
        setupViewModel()

        // then
        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)
            assertEquals(PokedexState.Error("error message"), state.awaitItem())
        }
    }
}