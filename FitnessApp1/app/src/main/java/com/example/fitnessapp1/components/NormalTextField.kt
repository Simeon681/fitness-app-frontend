package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp1.R
import com.example.fitnessapp1.theme.componentShapes

@Composable
fun NormalTextField(
    value: String,
    labelValue: String,
    onChange: (String) -> Unit,
    painterResource: ImageVector
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        value = value,
        onValueChange = {
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
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NormalTextFieldPreview() {
    NormalTextField(
        value = "",
        labelValue = stringResource(id = R.string.username),
        onChange = {},
        painterResource = Icons.Default.Person
    )
}