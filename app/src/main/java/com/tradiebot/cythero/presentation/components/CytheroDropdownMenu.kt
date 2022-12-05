package com.tradiebot.cythero.presentation.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * extension of [DropdownMenu] with ability to be used as a button, to add secondary title text and
 * a dropdown arrow which is animated
 *
 * @param title optional title located on top of [text]
 * @param text the text of the menu, using { var text by remember { mutableStateOf(text) } } it is
 * @param expanded if left undefined expansion of the dropdown menu will be handled automatically,
 * if defined the user has to handle it himself
 * possible to change the text with the text of the selected item or some other text
 * @param includeDropdownArrow if enabled an dropdown arrow will be put at the end, this arrow will
 * spin up and point upwards when the menu is expanded, on by default
 * @param onCustomAction action that will be activated when the row surrounding [text] is activated,
 * can't be null if [dropdownContent] is null
 * @param onDismissRequest called when the user requests to dismiss the menu, such as by tapping
 * outside the menu's bounds
 * @param dropdownContent content of the dropdown menu, if left null the menu will act as a button
 * can't be null if [onCustomAction] is null
 * @throws IllegalArgumentException if [onCustomAction] and [dropdownContent] are null at the same time
 * @see [DropdownMenu]
 * @see [DropdownMenuItem]
 * @see [CytheroDropdownMenuDelegate]
 *
 * TODO animate the arrow
 */
@Composable
fun CytheroDropdownMenu(
    title: String? = null,
    text: String,
    expanded: Boolean? = null,
    includeDropdownArrow: Boolean = true,
    onCustomAction: (() -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
    dropdownContent: (@Composable ColumnScope.() -> Unit)? = null,
) {
    if(onCustomAction == null && dropdownContent == null){
        throw IllegalArgumentException("onCustomAction and dropdownContent can't be null at the same time")
    }

    var expandedDelegate by remember { mutableStateOf(false) }

    CytheroDropdownMenuDelegate(
        title = title,
        text = text,
        expanded = expanded ?: expandedDelegate,
        includeDropdownArrow = includeDropdownArrow,
        onCustomAction = {
            if (onCustomAction != null) {
                onCustomAction()
            }

            if(expanded == null){
                expandedDelegate = !expandedDelegate
            }
        },
        onDismissRequest = {
            if(onDismissRequest != null){
                onDismissRequest()
            }

            if(expanded == null){
                expandedDelegate = false
            }
        },
        dropdownContent = dropdownContent
    )
}


@Composable
private fun CytheroDropdownMenuDelegate(
    title: String? = null,
    text: String,
    includeDropdownArrow: Boolean = true,
    expanded: Boolean,
    onCustomAction: (() -> Unit)? = null,
    onDismissRequest: () -> Unit,
    dropdownContent: (@Composable ColumnScope.() -> Unit)? = null,
){
    if(title != null) {
        Text(text = title)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (onCustomAction != null) {
                    onCustomAction()
                }
            }
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = 3.5.dp,
                    bottom = 8.dp,
                    start = 1.dp
                ),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            text = text,
        )

        if(includeDropdownArrow) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = ""
                )
            }
        }
    }

    if(dropdownContent != null) {
        DropdownMenu(
            expanded = expanded,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer),
            onDismissRequest = onDismissRequest
        ) {
            dropdownContent()
        }
    }

    Divider(
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}

@Preview(
    name = "Dropdown Menu" ,
    widthDp = 300,
    heightDp = 175,
)
@Composable
private fun CytheroDropdownMenuPreview1(){
    CytheroCard(title = "Example") {
        CytheroDropdownMenu(
            title = "Top Title",
            text = "Selected Item",
        ) {
            DropdownMenuItem(text = { Text(text = "Dropdown Item1") }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Dropdown Item2") }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Dropdown Item3") }, onClick = { /*TODO*/ })
        }
    }
}

@Preview(
    name = "Dropdown menu as custom action",
    widthDp = 300,
    heightDp = 175,
)
@Composable
private fun CytheroDropdownMenuPreview2(){
    var displayDialog by remember { mutableStateOf(false) }

    CytheroCard(title = "Example") {
        CytheroDropdownMenu(
            text = "Selected Item",
            expanded = false,
            includeDropdownArrow = false,
            onCustomAction = {
                displayDialog = !displayDialog
            },
            onDismissRequest = {
                displayDialog = false
            }
        )
    }


    if(displayDialog) {
        AlertDialog(
            text = {
                Text(text = "hello")
            },
            confirmButton = {
                TextButton(onClick = { displayDialog = false }) {
                    Text(text = stringResource(R.string.ok))
                }
            },
            onDismissRequest = { displayDialog = false },
        )
    }
}

