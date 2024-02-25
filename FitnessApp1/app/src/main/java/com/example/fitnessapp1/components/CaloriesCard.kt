package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.resource.response.ProfileResponse
import com.example.fitnessapp1.theme.TextColor

@Composable
fun CaloriesCard(
    profile: ProfileResponse?,
    activityStat: ActivityStatResponse?
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
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
                Column(
                    modifier = Modifier.width(86.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (activityStat?.calories == null) "0"
                        else "${activityStat.calories}",
                        modifier = Modifier
                            .heightIn()
                            .padding(top = 64.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = TextColor
                    )

                    NormalGrayText(
                        text = stringResource(id = R.string.eaten),
                        modifier = Modifier
                            .heightIn()
                            .padding(top = 1.dp)
                    )
                }

                CustomCircularProgressIndicator(
                    modifier = Modifier
                        .size(160.dp),
                    initialValue = activityStat?.calories?.toFloat() ?: 0f,
                    primaryColor = Color.Green,
                    secondaryColor = Color.LightGray,
                    maxValue = profile?.goalCalories?.toFloat() ?: 0f,
                    circularRadius = 200f,
                    isCircle = false,
                    text = stringResource(id = R.string.remaining),
                    hasButton = false,
                    imageVector = null
                )

                Column(
                    modifier = Modifier.width(86.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${profile?.goalCalories}",
                        modifier = Modifier
                            .heightIn()
                            .padding(top = 64.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = TextColor
                    )

                    NormalGrayText(
                        text = stringResource(id = R.string.goal),
                        modifier = Modifier
                            .heightIn()
                            .padding(top = 1.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.width(110.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalGrayText(
                        text = stringResource(id = R.string.carbs),
                        modifier = Modifier.heightIn()
                    )

                    CustomLineProgressIndicator(
                        modifier = Modifier
                            .height(24.dp),
                        initialValue = activityStat?.carbs ?: 0f,
                        primaryColor = Color.Green,
                        secondaryColor = Color.LightGray,
                        maxValue = profile?.goalCarbs ?: 100f,
                        length = 110f
                    )

                    Text(
                        text = "${activityStat?.carbs} / ${profile?.goalCarbs} g",
                        modifier = Modifier
                            .heightIn(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = TextColor
                    )
                }

                Column(
                    modifier = Modifier.width(112.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalGrayText(
                        text = stringResource(id = R.string.protein),
                        modifier = Modifier.heightIn()
                    )

                    CustomLineProgressIndicator(
                        modifier = Modifier
                            .height(24.dp),
                        initialValue = activityStat?.protein ?: 0f,
                        primaryColor = Color.Green,
                        secondaryColor = Color.LightGray,
                        maxValue = profile?.goalProtein ?: 100f,
                        length = 110f
                    )

                    Text(
                        text = "${activityStat?.protein} / ${profile?.goalProtein} g",
                        modifier = Modifier
                            .heightIn(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = TextColor
                    )
                }

                Column(
                    modifier = Modifier.width(110.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalGrayText(
                        text = stringResource(id = R.string.fat),
                        modifier = Modifier.heightIn()
                    )

                    CustomLineProgressIndicator(
                        modifier = Modifier
                            .height(24.dp),
                        initialValue = activityStat?.fat ?: 0f,
                        primaryColor = Color.Green,
                        secondaryColor = Color.LightGray,
                        maxValue = profile?.goalFat ?: 100f,
                        length = 110f
                    )

                    Text(
                        text = "${if (activityStat?.fat != null) activityStat.fat else 0f} / ${profile?.goalFat} g",
                        modifier = Modifier
                            .heightIn(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        color = TextColor
                    )
                }
            }
        }
    }
}