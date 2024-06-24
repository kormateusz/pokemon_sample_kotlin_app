package pl.kormateusz.pokemon.data.models.responses

import com.google.gson.annotations.SerializedName
import pl.kormateusz.pokemon.domain.models.PokemonDetails
import pl.kormateusz.pokemon.domain.models.PokemonTypes

data class PokemonDetailsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("types") val types: List<PokemonTypeResponse>,
)

data class PokemonTypeResponse(
    @SerializedName("type") val type: PokemonTypeDetailsResponse,
)

data class PokemonTypeDetailsResponse(
    @SerializedName("name") val name: String,
)

fun PokemonDetailsResponse.toDomain() = PokemonDetails(
    id = id,
    name = name,
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
    height = height,
    weight = weight,
    types = types.map { PokemonTypes.find(it.type.name) }
)