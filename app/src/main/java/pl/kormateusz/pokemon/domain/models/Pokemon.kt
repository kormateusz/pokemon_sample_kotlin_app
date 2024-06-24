package pl.kormateusz.pokemon.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: String,
    val name: String,
    val imageUrl: String,
)