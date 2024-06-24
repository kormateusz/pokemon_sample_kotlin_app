package pl.kormateusz.pokemon.domain.models

data class PokemonDetails(
    val id: String,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypes>,
)
