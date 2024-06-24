package pl.kormateusz.pokemon.domain.models

import androidx.annotation.ColorRes
import pl.kormateusz.pokemon.R

enum class PokemonTypes(@ColorRes val color: Int) {
    GRASS(R.color.grassType),
    FIRE(R.color.fireType),
    FLYING(R.color.flyingType),
    WATER(R.color.waterType),
    BUG(R.color.bugType),
    NORMAL(R.color.normalType),
    POISON(R.color.poisonType),
    ELECTRIC(R.color.electricType),
    GROUND(R.color.groundType),
    FAIRY(R.color.fairyType),
    FIGHTING(R.color.fightingType),
    PSYCHIC(R.color.psychicType),
    ROCK(R.color.rockType),
    STEEL(R.color.steelType),
    ICE(R.color.iceType),
    GHOST(R.color.ghostType),
    DRAGON(R.color.dragonType),
    UNKNOWN(R.color.normalType);

    companion object {
        fun find(name: String?) = entries.firstOrNull { it.name.lowercase() == name?.lowercase() } ?: UNKNOWN
    }
}