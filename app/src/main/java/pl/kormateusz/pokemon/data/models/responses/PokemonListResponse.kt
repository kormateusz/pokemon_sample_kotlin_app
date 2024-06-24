package pl.kormateusz.pokemon.data.models.responses

import androidx.core.net.toUri
import com.google.gson.annotations.SerializedName
import pl.kormateusz.pokemon.domain.models.Pokemon

data class PokemonListResponse(
    @SerializedName("results") val results: List<PokemonResponse>
)

data class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val detailsUrl: String,
)

fun PokemonResponse.toDomain(): Pokemon {
    val id = detailsUrl.toUri().pathSegments[3]
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl,
    )
}
