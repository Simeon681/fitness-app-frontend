package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp1.theme.componentShapes

@Composable
fun DateTextField(
    value: String,
    onChange: (String) -> Unit,
    painterResource: ImageVector
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text("Date of birth(dd/MM/yyyy)") },
        value = text,
        onValueChange = { it ->
            val filteredText = it.filter { it.isDigit() }

            if (filteredText.length <= 10) {
                text = filteredText.replace("/", "")
                if (text.length >= 2 && text[1] != '/') {
                    text = text.substring(0, 2) + '/' + text.substring(2)
                }
                if (text.length >= 5 && text[4] != '/') {
                    text = text.substring(0, 5) + '/' + text.substring(5)
                }
            }

            onChange(text)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        leadingIcon = {
            Icon(imageVector = painterResource, contentDescription = null)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DateTextFieldPreview() {
    DateTextField(
        value = "",
        onChange = {},
        painterResource = Icons.Default.CalendarMonth
    )
}