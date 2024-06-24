package pl.kormateusz.pokemon.domain.usecases

import pl.kormateusz.pokemon.domain.models.PokemonDetails
import pl.kormateusz.pokemon.domain.repositories.PokemonRepository

class GetPokemonDetailsUseCase(
    private val pokemonRepository: PokemonRepository
) : BaseParamUseCase<String, Result<PokemonDetails>>() {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun buildUseCase(id: String): Result<PokemonDetails> =
        pokemonRepository.getPokemonDetails(id)
}