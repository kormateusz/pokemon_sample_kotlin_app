package pl.kormateusz.pokemon.domain.usecases

import pl.kormateusz.pokemon.domain.models.PokemonDetails
import pl.kormateusz.pokemon.domain.repositories.PokemonRepository

class GetPokemonDetailsUseCase(
    private val pokemonRepository: PokemonRepository
) : BaseParamUseCase<String, PokemonDetails>() {

    override suspend fun buildUseCase(param: String): PokemonDetails =
        pokemonRepository.getPokemonDetails()
}