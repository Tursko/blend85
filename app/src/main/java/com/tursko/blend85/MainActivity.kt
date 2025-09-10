package com.tursko.blend85

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tursko.blend85.ui.theme.Blend85Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MenuAnchorType.Companion.PrimaryEditable
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val scrollState = rememberScrollState()
    Scaffold (
        bottomBar = {
//            NavigationBar {  }
        }
    )
    { innerPadding ->
        Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            VehicleDropDown(
//                modifier = Modifier.fillMaxWidth()
//            )

            TextField(
                value = uiState.tankInputValue,
                onValueChange = { calculatorViewModel.onUpdateTankInputValue(it) },
                label = { Text("Gas Tank Size") },
                singleLine = true,
                suffix = { Text("Gal")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.currFuelInputValue,
                onValueChange = { calculatorViewModel.onUpdateCurrFuelInputValue(it) },
                label = { Text("Current Tank Level") },
                singleLine = true,
                suffix = { Text("%")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()

            )
            TextField(
                value = uiState.currMixInputValue,
                onValueChange = { calculatorViewModel.onUpdateCurrMixInputValue(it) },
                label = { Text("Current Ethanol Blend") },
                singleLine = true,
                prefix = { Text("E")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(thickness = 3.dp)

            TextField(
                value = uiState.targetMixInputValue,
                onValueChange = { calculatorViewModel.onUpdateTargetMixInputValue(it) },
                label = { Text("Target Blend") },
                singleLine = true,
                prefix = { Text("E")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.e85EthInputValue,
                onValueChange = { calculatorViewModel.onUpdateE85EthInputValue(it) },
                label = { Text("E85 Ethanol Percentage") },
                singleLine = true,
                suffix = { Text("%")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.gasEthInputValue,
                onValueChange = { calculatorViewModel.onUpdateGasEthInputValue(it) },
                label = { Text("Gas Ethanol Percentage") },
                singleLine = true,
                suffix = { Text("%")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { calculatorViewModel.calculateBlend() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculate Blend")
            }

            ResultSection(uiState.e85ToAdd, uiState.gasToAdd, uiState.targetMixResult)
        }
    }
}

@Composable
fun ResultSection(e85: String, gas: String, targetMix: String) {
    if (e85.isNotBlank() && gas.isNotBlank() && targetMix.isNotBlank()) {
        Text("E85 to Add: ${e85}g")
        Text("Pump Gas to Add: ${gas}g")
        Text("Resulting Blend: E${targetMix}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDropDown(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = "Vehicle",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor(PrimaryEditable, true).fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("test") },
                onClick = {}
            )

        }
    }
}

@Composable
fun VolumeUnitSegmentedButton (modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Gallons", "Liters")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
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