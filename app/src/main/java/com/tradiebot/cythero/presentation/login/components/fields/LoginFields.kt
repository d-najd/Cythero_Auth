package com.tradiebot.cythero.presentation.login.components.fields

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.presentation.login.LoginState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFields(
    state: LoginState,
) {
    val defaultFieldModifier = Modifier.padding(top = 12.dp, bottom = 12.dp)

    var mail by rememberSaveable { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = mail,
        onValueChange = { mail = it },
        modifier = defaultFieldModifier,
        label = { Text(stringResource(R.string.email)) },
        // placeholder = { Text("example@gmail.com") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        singleLine = true,
    )

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        modifier = defaultFieldModifier,
        label = { Text(stringResource(R.string.password)) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
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

    Button(onClick = {
        mail = "testings"
    }) {
        Text(stringResource(R.string.action_confirm))
    }
}