package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.resource.response.HeightChangeResponse

@Composable
fun HeightLineChartCard(
    list: List<HeightChangeResponse>,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .width(394.dp)
            .height(300.dp),
        colors = CardDefaults.elevatedCardColors(
            Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.height_changes),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { onClick() },
                    modifier = Modifier.size(30.dp)
                ) {
                     Icon(
                         Icons.Default.Edit,
                         null
                     )
                }
            }

            HeightLineChart(list = list)
        }
    }
}
