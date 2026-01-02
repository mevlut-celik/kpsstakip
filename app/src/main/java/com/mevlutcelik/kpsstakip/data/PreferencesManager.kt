package com.mevlutcelik.kpsstakip.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManager(private val context: Context) {
    
    companion object {
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        private val KPSS_EXAM_DATE_KEY = longPreferencesKey("kpss_exam_date")
        
        // Default KPSS exam date: July 25, 2026 at 10:00 AM
        private const val DEFAULT_KPSS_DATE = 1753344000000L // 2026-07-25 10:00:00
        // private const val DEFAULT_KPSS_DATE = 1753344000000L // 2026-07-25 10:00:00
    }
    
    val isDarkMode: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[DARK_MODE_KEY] ?: false
    }
    
    val kpssExamDate: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[KPSS_EXAM_DATE_KEY] ?: run {
                // Default: July 25, 2026 at 10:00 AM
                val calendar = java.util.Calendar.getInstance()
                calendar.set(2026, 6, 25, 10, 0, 0) // Month is 0-indexed (6 = July)
                calendar.timeInMillis
            }
        }
    
    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }
    
    suspend fun setKpssExamDate(timestamp: Long) {
        context.dataStore.edit { preferences ->
            preferences[KPSS_EXAM_DATE_KEY] = timestamp
        }
    }
}
