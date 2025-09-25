package com.tursko.blend85

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SettingsDataStore(private val context: Context) {

    val DEFAULT_TARGET_ETHANOL = stringPreferencesKey("default_target_ethanol")
    val default_target_ethanol_flow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DEFAULT_TARGET_ETHANOL] ?: "30.0"
        }
    suspend fun updateDefaultTargetEthanol(targetEthanol: String) {
        context.dataStore.edit { preferences ->
            preferences[DEFAULT_TARGET_ETHANOL] = targetEthanol
        }
    }

    val DEFAULT_GAS_ETHANOL = stringPreferencesKey("default_gas_ethanol")
    val default_gas_ethanol_flow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DEFAULT_GAS_ETHANOL] ?: "0.0"
        }
    suspend fun updateDefaultGasEthanol(gasEthanol: String) {
        context.dataStore.edit { preferences ->
            preferences[DEFAULT_GAS_ETHANOL] = gasEthanol
        }
    }
}