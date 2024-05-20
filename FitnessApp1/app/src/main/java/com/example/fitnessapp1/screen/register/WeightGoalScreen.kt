package com.example.fitnessapp1.screen.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.SelectingItem
import com.example.fitnessapp1.shared.WeightGoal

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeightGoalScreen(
    onArrowClick: () -> Unit = {},
    onWeightGoalSelected: (WeightGoal) -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(-1) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.DarkGray
                    ),
                    title = {
                        Text(text = stringResource(id = R.string.weight_goal))
                    },
                    navigationIcon = {
                        IconButton(onClick = { onArrowClick() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                        }
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(16.dp)

                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.your_weight_goal),
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        repeat(WeightGoal.entries.size) { index ->
                            Spacer(modifier = Modifier.padding(4.dp))

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = selectedIndex == index,
                                        onClick = {
                                            selectedIndex =
                                                if (selectedIndex != index) {
                                                    index
                                                } else {
                                                    -1
                                                }

                                            if (selectedIndex != -1) {
                                                onWeightGoalSelected(WeightGoal.entries[selectedIndex])
                                            }
                                        }
                                    ),
                                shape = RoundedCornerShape(16.dp),
                                border = CardDefaults.outlinedCardBorder(
                                    true
                                ),
                                colors = CardDefaults.outlinedCardColors(
                                    containerColor =
                                    if (selectedIndex == index) {
                                        Color.Cyan.copy(alpha = 0.1f)
                                    } else {
                                        Color.Transparent
                                    }
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = WeightGoal.entries[index].displayName,
                                    color = Color.Cyan
                                )
                            }
                        }

                        SelectingItem(
                            errorMessage = stringResource(id = R.string.weight_goal_error),
                            selectedIndex = selectedIndex,
                            onClick = onNextClick
                        )
                    }
                }
            }
        )
    }
}
