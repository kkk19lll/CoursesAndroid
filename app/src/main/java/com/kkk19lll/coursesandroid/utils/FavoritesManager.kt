package com.kkk19lll.coursesandroid.utils

import android.content.Context
import android.content.SharedPreferences

class FavoritesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)

    private val FAVORITES_KEY = "favorites_ids"

    fun saveFavorite(id: Int) {
        val favorites = getFavorites().toMutableSet()
        favorites.add(id.toString())
        prefs.edit().putStringSet(FAVORITES_KEY, favorites).apply()
    }

    fun removeFavorite(id: Int) {
        val favorites = getFavorites().toMutableSet()
        favorites.remove(id.toString())
        prefs.edit().putStringSet(FAVORITES_KEY, favorites).apply()
    }

    fun isFavorite(id: Int): Boolean {
        return getFavorites().contains(id.toString())
    }

    fun getFavorites(): Set<String> {
        return prefs.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()
    }
}
