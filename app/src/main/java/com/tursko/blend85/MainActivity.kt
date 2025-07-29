package com.tursko.blend85

import android.os.Bundle
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Blend85Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var tankInputValue by remember { mutableStateOf("") }
    var gasEthInputValue by remember { mutableStateOf("") }
    var e85EthInputValue by remember { mutableStateOf("") }
    var targetMixInputValue by remember { mutableStateOf("")}
    var currFuelInputValue by remember { mutableStateOf("") }
    var currMixInputValue by remember { mutableStateOf("")}
    Column (modifier = Modifier.padding(20.dp)){
        TextField(
            value = tankInputValue,
            onValueChange = { tankInputValue = it },
            label = { Text("Tank size") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = gasEthInputValue,
            onValueChange = { gasEthInputValue = it },
            label = { Text("Gas Ethanol Percentage") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = e85EthInputValue,
            onValueChange = { e85EthInputValue = it },
            label = { Text("E85 Ethanol Percentage") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = targetMixInputValue,
            onValueChange = { targetMixInputValue = it },
            label = { Text("Target Blend/Mix") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = currFuelInputValue,
            onValueChange = { currFuelInputValue = it },
            label = { Text("Current Fuel Level Percentage") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = currMixInputValue,
            onValueChange = { currMixInputValue = it },
            label = { Text("Current Ethanol Mix") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {}) {
            Text("Calculate")
        }
        Text("E85 to Add: ")
        Text("Pump gas to Add:")
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    Blend85Theme {
        CalculatorScreen()
    }
}