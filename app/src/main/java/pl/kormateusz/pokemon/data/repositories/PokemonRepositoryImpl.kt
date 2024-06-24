package pl.kormateusz.pokemon.data.repositories

import pl.kormateusz.pokemon.data.models.responses.toDomain
import pl.kormateusz.pokemon.data.services.PokemonApiService
import pl.kormateusz.pokemon.data.utils.makeRequest
import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.domain.models.PokemonDetails
import pl.kormateusz.pokemon.domain.repositories.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonApiService: PokemonApiService,
) : PokemonRepository {

    override suspend fun getPokemons(): Result<List<Pokemon>> =
        pokemonApiService.getPage(151)
            .makeRequest { it.results.map { it.toDomain() } }

    override suspend fun getPokemonDetails(id: String): Result<PokemonDetails> =
        pokemonApiService.getPokemonDetails(id)
            .makeRequest { it.toDomain() }
}

