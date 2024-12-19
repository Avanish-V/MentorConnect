package com.byteapps.mentorconnect.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

data class AppColorScheme(
    val background:Color,
    val onBackground:Color,
    val primary:Color,
    val onPrimary:Color,
    val secondary:Color,
    val onSecondary:Color,
    val tertiary:Color
)

data class AppTypography(
    val titleLarge:TextStyle,
    val titleMedium:TextStyle,
    val titleSmall:TextStyle,
)

data class AppShape(
    val container:Shape,
    val button:Shape
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        tertiary = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleSmall = TextStyle.Default
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        button = RectangleShape
    )
}

private val darkColorScheme = AppColorScheme(
    background = Color.Black,
    onBackground = Color.Gray,
    primary = Color.White,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Gray,
    tertiary = Color.Black
)

private val lightColorScheme = AppColorScheme(
    background = Background,
    onBackground = Color.White,
    primary = Primary,
    onPrimary = Color.Gray,
    secondary = Secondary,
    onSecondary = Color.Gray,
    tertiary = Tertiary
)

private val typography = AppTypography(
    titleLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleSmall = TextStyle(
        fontSize = 10.sp
    )
)

private val shape = AppShape(
    container = RoundedCornerShape(12.dp),
    button = RoundedCornerShape(12.dp)
)

@Composable
fun AppTheme(
    isDarkTheme:Boolean = isSystemInDarkTheme(),
    content:@Composable ()->Unit
) {

    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        content = content
    )
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars
        }
    }
}
object AppTheme{
    val colorScheme:AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography:AppTypography
        @Composable get() = LocalAppTypography.current

    val shape:AppShape
        @Composable get() = LocalAppShape.current
}
