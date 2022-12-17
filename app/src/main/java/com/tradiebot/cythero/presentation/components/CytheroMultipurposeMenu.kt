package com.tradiebot.cythero.presentation.components

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
import com.tradiebot.cythero.R

/**
 * Multipurpose button which has title on the top, text which is clickable, an arrow at the far right
 * and a divider on the bottom
 *
 * @param modifier modifier for the entire composable
 * @param title optional title located on top of [text]
 * @param text the text of the menu
 * @param includeDropdownArrow if enabled an dropdown arrow will be put at the end, this arrow will
 * point downwards, off by default
 * @param onClick gets triggered when [text] is clicked
 * @see [CytheroMultipurposeMenuPreview]
 */
@Composable
fun CytheroMultipurposeMenu(
    modifier: Modifier = Modifier,
    title: String? = null,
    text: String,
    includeDropdownArrow: Boolean = false,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        if (title != null) {
            Text(
                modifier = Modifier
                    .padding(start = MULTIPURPOSE_MENU_TEXT_START_PADDING),
                text = title,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        top = (3.5).dp,
                        bottom = 8.dp,
                        start = MULTIPURPOSE_MENU_TEXT_START_PADDING + 1.dp
                    ),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = text,
            )

            if (includeDropdownArrow) {
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

        Divider(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

/** start padding for the text so the text isn't right at the beginning */
private val MULTIPURPOSE_MENU_TEXT_START_PADDING = 2.5.dp


@Preview(
    widthDp = 300,
    heightDp = 175,
)
@Composable
private fun CytheroMultipurposeMenuPreview(){
    var displayDialog by remember { mutableStateOf(false) }

    CytheroCard(title = "Example") {
        CytheroMultipurposeMenu(
            text = "Show Dialog",
            includeDropdownArrow = false,
            onClick = {
                displayDialog = !displayDialog
            },
        )
    }

    if(displayDialog) {
        AlertDialog(
            text = {
                Text(text = "hello")
            },
            confirmButton = {
                TextButton(onClick = { displayDialog = false }) {
                    Text(text = stringResource(R.string.action_confirm))
                }
            },
            onDismissRequest = { displayDialog = false },
        )
    }
}

