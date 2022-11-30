package com.tradiebot.cythero.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import com.google.android.material.composethemeadapter3.createMdc3Theme

@Composable
fun CytheroTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val layoutDirection = LocalLayoutDirection.current

    val colors = if (!isSystemInDarkTheme()) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}

/*
@Composable
fun CytheroTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    val context = LocalContext.current
    val layoutDirection = LocalLayoutDirection.current

    val (colorScheme, typography) = createMdc3Theme(
        context = context,
        layoutDirection = layoutDirection,
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography!!,
        content = content
    )
}
 */

private val LightColors = lightColorScheme(
    primary = cythero_theme_light_primary,
    onPrimary = cythero_theme_light_onPrimary,
    primaryContainer = cythero_theme_light_primaryContainer,
    onPrimaryContainer = cythero_theme_light_onPrimaryContainer,
    secondary = cythero_theme_light_secondary,
    onSecondary = cythero_theme_light_onSecondary,
    secondaryContainer = cythero_theme_light_secondaryContainer,
    onSecondaryContainer = cythero_theme_light_onSecondaryContainer,
    tertiary = cythero_theme_light_tertiary,
    onTertiary = cythero_theme_light_onTertiary,
    tertiaryContainer = cythero_theme_light_tertiaryContainer,
    onTertiaryContainer = cythero_theme_light_onTertiaryContainer,
    error = cythero_theme_light_error,
    errorContainer = cythero_theme_light_errorContainer,
    onError = cythero_theme_light_onError,
    onErrorContainer = cythero_theme_light_onErrorContainer,
    background = cythero_theme_light_background,
    onBackground = cythero_theme_light_onBackground,
    surface = cythero_theme_light_surface,
    onSurface = cythero_theme_light_onSurface,
    surfaceVariant = cythero_theme_light_surfaceVariant,
    onSurfaceVariant = cythero_theme_light_onSurfaceVariant,
    outline = cythero_theme_light_outline,
    inverseOnSurface = cythero_theme_light_inverseOnSurface,
    inverseSurface = cythero_theme_light_inverseSurface,
    inversePrimary = cythero_theme_light_inversePrimary,
    surfaceTint = cythero_theme_light_surfaceTint,
    outlineVariant = cythero_theme_light_outlineVariant,
    scrim = cythero_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = cythero_theme_dark_primary,
    onPrimary = cythero_theme_dark_onPrimary,
    primaryContainer = cythero_theme_dark_primaryContainer,
    onPrimaryContainer = cythero_theme_dark_onPrimaryContainer,
    secondary = cythero_theme_dark_secondary,
    onSecondary = cythero_theme_dark_onSecondary,
    secondaryContainer = cythero_theme_dark_secondaryContainer,
    onSecondaryContainer = cythero_theme_dark_onSecondaryContainer,
    tertiary = cythero_theme_dark_tertiary,
    onTertiary = cythero_theme_dark_onTertiary,
    tertiaryContainer = cythero_theme_dark_tertiaryContainer,
    onTertiaryContainer = cythero_theme_dark_onTertiaryContainer,
    error = cythero_theme_dark_error,
    errorContainer = cythero_theme_dark_errorContainer,
    onError = cythero_theme_dark_onError,
    onErrorContainer = cythero_theme_dark_onErrorContainer,
    background = cythero_theme_dark_background,
    onBackground = cythero_theme_dark_onBackground,
    surface = cythero_theme_dark_surface,
    onSurface = cythero_theme_dark_onSurface,
    surfaceVariant = cythero_theme_dark_surfaceVariant,
    onSurfaceVariant = cythero_theme_dark_onSurfaceVariant,
    outline = cythero_theme_dark_outline,
    inverseOnSurface = cythero_theme_dark_inverseOnSurface,
    inverseSurface = cythero_theme_dark_inverseSurface,
    inversePrimary = cythero_theme_dark_inversePrimary,
    surfaceTint = cythero_theme_dark_surfaceTint,
    outlineVariant = cythero_theme_dark_outlineVariant,
    scrim = cythero_theme_dark_scrim,
)
