package pl.kormateusz.pokemon.modules

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.kormateusz.pokemon.ui.common.ResourceProvider

val appModule = module {
    singleOf(::ResourceProvider)
}