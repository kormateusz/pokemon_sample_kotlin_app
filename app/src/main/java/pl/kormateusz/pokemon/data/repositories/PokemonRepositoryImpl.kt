package pl.kormateusz.pokemon.data.repositories

import kotlinx.coroutines.delay
import pl.kormateusz.pokemon.domain.models.Pokemon
import pl.kormateusz.pokemon.domain.models.PokemonDetails
import pl.kormateusz.pokemon.domain.models.PokemonTypes
import pl.kormateusz.pokemon.domain.repositories.PokemonRepository

class PokemonRepositoryImpl : PokemonRepository {
    override suspend fun getPokemons(): List<Pokemon> {
        delay(1000)
        return listOf(
            Pokemon(
                id = "1",
                name = "One",
                imageUrl = "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/133.png"
            ),
            Pokemon(
                id = "2",
                name = "Two",
                imageUrl = "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/131.png"
            ),
            Pokemon(
                id = "3",
                name = "Three",
                imageUrl = "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/129.png"
            )
        )
    }

    override suspend fun getPokemonDetails(): PokemonDetails {
        delay(1000)
        return PokemonDetails(
            id = "2",
            name = "Two",
            imageUrl = "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/131.png",
            height = 50,
            weight = 60,
            types = listOf(PokemonTypes.WATER)
        )
    }
}