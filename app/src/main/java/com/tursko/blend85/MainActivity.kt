package com.tursko.blend85

import android.os.Bundle
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tursko.blend85.ui.theme.Blend85Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            Blend85Theme {
                //Scaffold() { innerPadding ->
                    CalculatorScreen()
                //}
            }
        }
    }
}

@Composable
fun CalculatorScreen(
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    val uiState by calculatorViewModel.uiState.collectAsState()
    Scaffold ()
    { innerPadding ->
        Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
        ) {
            TextField(
                value = uiState.tankInputValue,
                onValueChange = { calculatorViewModel.onUpdateTankInputValue(it) },
                label = { Text("Tank size") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = uiState.gasEthInputValue,
                onValueChange = { calculatorViewModel.onUpdateGasEthInputValue(it) },
                label = { Text("Gas Ethanol %") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = uiState.e85EthInputValue,
                onValueChange = { calculatorViewModel.onUpdateE85EthInputValue(it) },
                label = { Text("E85 Ethanol %") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = uiState.targetMixInputValue,
                onValueChange = { calculatorViewModel.onUpdateTargetMixInputValue(it) },
                label = { Text("Target Blend/Mix") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = uiState.currFuelInputValue,
                onValueChange = { calculatorViewModel.onUpdateCurrFuelInputValue(it) },
                label = { Text("Current Fuel Level %") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = uiState.currMixInputValue,
                onValueChange = { calculatorViewModel.onUpdateCurrMixInputValue(it) },
                label = { Text("Current Ethanol Mix") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = { calculatorViewModel.calculateBlend() }
            ) {
                Text("Calculate")
            }

            Text("E85 to Add: ${uiState.e85ToAdd}")
            Text("Pump gas to Add: ${uiState.gasToAdd}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    Blend85Theme {
        CalculatorScreen()
    }
}