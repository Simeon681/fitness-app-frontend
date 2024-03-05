package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp1.R
import com.example.fitnessapp1.shared.WeightGoal
import com.example.fitnessapp1.theme.componentShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightGoalTextField(
    value: WeightGoal?,
    onChange: (WeightGoal) -> Unit,
    painterResource: ImageVector
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small)
                .menuAnchor(),
            value = value?.displayName ?: stringResource(id = R.string.weight_goal), // TODO change string resource
            onValueChange = {},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            readOnly = true,
            leadingIcon = {
                Icon(imageVector = painterResource, contentDescription = null)
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )
        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            WeightGoal.entries.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.displayName) },
                    onClick = {
                        onChange(it)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun WeightGoalTextFieldPreview() {
    WeightGoalTextField(
        value = null as WeightGoal?,
        onChange = {},
        painterResource = Icons.Default.FitnessCenter
    )
}