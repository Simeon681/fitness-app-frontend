package com.example.fitnessapp1.screen.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.DateTextField
import com.example.fitnessapp1.components.IntTextField
import com.example.fitnessapp1.components.NormalButton
import com.example.fitnessapp1.components.isDateRight
import com.example.fitnessapp1.shared.Gender

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PersonalInformationScreen(
    onArrowClick: () -> Unit = {},
    onPersonalInformationSelected: (Gender) -> Unit = {},
    dateOfBirth: String,
    onDateOfBirthChange: (String) -> Unit,
    goalSteps: Int,
    onGoalStepsChange: (Int) -> Unit,
    onNextClick: () -> Unit = {}
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(-1) }
    var showError by rememberSaveable { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("")}

    if (showError) {
        Toast.makeText(
            LocalContext.current,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()

        showError = false
    }

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
                        Text(text = stringResource(id = R.string.personal_information))
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
                            text = stringResource(id = R.string.your_gender),
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.padding(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(Gender.entries.size) { index ->
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

                                                onPersonalInformationSelected(Gender.entries[index])
                                            }
                                        )
                                        .weight(1f, true),
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
                                        text = Gender.entries[index].displayName,
                                        color = Color.Cyan,
                                    )
                                }

                                Spacer(modifier = Modifier.padding(4.dp))
                            }
                        }

                        Spacer(modifier = Modifier.padding(16.dp))

                        Text(
                            text = stringResource(id = R.string.your_date_of_birth),
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.padding(12.dp))

                        DateTextField(
                            value = dateOfBirth,
                            onChange = onDateOfBirthChange
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        Text(
                            text = stringResource(id = R.string.your_goal_steps),
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.padding(12.dp))

                        IntTextField(
                            value = goalSteps.toString(),
                            labelValue = stringResource(id = R.string.goal_steps),
                            onChange = { onGoalStepsChange(it.toInt()) },
                            painterResource = Icons.AutoMirrored.Filled.DirectionsWalk,
                            check = true
                        )

                        Column {
                            Spacer(modifier = Modifier.weight(1f))

                            NormalButton(
                                value = stringResource(id = R.string.next),
                                onClick = {
                                    if (selectedIndex == -1) {
                                        errorMessage = "Please select your gender."
                                        showError = true
                                    } else if (dateOfBirth.isEmpty()) {
                                        errorMessage = "Please select your date of birth."
                                        showError = true
                                    } else if (isDateRight(dateOfBirth)) {
                                        errorMessage = "Please enter a valid date of birth."
                                        showError = true
                                    }else if (goalSteps < 1000) {
                                        errorMessage = "Your goal steps must be minimum 1000."
                                        showError = true
                                    } else {
                                        onNextClick()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}
