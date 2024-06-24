package pl.kormateusz.pokemon.domain.repositories

import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.domain.models.PokemonDetails

interface PokemonRepository {
    suspend fun getPokemons(): List<Pokemon>
    suspend fun getPokemonDetails(): PokemonDetails
}