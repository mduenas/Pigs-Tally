package com.markduenas.pigstally2.storage

import com.markduenas.pigstally2.model.GamePreferences

expect class PreferencesStorage {
    suspend fun savePreferences(preferences: GamePreferences)
    suspend fun loadPreferences(): GamePreferences
}