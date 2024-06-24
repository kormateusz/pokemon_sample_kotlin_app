package pl.kormateusz.pokemon.domain.usecases

import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.domain.repositories.PokemonRepository

class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) : BaseNoParamUseCase<List<Pokemon>>() {

    override suspend fun buildUseCase(): List<Pokemon> =
        pokemonRepository.getPokemons()
}