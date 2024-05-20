package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalElevatedButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier.size(200.dp, 50.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = color,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
