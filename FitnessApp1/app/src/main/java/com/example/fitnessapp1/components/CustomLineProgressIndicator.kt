package com.example.fitnessapp1.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomLineProgressIndicator(
    modifier: Modifier,
    initialValue: Float,
    primaryColor: Color,
    secondaryColor: Color,
    maxValue: Float,
    length: Float
) {
    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val thickness = width / 15f

            drawLine(
                color = secondaryColor,
                start = Offset(
                    (width - length * 2f) / 2f,
                    height / 2f
                ),
                end = Offset(
                    (width - length * 2f) / 2f + length * 2f,
                    height / 2f
                ),
                strokeWidth = thickness,
                cap = StrokeCap.Round
            )

            drawLine(
                color = primaryColor,
                start = Offset(
                    (width - length * 2f) / 2f,
                    height / 2f
                ),
                end = Offset(
                    x = if (initialValue > maxValue) {
                        (width - length * 2f) / 2f + length * 2f
                    } else {
                        width * (initialValue / maxValue)
                    },
                    height / 2f
                ),
                strokeWidth = thickness,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
fun CustomLineProgressIndicatorPreview() {
    CustomLineProgressIndicator(
        modifier = Modifier
            .size(230f.dp)
            .background(Color.White),
        initialValue = 50f,
        primaryColor = Color.Green,
        secondaryColor = Color.LightGray,
        maxValue = 100f,
        length = 150f,
    )
}