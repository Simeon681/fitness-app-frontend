package com.example.fitnessapp1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.NormalButton
import com.example.fitnessapp1.state.LoginState

@Composable
fun WelcomeScreen(
    state: LoginState,
    onStateChange: (LoginState) -> Unit,
    onAutoLogin: () -> Unit,
    goToMain: () -> Unit,
    onRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    LaunchedEffect(key1 = Unit) {
        onAutoLogin()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.padding(16.dp))

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, true),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalButton(value = stringResource(id = R.string.sign_up)) {
                        onRegisterClick()
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    Button(
                        onClick = { onLoginClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(48.dp),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        border = ButtonDefaults.outlinedButtonBorder
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.login),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.padding(16.dp))
                }
            }

            if (state is LoginState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }

            if (state is LoginState.Success) {
                goToMain()
                onStateChange(LoginState.Empty)
            }
        }
    }
}
