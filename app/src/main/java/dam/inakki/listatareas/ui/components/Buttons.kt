package dam.inakki.listatareas.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dam.inakki.listatareas.config.AppPaddings
import dam.inakki.listatareas.config.AppRoundedCorner
import dam.inakki.listatareas.config.ButtonSquareSize

@Composable
private fun BaseCardButton(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    text: String
) {
    Button(
        modifier = Modifier
            .width(ButtonSquareSize.Small)
            .height(ButtonSquareSize.Small)
            .padding(end = AppPaddings.ExtraSmall),
        contentPadding = PaddingValues(horizontal = AppPaddings.None, vertical = AppPaddings.None),
        shape = RoundedCornerShape(AppRoundedCorner.Large),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
            ),
        onClick = onClick
    ) {
        Text(text)
    } // Button
} // ButtonCardAction