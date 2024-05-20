package com.example.fitnessapp1.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.FloatTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.NormalElevatedButton
import com.example.fitnessapp1.state.WeightChangeState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateWeightScreen(
    state: WeightChangeState,
    onStateChange: (WeightChangeState) -> Unit,
    weight: Float,
    onWeightChange: (Float) -> Unit,
    onButtonClick: () -> Unit,
    onArrowClick: () -> Unit
) {
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

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
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
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(start = 14.dp, top = 16.dp, end = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HeadingText(
                            value = stringResource(id = R.string.update_weight),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.padding(20.dp))

                        FloatTextField(
                            value = weight.toString(),
                            labelValue = stringResource(id = R.string.weight),
                            onChange = { onWeightChange(it.toFloat()) },
                            painterResource = Icons.Default.MonitorWeight
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        NormalElevatedButton(
                            text = stringResource(id = R.string.update_weight),
                            color = Color.Cyan,
                            onClick = {
                                if (weight < 30f || weight > 600f) {
                                    showError = true
                                    errorMessage = "Weight should be between 30 and 600!"
                                } else {
                                    onButtonClick()
                                }
                            }
                        )
                    }

                    if (state is WeightChangeState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    if (state is WeightChangeState.Error) {
                        Toast.makeText(
                            LocalContext.current,
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    if (state is WeightChangeState.Success) {
                        onArrowClick()
                        onStateChange(WeightChangeState.Empty)
                    }
                }
            }
        )
    }
}
