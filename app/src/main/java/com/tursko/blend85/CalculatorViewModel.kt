package com.tursko.blend85

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CalculatorUiState(
    val tankInputValue: String = "",
    val gasEthInputValue: String = "",
    val e85EthInputValue: String = "",
    val targetMixInputValue: String = "",
    val currFuelInputValue: String = "",
    val currMixInputValue: String = "",
    val e85ToAdd: String = "",
    val gasToAdd: String = "",
    val targetMixResult: String = ""
)

class CalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    fun onUpdateTankInputValue(newValue: String) {
        _uiState.update { currentState -> currentState.copy(tankInputValue = newValue) }
    }

    fun onUpdateGasEthInputValue(newValue: String) {
        _uiState.update { currentState -> currentState.copy(gasEthInputValue = newValue) }
    }

    fun onUpdateE85EthInputValue(newValue: String) {
        _uiState.update { currentState -> currentState.copy(e85EthInputValue = newValue) }
    }

    fun onUpdateTargetMixInputValue(newValue: String) {
        _uiState.update { currentState -> currentState.copy(targetMixInputValue = newValue) }
    }

    fun onUpdateCurrFuelInputValue(newValue: String) {
        _uiState.update { currentState -> currentState.copy(currFuelInputValue = newValue) }
    }

    fun onUpdateCurrMixInputValue(newValue: String) {
        _uiState.update { currentState -> currentState.copy(currMixInputValue = newValue) }
    }

    fun calculateBlend() {
        val tankInputValue = _uiState.value.tankInputValue.toDoubleOrNull() ?: 0.0
        val gasEthInputValue = _uiState.value.gasEthInputValue.toDoubleOrNull() ?: 0.0
        val e85EthInputValue = _uiState.value.e85EthInputValue.toDoubleOrNull() ?: 0.0
        val targetMixInputValue = _uiState.value.targetMixInputValue.toDoubleOrNull() ?: 0.0
        val currFuelInputValue = _uiState.value.currFuelInputValue.toDoubleOrNull() ?: 0.0
        val currMixInputValue = _uiState.value.currMixInputValue.toDoubleOrNull() ?: 0.0

        val currentFuel = tankInputValue * currFuelInputValue
        val currentE85 = currentFuel * currMixInputValue
        val targetE85 = tankInputValue * targetMixInputValue

        //val e85Result = (currMixInputValue + (tankInputValue - currFuelInputValue) * gasEthInputValue - targetMixInputValue) / (gasEthInputValue - e85EthInputValue)
        val e85ToAdd = (currentE85 + (tankInputValue - currentFuel) * gasEthInputValue - targetE85) / (gasEthInputValue - e85EthInputValue)
        val gasToAdd = tankInputValue - currentFuel - e85ToAdd

        if (e85ToAdd < 0 && gasToAdd < 0) {
            _uiState.value = _uiState.value.copy(
                e85ToAdd = e85ToAdd.toString(),
                gasToAdd = gasToAdd.toString(),
                targetMixResult = targetMixInputValue.toString()
            )
        } else {
            _uiState.value = _uiState.value.copy(
                e85ToAdd = "Error",
                gasToAdd = "Error",
                targetMixResult = "Error"
            )
        }
    }
}