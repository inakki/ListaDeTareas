package dam.inakki.listatareas.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BlueBright,
    onPrimary = Color.Black,

    secondary = DarkCard, // -
    onSecondary = TextWhite,

    tertiary = SuccessDark,
    onTertiary = Color.Black,

    background = DarkBackground,
    onBackground = TextWhite,

    surface = DarkCard,
    onSurface = TextWhite,

    error = DeleteDark,
    onError = TextWhite
)

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,      // Añadir
    onPrimary = Color.White,    // text añadir

    secondary = BlueHeader,     // Header
    onSecondary = Color.White,

    tertiary = CheckSuccess,    // Completar
    onTertiary = Color.White,   // text completar

    background = AppBackground, // background
    onBackground = TextMain,

    surface = CardWhite,
    onSurface = TextMain,

    error = DeleteIconColor,    // Delete
    onError = Color.White       // text delete
)

val ColorScheme.editIcon: Color
    @Composable
    get() = if (isSystemInDarkTheme()) EditIconDark else EditIconLight

val ColorScheme.onEditIcon: Color
    @Composable
    get() = if (isSystemInDarkTheme()) TextWhite else Color.White

val ColorScheme.TextTaskDone: Color
    @Composable
    get() = Color.Gray

val ColorScheme.TextTaskNotDone: Color
    @Composable
    get() = TextWhite

@Composable
fun ListaTareasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}