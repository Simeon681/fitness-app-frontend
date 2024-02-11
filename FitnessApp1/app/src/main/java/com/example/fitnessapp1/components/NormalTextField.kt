package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp1.R
import com.example.fitnessapp1.theme.componentShapes

@Composable
fun NormalTextField(
    labelValue: String,
    onChange: (String) -> Unit,
    keyboardType: KeyboardType,
    painterResource: ImageVector
) {
    var textValue by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        value = textValue,
        onValueChange = {
            textValue = it
            onChange(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
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
        labelValue = stringResource(id = R.string.username),
        onChange = {},
        keyboardType = KeyboardType.Text,
        painterResource = Icons.Default.Person
    )
}

@Preview(showBackground = true)
@Composable
fun NumberTextFieldPreview() {
    NormalTextField(
        labelValue = stringResource(id = R.string.goal_calories),
        onChange = {},
        keyboardType = KeyboardType.Number,
        painterResource = Icons.Default.Fastfood
    )
}

@Preview(showBackground = true)
@Composable
fun DecimalTextFieldPreview() {
    NormalTextField(
        labelValue = stringResource(id = R.string.weight),
        onChange = {},
        keyboardType = KeyboardType.Decimal,
        painterResource = Icons.Default.Scale
    )
}
