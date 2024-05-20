package com.example.fitnessapp1.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.ButtonDefaults.elevatedButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.FloatTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.shared.MealType
import com.example.fitnessapp1.state.MealStatState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealScreen(
    onGetMeal: () -> Unit,
    state: MealStatState,
    onStateChange: (MealStatState) -> Unit,
    meal: MealResponse?,
    portion: Float,
    onPortionChange: (Float) -> Unit,
    onMealTypeChange: (MealType) -> Unit,
    onAddMealToPlan: () -> Unit,
    onAddMealToPlanClick: () -> Unit,
    onArrowClick: () -> Unit
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(-1) }
    var showError by rememberSaveable { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    if (showError) {
        Toast.makeText(
            LocalContext.current,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()

        showError = false
    }

    LaunchedEffect(key1 = true) {
        onGetMeal()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
    ) {
        val lazyListState = rememberLazyListState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.DarkGray
                    ),
                    title = {
                        Text(text = stringResource(id = R.string.home))
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
            }, content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 14.dp, top = 16.dp, end = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = lazyListState
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(it)
                            ) {
                                HeadingText(
                                    value = "${meal?.name}",
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.padding(8.dp))

                                Text(
                                    text = "Calories: ${meal?.calories} kcal",
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "Protein: ${meal?.protein} g",
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "Carbohydrates: ${meal?.carbs} g",
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "Fat: ${meal?.fat} g",
                                    fontSize = 20.sp
                                )

                                Spacer(modifier = Modifier.padding(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    repeat(MealType.entries.size) { index ->
                                        Spacer(modifier = Modifier.padding(2.dp))

                                        Card(
                                            modifier = Modifier
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
                                                            onMealTypeChange(MealType.entries[selectedIndex])
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
                                                modifier = Modifier.padding(14.dp),
                                                text = MealType.entries[index].displayName,
                                                color = Color.Cyan
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.padding(16.dp))

                                FloatTextField(
                                    value = portion.toString(),
                                    labelValue = stringResource(id = R.string.portion),
                                    onChange = { onPortionChange(it.toFloat()) },
                                    painterResource = Icons.Default.Fastfood
                                )

                                Spacer(modifier = Modifier.padding(16.dp))

                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    ElevatedButton(
                                        modifier = Modifier.size(220.dp, 50.dp),
                                        onClick = {
                                            if (selectedIndex == -1) {
                                                showError = true
                                                errorMessage = "Please select a meal type!"
                                            } else if (portion == 0f || portion.isNaN()) {
                                                showError = true
                                                errorMessage = "Please enter a valid portion!"
                                            } else {
                                                onAddMealToPlan()
                                            }
                                        },
                                        colors = elevatedButtonColors(
                                            containerColor = Color.Cyan,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.add_to_plan),
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    }
                                }
                            }
                        }
                    }

                    if (state is MealStatState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    if (state is MealStatState.Error) {
                        Toast.makeText(
                            LocalContext.current,
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    if (state is MealStatState.Success) {
                        onAddMealToPlanClick()
                        onStateChange(MealStatState.Empty)
                    }
                }
            }
        )
    }
}
