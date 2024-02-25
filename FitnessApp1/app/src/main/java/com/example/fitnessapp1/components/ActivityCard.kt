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
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.resource.response.ProfileResponse

@Composable
fun ActivityCard(
    steps: Int?,
    profile: ProfileResponse?,
    activityStat: ActivityStatResponse,
    onWaterChange: (Float) -> Unit,
    updateActivityStat: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.elevatedCardColors(
            Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CustomCircularProgressIndicator(
                    modifier = Modifier
                        .size(160.dp),
                    initialValue = steps?.toFloat() ?: 0f,
                    primaryColor = Color.Green,
                    secondaryColor = Color.LightGray,
                    maxValue = profile?.goalSteps?.toFloat() ?: 100f,
                    circularRadius = 200f,
                    isCircle = true,
                    text = stringResource(id = R.string.steps),
                    hasButton = false,
                    imageVector = Icons.AutoMirrored.Filled.DirectionsWalk
                )

                Spacer(modifier = Modifier.width(32.dp))

                CustomCircularProgressIndicator(
                    modifier = Modifier
                        .size(160.dp),
                    initialValue = activityStat.water,
                    primaryColor = Color.Cyan,
                    secondaryColor = Color.LightGray,
                    maxValue = profile?.goalWater ?: 0f,
                    circularRadius = 140f,
                    isCircle = false,
                    text = stringResource(id = R.string.Liters),
                    hasButton = true,
                    onWaterChange = onWaterChange,
                    updateActivityStat = updateActivityStat
                )
            }
        }
    }
}