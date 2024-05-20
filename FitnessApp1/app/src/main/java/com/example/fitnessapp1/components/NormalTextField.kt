package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R

@Composable
fun NormalTextField(
    value: String,
    labelValue: String,
    onChange: (String) -> Unit,
    painterResource: ImageVector,
    check: Boolean = false
) {
    var isError by remember { mutableStateOf(true) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = labelValue) },
        value = value,
        onValueChange = {
            isError = checkUsername(it)
            onChange(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(imageVector = painterResource, contentDescription = null)
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Cyan,
            unfocusedBorderColor = Color.LightGray
        ),
        isError = !isError && check,
        supportingText = {
            if (!isError && check) {
                Text(text = stringResource(id = R.string.username_error))
            }
        },
        trailingIcon = {
            if (!isError && check) {
                Icon(imageVector = Icons.Default.Error, contentDescription = null)
            }
        }
    )
}

fun checkUsername(username: String): Boolean {
    return username.length in 4..16
}

