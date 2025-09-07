package com.tursko.blend85

import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Test
import org.junit.Assert.*
class BlendCalculatorTest {
    companion object {
        private const val DELTA = 0.1
    }

    @Test
    fun calculateWithEmptyValues() {
        val calculator = BlendCalculator()

        val result = calculator.calculateBlend(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

        assertEquals(0.0, result.e85ToAdd, 0.0)
        assertEquals(0.0, result.gasToAdd, 0.0)
        assertEquals(0.0, result.targetMixResult, 0.0)
    }

    @Test
    fun calculateFreshE30Blend() {
        val calculator = BlendCalculator()
        val result = calculator.calculateBlend(14.7, 0.0, 10.0, 30.0, 85.0, 10.0)

        assertEquals(3.92, result.e85ToAdd, DELTA)
        assertEquals(10.78, result.gasToAdd, DELTA)
        assertEquals(30.0, result.targetMixResult, 0.0)
    }

    @Test
    fun calculateTopOffE30Blend() {
        val calculator = BlendCalculator()
        val result = calculator.calculateBlend(14.7, 20.0, 30.0, 30.0, 85.0, 10.0)

        assertEquals(3.14, result.e85ToAdd, DELTA)
        assertEquals(8.62, result.gasToAdd, DELTA)
        assertEquals(30.0, result.targetMixResult, 0.0)
    }

    @Test
    fun calculateImpossibleE30Blend() {
        val calculator = BlendCalculator()
        val result = calculator.calculateBlend(14.7, 110.0, 30.0, 30.0, 85.0, 10.0)

        assertEquals(0.0, result.e85ToAdd, 0.0)
        assertEquals(0.0, result.gasToAdd, 0.0)
        assertEquals(30.0, result.targetMixResult, 0.0)
    }
}