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
import pl.kormateusz.pokemon.domain.models.PokemonDetails
import pl.kormateusz.pokemon.domain.usecases.GetPokemonDetailsUseCase
import pl.kormateusz.pokemon.ui.common.ResourceProvider
import pl.kormateusz.pokemon.ui.screens.details.DetailsState
import pl.kormateusz.pokemon.ui.screens.details.DetailsViewModel

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTests {

    @Mock
    private lateinit var getPokemonDetailsUseCase: GetPokemonDetailsUseCase

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private lateinit var viewModel: DetailsViewModel

    private fun setupViewModel() {
        viewModel = DetailsViewModel("", getPokemonDetailsUseCase, resourceProvider)
    }

    @Test
    fun `loads pokemon details on init`() = runTest {
        // given
        val result = PokemonDetails(
            id = "tristique",
            name = "Jimmy Chaney",
            imageUrl = "https://search.yahoo.com/search?p=senserit",
            height = 9769,
            weight = 9504,
            types = listOf()
        )
        whenever(getPokemonDetailsUseCase.execute(any())).thenAnswer { Result.success(result) }

        // when
        setupViewModel()

        // then
        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)
            assertEquals(DetailsState.Loaded(result), state.awaitItem())
        }
    }

    @Test
    fun `shows error message on fail`() = runTest {
        // given
        whenever(getPokemonDetailsUseCase.execute(any())).thenAnswer { Result.failure<PokemonDetails>(Exception()) }
        whenever(resourceProvider.getString(any())).thenReturn("error message")

        // when
        setupViewModel()

        // then
        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)
            assertEquals(DetailsState.Error("error message"), state.awaitItem())
        }
    }
}