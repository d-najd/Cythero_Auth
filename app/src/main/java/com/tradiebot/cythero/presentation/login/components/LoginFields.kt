package com.tradiebot.cythero.presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.login.LoginScreenState
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.presentation.components.dialogs.CytheroTextFieldDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.LoginFields(
    @Suppress("unused")
    state: LoginScreenState.Success,
    onClickUserLogin: (UserLogin) -> Unit,
    onClickRegister: () -> Unit,

    onMissingFields: () -> Unit,
) {
    val defaultContentModifier = Modifier
        .padding(
            start = 16.dp,
            top = 6.dp,
            end = 16.dp,
            bottom = 6.dp
        )
        .align(CenterHorizontally)
        .fillMaxWidth()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        colors = CytheroTextFieldDefaults.outlinedTextFieldBrightColors(),
        value = email,
        onValueChange = { email = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.field_email)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        placeholder = { Text(stringResource(R.string.placeholder_email)) },
        singleLine = true,
    )

    OutlinedTextField(
        colors = CytheroTextFieldDefaults.outlinedTextFieldBrightColors(),
        value = password,
        onValueChange = { password = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.field_password)) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_password))
        },
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (passwordVisible) stringResource(R.string.action_hide_password)
                else stringResource(R.string.action_show_password)

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        },
        singleLine = true,
    )


    Button(
        modifier = defaultContentModifier
            .padding(top = 20.dp),
        onClick = {
            // email = UserLogin.testingInstance().email.toString()
            // password = UserLogin.testingInstance().password
            if (email.isBlank() || password.isBlank()) {
                onMissingFields()
            } else {
                onClickUserLogin(
                    UserLogin(
                        email = email,
                        password = password,
                    )
                )
            }
        }) {
        Text(
            text = stringResource(R.string.action_sign_in),
            color = Color.White
        )
    }

    Column(
        horizontalAlignment = CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 8.dp
                )
                .fillMaxWidth()
                .width(300.dp),
        ) {
            Text(
                modifier = Modifier.padding(top = 18.dp),
                text = stringResource(R.string.info_do_not_have_account),
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(.8f),
            )
            TextButton(
                onClick = { onClickRegister() },
            ) {
                Text(
                    text = stringResource(R.string.action_sign_up),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}