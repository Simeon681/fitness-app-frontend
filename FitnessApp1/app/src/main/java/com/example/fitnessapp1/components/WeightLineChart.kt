package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.fitnessapp1.resource.response.WeightChangeResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun WeightLineChart(
    list: List<WeightChangeResponse>
) {
    val pointsData: MutableList<Point> = mutableListOf()
    val dates: MutableList<LocalDate> = mutableListOf()
    val mutableList = list.reversed().toMutableList()

    mutableList.forEach {
        dates.add(LocalDate.parse(it.date))
        pointsData.add(Point((6 - mutableList.indexOf(it)).toFloat(), it.weight))
    }

    if (dates.size < 7) {
        for (i in 0 until (7 - list.size)) {
            dates.add(dates.minOf { it }.minusDays(1))
            pointsData.add(Point((7 - pointsData.size - 1).toFloat(), mutableList.last().weight))
        }
    }

    val steps = 4

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.White)
        .steps(steps + 2)
        .labelData { i ->
            dates.sorted()[i].format(
                DateTimeFormatter.ofPattern("dd-MM")
            ).toString()
        }
        .labelAndAxisLinePadding(15.dp)
        .axisStepSize(48.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.White)
        .labelAndAxisLinePadding(15.dp)
        .labelData { i ->
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Color.Red,
                        alpha = 0.2f
                    ),
                    IntersectionPoint(radius = 5.dp),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Magenta,
                                Color.Red,
                            )
                        ),
                        alpha = 0.2f
                    ),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = Color.White
    )

    LineChart(
        modifier = Modifier
            .width(360.dp)
            .height(300.dp),
        lineChartData = lineChartData
    )
}
