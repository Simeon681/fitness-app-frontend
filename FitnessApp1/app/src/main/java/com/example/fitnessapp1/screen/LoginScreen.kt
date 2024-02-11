package com.example.fitnessapp1.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.DividerText
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.NormalButton
import com.example.fitnessapp1.components.NormalClickableText
import com.example.fitnessapp1.components.NormalText
import com.example.fitnessapp1.components.NormalTextField
import com.example.fitnessapp1.components.PasswordTextField
import com.example.fitnessapp1.components.UnderlinedClickableText
import com.example.fitnessapp1.view_model.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavHostController?,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val username by loginViewModel.username.collectAsState()
    val password by loginViewModel.password.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalText(value = stringResource(id = R.string.login))
            HeadingText(value = stringResource(id = R.string.welcome))

            Spacer(modifier = Modifier.height(20.dp))

            NormalTextField(
                labelValue = stringResource(id = R.string.username),
                onChange = {
                    loginViewModel.saveUsername(it)
                },
                keyboardType = KeyboardType.Text,
                painterResource = Icons.Default.Person
            )

            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                painterResource = Icons.Default.Lock,
                onChange = {
                    loginViewModel.savePassword(it)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            UnderlinedClickableText(
                mainText = stringResource(id = R.string.forgot_password),
                onTextSelected = {
                    navController?.navigate("forgot_password")
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            NormalButton(
                value = stringResource(id = R.string.login),
                onClick = {
                    loginViewModel.login(username, password)
                    navController?.navigate("main")
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerText(value = stringResource(id = R.string.or))

            NormalClickableText(
                initialText = stringResource(id = R.string.go_to_signup),
                mainText = stringResource(id = R.string.register),
                onTextSelected = {
                    navController?.navigate("register")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null)
}
