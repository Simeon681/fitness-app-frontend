package com.example.fitnessapp1.components

import android.util.Patterns
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R

@Composable
fun EmailTextField(
    value: String,
    onChange: (String) -> Unit
) {
    var isEmailValid by remember { mutableStateOf(true) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.email)) },
        value = value,
        onValueChange = {
            isEmailValid = checkEmail(it)
            onChange(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = null)
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Cyan,
            unfocusedBorderColor = Color.LightGray
        ),
        isError = !isEmailValid,
        supportingText = {
            if (!isEmailValid) {
                Text(text = "Enter a valid email address!")
            }
        },
        trailingIcon = {
            if (!isEmailValid) {
                Icon(imageVector = Icons.Default.Error, contentDescription = null)
            }
        }
    )
}

fun checkEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}