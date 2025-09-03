package com.tursko.blend85

import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Test
import org.junit.Assert.*

class CalculatorViewModelTest {
    @Test
    fun `calculateResult should handle empty inputs`() {
        val viewModel = CalculatorViewModel()

        viewModel.calculateBlend()

        assertEquals("0.0", viewModel.uiState.value.e85ToAdd)
        assertEquals("0.0", viewModel.uiState.value.gasToAdd)
    }
}