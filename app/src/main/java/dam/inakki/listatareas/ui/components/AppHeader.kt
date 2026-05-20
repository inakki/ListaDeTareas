package dam.inakki.listatareas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import dam.inakki.listatareas.config.AppColor
import dam.inakki.listatareas.config.AppElevation
import dam.inakki.listatareas.config.AppFontSize
import dam.inakki.listatareas.config.AppPaddings
import dam.inakki.listatareas.config.AppRoundedCorner

@Preview(showBackground = true)
@Composable
fun Header(){ // Encabezado
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.secondary,
        shadowElevation = AppElevation.Medium,
        shape = RoundedCornerShape(bottomStart = AppRoundedCorner.Large, bottomEnd = AppRoundedCorner.Large),
    ){
        Column(
            modifier = Modifier
                .padding(top = AppPaddings.Small, bottom = AppPaddings.Small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lista de tareas",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = AppFontSize.Large,
                fontWeight = FontWeight.Bold
            )
        } // Column
    } // Surface
} //Header