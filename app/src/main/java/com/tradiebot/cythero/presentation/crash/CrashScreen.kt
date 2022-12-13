package com.tradiebot.cythero.presentation.crash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import kotlinx.coroutines.launch

private val PADDING_SMALL = 4.dp
private val PADDING_MEDIUM = 8.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrashScreen(
    exception: Throwable?,
    onRestartClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        bottomBar = {
            val strokeWidth = Dp.Hairline
            val borderColor = MaterialTheme.colorScheme.outline
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .drawBehind {
                        drawLine(
                            borderColor,
                            Offset(0f, 0f),
                            Offset(size.width, 0f),
                            strokeWidth.value,
                        )
                    }
                    .padding(WindowInsets.navigationBars.asPaddingValues())
                    .padding(horizontal = PADDING_MEDIUM, vertical = PADDING_SMALL),
                verticalArrangement = Arrangement.spacedBy(PADDING_SMALL),
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            // CrashLogUtil(context).dumpLogs()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = stringResource(R.string.field_dump_crash_logs))
                }
                OutlinedButton(
                    onClick = onRestartClick,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = stringResource(R.string.crash_screen_restart_application))
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(top = 56.dp)
                .padding(horizontal = PADDING_MEDIUM),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Outlined.BugReport,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp),
            )
            Text(
                text = stringResource(R.string.crash),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(R.string.crash_screen_description, stringResource(R.string.app_name)),
                modifier = Modifier
                    .padding(vertical = PADDING_SMALL),
            )
            Box(
                modifier = Modifier
                    .padding(vertical = PADDING_SMALL)
                    .clip(MaterialTheme.shapes.small)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            ) {
                Text(
                    text = exception.toString(),
                    modifier = Modifier
                        .padding(all = PADDING_SMALL),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
