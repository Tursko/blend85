package com.tursko.blend85

import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Test
import org.junit.Assert.*

class CalculatorViewModelTest {
    @Test
    fun calculateWithEmptyValues() {
        val viewModel = CalculatorViewModel()
        val expectedValue1 = "0.00"
        val expectedValue2 = "0"

        viewModel.calculateBlend()

        assertEquals(expectedValue1, viewModel.uiState.value.e85ToAdd)
        assertEquals(expectedValue1, viewModel.uiState.value.gasToAdd)
        assertEquals(expectedValue2, viewModel.uiState.value.targetMixResult)
    }
}