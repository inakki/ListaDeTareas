package dam.inakki.listatareas.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dam.inakki.listatareas.config.AppFontSize
import dam.inakki.listatareas.config.AppPaddings
import dam.inakki.listatareas.config.AppRoundedCorner
import dam.inakki.listatareas.config.ButtonSquareSize
import dam.inakki.listatareas.data.TaskManager
import dam.inakki.listatareas.ui.components.DialogNewTask

@Composable
fun ButtonNewTask(
    tasks: TaskManager,
    onError: () -> Unit
) {
    var showDialog: Boolean by remember { mutableStateOf(false) }
    var dialogInputText: String by remember { mutableStateOf(value = "") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppPaddings.Small)
    ) {
        Button(
            modifier = Modifier
                .width(ButtonSquareSize.Large)
                .height(ButtonSquareSize.Large)
                .align(Alignment.BottomEnd)
                .size(56.dp),
            contentPadding = PaddingValues(horizontal = AppPaddings.None, vertical = AppPaddings.None), // es para ajustar el padding entre el borde y el texto
            shape = RoundedCornerShape(AppRoundedCorner.Large),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = { showDialog = true }
        ) {
            Text(
                text = "+",
                fontSize = AppFontSize.Large,

                )
        } // Button
    } // Box

    if(showDialog){
        DialogNewTask(
            onSave = {
                if(dialogInputText.isNotBlank()) {
                    tasks.createNewTask(name = dialogInputText)
                    dialogInputText = ""
                }
                else onError()
            },
            onCancel = { dialogInputText = "" },
            onChangeDialog = { showDialog = false },
            content = {
                OutlinedTextField(
                    value = dialogInputText,
                    onValueChange = { dialogInputText = it },
                    label = { Text(text = "Escribe la tarea:") },
                    maxLines = 3
                ) // OutlinedTextFied
            }
        ) // DialogNewTask
    } // if
} // ButtonNewTask