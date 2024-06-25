package pl.kormateusz.pokemon.ui.common

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(
    private val context: Context,
) {
    fun getString(@StringRes resource: Int) = context.getString(resource)
}