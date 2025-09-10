package com.tursko.blend85

import android.content.Context
import androidx.lifecycle.ViewModel

class SettingsViewModel(context: Context) : ViewModel() {
    private val settingsDataStore = SettingsDataStore(context)
}