package com.tradiebot.cythero.presentation.user_info.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.user_info.UserInfoScreenState

@Composable
fun UserInfoContent(
    state: UserInfoScreenState.Success,
    contentPadding: PaddingValues,
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .fillMaxSize(),
    ) {
        val cardContentPadding = USER_INFO_CARD_CONTENT_PADDING

        UserInfoCard(
            title = stringResource(R.string.field_analytics),
            modifier = Modifier
                .height(200.dp)
        ) {
            Text(
                text = "Get User Data",
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 12.dp),
            )
        }

        UserInfoGeneralCard(
            state = state,
        )


        /*
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {

            //LineChartComponent()
        }
        
         */


        // PieChartComponent()


        Divider(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 12.dp)
        )

        UserInfoBasicFields(
            state = state,
        )
    }
}