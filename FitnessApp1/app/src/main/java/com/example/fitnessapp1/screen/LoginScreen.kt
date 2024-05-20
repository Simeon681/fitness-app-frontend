package com.example.fitnessapp1.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.DividerText
import com.example.fitnessapp1.components.EmailTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.NormalButton
import com.example.fitnessapp1.components.NormalClickableText
import com.example.fitnessapp1.components.PasswordTextField
import com.example.fitnessapp1.components.checkEmail
import com.example.fitnessapp1.components.checkPassword
import com.example.fitnessapp1.state.LoginState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginState,
    onStateChange: (LoginState) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    onArrowClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
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
                        Text(text = stringResource(id = R.string.login))
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
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Spacer(modifier = Modifier.height(70.dp))

                        HeadingText(
                            value = stringResource(id = R.string.login),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(70.dp))

                        EmailTextField(
                            value = email,
                            onChange = onEmailChange
                        )

                        PasswordTextField(
                            value = password,
                            onChange = onPasswordChange,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        NormalButton(
                            value = stringResource(id = R.string.login),
                            onClick = {
                                if (!checkEmail(email)) {
                                    errorMessage = "Email is not valid!"
                                    showError = true
                                } else if (!checkPassword(password)) {
                                    errorMessage = "Password must be 8-16 characters long " +
                                            "and contain at least one digit!"
                                    showError = true
                                } else {
                                    onButtonClick()
                                }
                            }
                        )

                        DividerText(value = stringResource(id = R.string.or))

                        NormalClickableText(
                            initialText = stringResource(id = R.string.go_to_register),
                            mainText = stringResource(id = R.string.register),
                            onTextSelected = {
                                onRegisterClick()
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    if (state is LoginState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    if (state is LoginState.Error) {
                            Toast.makeText(
                                LocalContext.current,
                                state.message,
                                Toast.LENGTH_SHORT
                            ).show()
                    }

                    if (state is LoginState.Success) {
                        onLoginClick()
                        onStateChange(LoginState.Empty)
                    }
                }
            }
        )
    }
}
