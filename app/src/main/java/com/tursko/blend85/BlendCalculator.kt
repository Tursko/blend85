package com.tursko.blend85

import com.tursko.blend85.data.CalculationResult

class BlendCalculator {
    fun calculateBlend(tankSize: Double, currentTankLevel: Double, currentBlend: Double,
                       targetBlend: Double, e85Eth: Double, gasEth: Double): CalculationResult {
        var tankSize = tankSize
        var currentTankLevel = currentTankLevel / 100
        var currentBlend = currentBlend / 100
        var targetBlend = targetBlend / 100
        var e85Eth = e85Eth / 100
        var gasEth = gasEth / 100

        val currentFuel = (tankSize * currentTankLevel) ?: 0.0
        val currentE85 = (currentFuel * currentBlend) ?: 0.0
        val targetE85 = (tankSize * targetBlend) ?: 0.0

        var e85ToAdd =
            ((currentE85 + (tankSize - currentFuel) * gasEth - targetE85) / (gasEth - e85Eth)) ?: 0.0

        if (e85ToAdd <= 0 || e85ToAdd.isNaN()) {
            e85ToAdd = 0.0
        }

        var gasToAdd = ( tankSize - currentFuel ) - e85ToAdd
        if (gasToAdd <= 0 || gasToAdd.isNaN()) {
            gasToAdd = 0.0
        }

        targetBlend = (targetBlend * 100)

        val calculationResult = CalculationResult(e85ToAdd, gasToAdd, targetBlend)
        return calculationResult
    }
}