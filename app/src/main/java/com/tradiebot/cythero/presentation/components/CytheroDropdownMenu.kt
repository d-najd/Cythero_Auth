package com.tradiebot.cythero.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * extension of [DropdownMenu] with the style of [CytheroMultipurposeMenu]
 *
 * @param title optional title located on top of [text]
 * @param text the text of the dropdown menu
 * @param expanded whether the menu is expanded or not
 * possible to change the text with the text of the selected item or some other text
 * @param includeDropdownArrow if enabled an dropdown arrow will be put at the end, this arrow will
 * spin up and point upwards when the menu is expanded, on by default
 * @param onDismissRequest called when the user requests to dismiss the menu, such as by tapping
 * outside the menu's bounds
 * @param offset [DpOffset] to be added to the position of the menu
 * @param onClick gets triggered when [text] is clicked
 * @param dropdownContent content of the dropdown menu,
 * @see [CytheroDropdownMenu]
 * @see [DropdownMenu]
 * @see [Cythero]
 *
 * TODO animate the arrow
 */
@Composable
fun CytheroDropdownMenu(
    title: String? = null,
    text: String,
    expanded: Boolean,
    includeDropdownArrow: Boolean = true,
    onDismissRequest: () -> Unit,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    onClick: () -> Unit,
    dropdownContent: @Composable ColumnScope.() -> Unit,
) {

    CytheroMultipurposeMenu(
        text = text,
        title = title,
        includeDropdownArrow = includeDropdownArrow,
        onClick = onClick,
    )

    DropdownMenu(
        expanded = expanded,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
        onDismissRequest = onDismissRequest,
        offset = offset,
    ) {
        dropdownContent()
    }
}

@Preview(
    widthDp = 300,
    heightDp = 175,
)
@Composable
private fun Cythero(){
    var dismissMenu by remember { mutableStateOf(false) }

    CytheroCard(title = "Example") {
        CytheroDropdownMenu(
            onClick = { dismissMenu = !dismissMenu },
            expanded = dismissMenu,
            title = "Top Title",
            text = "Selected Item",
            onDismissRequest = {
                dismissMenu = false
            },
        ) {
            DropdownMenuItem(text = { Text(text = "Dropdown Item 1") }, onClick = { dismissMenu = false })
            DropdownMenuItem(text = { Text(text = "Dropdown Item 2") }, onClick = { dismissMenu = false })
            DropdownMenuItem(text = { Text(text = "Dropdown Item 3") }, onClick = { dismissMenu = false })
        }
    }
}
