package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateTextField(
    value: String,
    onChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

    isError = isDateRight(text)

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.date_of_birth)) },
        value = if (value == "") "" else value,
        onValueChange = { it ->
            val filteredText = it.filter { it.isDigit() }

            if (filteredText.length <= 8) {
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
            Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Cyan,
            unfocusedBorderColor = Color.LightGray
        ),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = stringResource(id = R.string.enter_valid_date))
            } else {
                Text(text = "Format: DD/MM/YYYY")
            }
        },
        trailingIcon = {
            if (isError) {
                Icon(imageVector = Icons.Default.Error, contentDescription = null)
            }
        }
    )
}

fun isDateValid(date: String): Boolean {
    try {
        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return true
    } catch (e: Exception) {
        return false
    }
}

fun isDateRight(date: String): Boolean {
    return date.length == 10 && (!isDateValid(date) ||
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .isAfter(LocalDate.now()) ||
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .isBefore(LocalDate.now().minusYears(100))
            )
}
