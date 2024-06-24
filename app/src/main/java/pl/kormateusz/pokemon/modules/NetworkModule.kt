package pl.kormateusz.pokemon.modules

import org.koin.dsl.module
import pl.kormateusz.pokemon.BuildConfig
import pl.kormateusz.pokemon.data.services.PokemonApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { createApiService<PokemonApiService>(BuildConfig.API_URL) }
}

private inline fun <reified T> createApiService(url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(T::class.java)