package com.example.fitnessapp1.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.fitnessapp1.R

@Composable
fun SelectingItem(
    errorMessage: String,
    selectedIndex: Int,
    onClick: () -> Unit
) {
    var showError by remember { mutableStateOf(false) }

    if (showError) {
        Toast.makeText(
            LocalContext.current,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()

        showError = false
    }

    Column {
        Spacer(modifier = Modifier.weight(1f))

        NormalButton(
            value = stringResource(id = R.string.next),
            onClick = {
                if (selectedIndex == -1) {
                    showError = true
                } else {
                    onClick()
                }
            }
        )
    }
}