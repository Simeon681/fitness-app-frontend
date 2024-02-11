package com.example.fitnessapp1.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp1.theme.componentShapes
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTextField(
    labelValue: String,
    onChange: (LocalDate) -> Unit,
    painterResource: ImageVector
) {
    var pickedDate by remember {
        mutableStateOf(null as LocalDate?)
    }

    val dateDialogState = rememberMaterialDialogState()

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        value = pickedDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: "",
        placeholder = { dateDialogState.show() },
        onValueChange = {
            onChange(LocalDate.parse(it))
        },
        readOnly = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        leadingIcon = {
            Icon(imageVector = painterResource, contentDescription = null)
        }
    )
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Select a date",
            allowedDateValidator = { it.isBefore(LocalDate.now()) }
        ) {
            pickedDate = it
            onChange(it)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DateTextFieldPreview() {
    DateTextField(
        labelValue = "Date of Birth",
        onChange = {},
        painterResource = Icons.Default.CalendarMonth
    )
}