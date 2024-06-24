package pl.kormateusz.pokemon.domain.usecases

import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.domain.repositories.PokemonRepository

class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) : BaseNoParamUseCase<Result<List<Pokemon>>>() {

    override suspend fun buildUseCase(): Result<List<Pokemon>> =
        pokemonRepository.getPokemons()
}