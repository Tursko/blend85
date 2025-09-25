package com.tursko.blend85

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SettingsViewModel(private val settingsDataStore: SettingsDataStore): ViewModel() {
    val defaultTargetEthanol = settingsDataStore.default_target_ethanol_flow
    val defaultGasEthanol = settingsDataStore.default_gas_ethanol_flow
    val x = MutableStateFlow(value = "")

    init {
        viewModelScope.launch {
            x.debounce(1000).distinctUntilChanged().collect() {
                //updateDefaultTargetEthanol(it)
                settingsDataStore.updateDefaultTargetEthanol(it)
            }
        }
    }

    fun updateDefaultTargetEthanol(targetEthanol: String) {
        x.value = targetEthanol
    }

    fun updateDefaultGasEthanol(gasEthanol: String) {
        viewModelScope.launch {
            settingsDataStore.updateDefaultGasEthanol(gasEthanol)
        }
    }
}