package pl.kormateusz.pokemon.modules

import org.koin.dsl.module
import pl.kormateusz.pokemon.data.repositories.PokemonRepositoryImpl
import pl.kormateusz.pokemon.domain.repositories.PokemonRepository

val repositoriesModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}