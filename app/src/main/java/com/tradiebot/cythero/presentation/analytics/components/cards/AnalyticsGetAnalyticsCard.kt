package com.tradiebot.cythero.presentation.analytics.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsCard

@Composable
fun AnalyticsGetAnalyticsCard(
    state: AnalyticsScreenState.Success,
) {
    AnalyticsCard(
        title = stringResource(R.string.field_analytics),
        modifier = Modifier
            .padding(top = 24.dp)
            .height(200.dp)
    ) {
        test(
            title = "Select Report Type",
            content = {  },
            itemContent = {  }
        )
    }
}

@Composable
fun test(
    title: String,
    includeDropdownArrow: Boolean = true,
    itemContent: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
){
    var showMenu by remember { mutableStateOf(false) }


    Text(text = title)
    Text(
        modifier = Modifier
            .clickable {
                showMenu = !showMenu
            }
            .padding(top = 4.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        text = "User Report",
    )
    Divider(
        modifier = Modifier
            .padding(top = 4.dp),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
    DropdownMenu(
        expanded = showMenu, 
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(text = { Text(text = "hello") }, onClick = { /*TODO*/ })
    }

}