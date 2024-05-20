package com.example.fitnessapp1.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun CustomLineProgressIndicator(
    modifier: Modifier,
    initialValue: Float,
    primaryColor: Color,
    secondaryColor: Color,
    maxValue: Float,
    length: Float
) {
    var animationPlayed by remember { mutableStateOf(false) }
    var curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) initialValue else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 100
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

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

            if (initialValue > 0) {
                drawLine(
                    color = primaryColor,
                    start = Offset(
                        (width - length * 2f) / 2f,
                        height / 2f
                    ),
                    end = Offset(
                        x = if (curPercentage.value >= maxValue) {
                            (width - length * 2f) / 2f + length * 2f
                        } else {
                            (width - length * 2f) / 2f + length * 2f * (curPercentage.value / maxValue)
                        },
                        height / 2f
                    ),
                    strokeWidth = thickness,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}
