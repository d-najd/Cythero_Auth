package com.tradiebot.cythero.presentation.analytics.components.subcards

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        Text(
            text = "Get User Data",
            modifier = Modifier
                .padding(top = 12.dp),
        )

        test(
            title = "hello",
            content = {},
            itemContent = {}
        )
    }
}

@Composable
fun test(
    title: String,
    includeDropdownArrow: Boolean = false,
    itemContent: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
){
    var showMenu by remember { mutableStateOf(false) }

    Text(text = title)
    TextButton(
        onClick = { 
            showMenu = !showMenu 
        }
    ) {
        Text(text = "hello")
    }
    DropdownMenu(
        expanded = showMenu, 
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(text = { Text(text = "hello") }, onClick = { /*TODO*/ })
        
    }
    
}