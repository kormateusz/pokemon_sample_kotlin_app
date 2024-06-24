package pl.kormateusz.pokemon.modules

import org.koin.dsl.module
import pl.kormateusz.pokemon.domain.usecases.GetPokemonDetailsUseCase
import pl.kormateusz.pokemon.domain.usecases.GetPokemonsUseCase

val useCaseModule = module {
    factory { GetPokemonsUseCase(get()) }
    factory { GetPokemonDetailsUseCase(get()) }
}