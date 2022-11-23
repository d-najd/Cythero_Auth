package com.tradiebot.cythero.presentation.login.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUnitApi::class)
@Composable
        /**
         * should be possible to get rid of the onValueChange since text and it have to be the same
         */
fun LoginTextField(
    modifier: Modifier = Modifier,
    title: String?,
    text: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    content: @Composable ColumnScope.() -> Unit = {},
) {

    Column(
        modifier = modifier,
    ) {
        if(title != null) {
            Text(
                modifier = Modifier.alpha(.8F),
                text = title,
                fontSize = TextUnit(14F, TextUnitType.Sp),
            )
        }
        OutlinedTextField(
            label = { Text("test") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            value = text,
            onValueChange = onValueChange,
            singleLine = true,

        )
    }
}
