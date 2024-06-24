package pl.kormateusz.pokemon.data.services

import pl.kormateusz.pokemon.data.models.responses.PokemonDetailsResponse
import pl.kormateusz.pokemon.data.models.responses.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getPage(@Query("limit") pageSize: Int): Response<PokemonListResponse>

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetails(@Path("id") id: String): Response<PokemonDetailsResponse>
}