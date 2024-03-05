package com.example.fitnessapp1.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier,
    initialValue: Float,
    primaryColor: Color,
    secondaryColor: Color,
    maxValue: Float,
    circularRadius: Float,
    isCircle: Boolean,
    text: String,
    hasButton: Boolean,
    onWaterChange: (Float) -> Unit = {},
    updateActivityStat: () -> Unit = {},
    imageVector: ImageVector? = null
) {
    var circularCenter by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val circularThickness = if (isCircle) width / 10f else width / 16f

            circularCenter = Offset(width / 2f, height / 2f)

            if (isCircle) {
                drawCircle(
                    style = Stroke(
                        width = circularThickness
                    ),
                    color = secondaryColor,
                    radius = circularRadius,
                    center = circularCenter
                )
            } else {
                drawArc(
                    color = secondaryColor,
                    startAngle = 140f,
                    sweepAngle = 260f,
                    style = Stroke(
                        width = circularThickness,
                        cap = StrokeCap.Round
                    ),
                    useCenter = false,
                    size = Size(
                        width = circularRadius * 2f,
                        height = circularRadius * 2f
                    ),
                    topLeft = Offset(
                        x = circularCenter.x - circularRadius,
                        y = circularCenter.y - circularRadius
                    )
                )
            }

            drawArc(
                color = primaryColor,
                startAngle = if (isCircle) -90f else 140f,
                sweepAngle = if (initialValue < maxValue) {
                    (if (isCircle) 360f else 260f) / maxValue * initialValue
                } else {
                    (if (isCircle) 360f else 260f) / maxValue * maxValue
                },
                style = Stroke(
                    width = circularThickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width = circularRadius * 2f,
                    height = circularRadius * 2f
                ),
                topLeft = Offset(
                    x = circularCenter.x - circularRadius,
                    y = circularCenter.y - circularRadius
                )
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageVector != null) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = imageVector,
                    contentDescription = null
                )
            }

            Text(
                text = if (hasButton) "$initialValue"
                    else if (!hasButton && !isCircle) "${maxValue.minus(initialValue).toInt()}"
                    else "${initialValue.toInt()}",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            NormalGrayText(
                text = text,
                modifier = Modifier
            )
        }

        if (hasButton) {
            FloatingActionButton(
                onClick = {
                    onWaterChange(0.25f)
                    updateActivityStat()
                },
                modifier = Modifier
                    .padding(top = (circularRadius / 1.45f).dp)
                    .size((circularRadius / 3.5f).dp),
                shape = CircleShape,
                containerColor = Color.LightGray,
                contentColor = Color.Cyan
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add button"
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomCircularProgressIndicatorPreview() {
    CustomCircularProgressIndicator(
        modifier = Modifier
            .size(230f.dp)
            .background(Color.White),
        initialValue = 1f,
        primaryColor = Color.Green,
        secondaryColor = Color.LightGray,
        maxValue = 100f,
        circularRadius = 230f,
        isCircle = false,
        text = "Remaining",
        hasButton = true,
        imageVector = Icons.AutoMirrored.Filled.DirectionsWalk
    )
}