package com.tradiebot.cythero.presentation.register.components

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
import com.tradiebot.cythero.app.ui.register.RegisterScreenState
import com.tradiebot.cythero.domain.user.model.UserRegister

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.RegisterFields(
    @Suppress("UNUSED_PARAMETER") state: RegisterScreenState.Success,
    onClickUserRegister: (UserRegister) -> Unit,
    onClickLogin: () -> Unit,
) {
    val defaultContentModifier = Modifier
        .padding(start = 16.dp, top = 6.dp, end = 16.dp, bottom = 6.dp)
        .align(CenterHorizontally)
        .fillMaxWidth()

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = firstName,
        onValueChange = { firstName = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.first_name)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        placeholder = { Text(stringResource(R.string.placeholder_first_name)) },
        singleLine = true,
    )

    OutlinedTextField(
        value = lastName,
        onValueChange = { lastName = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.last_name)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        placeholder = { Text(stringResource(R.string.placeholder_last_name)) },
        singleLine = true,
    )

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.email)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        placeholder = { Text(stringResource(R.string.placeholder_email)) },
        singleLine = true,
    )

    OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.username)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        placeholder = { Text(stringResource(R.string.placeholder_username)) },
        singleLine = true,
    )

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.password)) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
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

    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
        modifier = defaultContentModifier,
        label = { Text(stringResource(R.string.confirm_password)) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        placeholder = { Text(stringResource(R.string.placeholder_confirm_password)) },
        singleLine = true,
    )

    Button(
        modifier = defaultContentModifier
            .padding(top = 20.dp),
        onClick = {
            if(password != confirmPassword) {
                firstName = "Passwords don't match"
            } else if(
                firstName.isBlank() ||
                lastName.isBlank() ||
                email.isBlank() ||
                username.isBlank() ||
                password.isBlank()) {
                firstName = "Empty Field/s"
            } else {
                onClickUserRegister(
                    //UserRegister.testingInstance()
                    UserRegister(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        username = username,
                        password = password,
                    )
                )
            }
        })
    {
        Text(
            text = stringResource(R.string.action_register_user),
            color = Color.White
        )
    }

    Column(
        horizontalAlignment = CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                .fillMaxWidth()
                .width(300.dp),
        ) {
            Text(
                modifier = Modifier.padding(top = 18.dp),
                text = stringResource(R.string.info_do_have_account),
                fontWeight = FontWeight.Bold,
            )
            TextButton(
                onClick = { onClickLogin() },
            ) {
                Text(
                    text = stringResource(R.string.action_log_in),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}