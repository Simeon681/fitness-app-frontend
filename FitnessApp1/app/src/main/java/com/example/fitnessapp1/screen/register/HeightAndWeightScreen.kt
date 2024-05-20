package com.example.fitnessapp1.screen.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.FloatTextField
import com.example.fitnessapp1.components.NormalButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeightAndWeightScreen(
    onArrowClick: () -> Unit,
    height: Float,
    onHeightChange: (Float) -> Unit,
    weight: Float,
    onWeightChange: (Float) -> Unit,
    onNextClick: () -> Unit,
) {
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

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
                        Text(text = stringResource(id = R.string.height_and_weight))
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
                            text = stringResource(id = R.string.how_tall_are_you),
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.padding(12.dp))

                        FloatTextField(
                            value = height.toString() ,
                            labelValue = stringResource(id = R.string.height),
                            onChange = { onHeightChange(it.toFloat()) },
                            painterResource = Icons.Default.Height
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        Text(
                            text = stringResource(id = R.string.how_much_do_you_weigh),
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(modifier = Modifier.padding(12.dp))

                        FloatTextField(
                            value = weight.toString(),
                            labelValue = stringResource(id = R.string.weight),
                            onChange = { onWeightChange(it.toFloat()) },
                            painterResource = Icons.Default.MonitorWeight
                        )

                        Column {
                            Spacer(modifier = Modifier.weight(1f))

                            NormalButton(
                                value = stringResource(id = R.string.next),
                                onClick = {
                                    if (height !in 50f..300f) {
                                        errorMessage = "Height must be between 50 and 300 cm."
                                        showError = true
                                    } else if (weight < 25.0f || weight > 600.0f) {
                                        errorMessage = "Weight must be between 25 and 600 kg."
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
