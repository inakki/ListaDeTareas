package dam.inakki.listatareas.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dam.inakki.listatareas.config.AppRoundedCorner
import dam.inakki.listatareas.data.TaskManager
import dam.inakki.listatareas.models.Task
import kotlinx.coroutines.launch

@Composable
fun MenuTaskCard(
    showMenu: Boolean,
    task: Task,
    tasks: TaskManager,
    onChangeMenu: () -> Unit,
    onClickDelete: () -> Unit,
    onError: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var showDialog: Boolean by remember { mutableStateOf(false) }
    var editInputText: String by remember { mutableStateOf("") }

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { onChangeMenu() },
        shape = RoundedCornerShape(AppRoundedCorner.Medium),
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        DropdownMenuItem(
            text = { Text(text = "Editar", color = MaterialTheme.colorScheme.onSecondary) },
            leadingIcon = { IconEdit() },
            onClick = {
                onChangeMenu()
                showDialog = true
            }
        ) // DropdownMenuItem

        DropdownMenuItem(
            text = { Text(text = "Eliminar", color = MaterialTheme.colorScheme.error) },
            leadingIcon = { IconDelete() },
            onClick = {
                onChangeMenu()
                onClickDelete()
            }
        )
    } // DropdownMenu

    if(showDialog) {
        DialogEdit(
            onSave = {
                if(editInputText.isNotBlank()){
                    coroutineScope.launch {
                        tasks.updateTask(task.copy(name = editInputText))
                        editInputText = ""
                    }
                }
                else onError()
                showDialog = false
            },
            onClose = {
                showDialog = false
                editInputText = ""
            },
            content = {
                OutlinedTextField(
                    value = editInputText,
                    onValueChange = { editInputText = it },
                    label = { Text(text = "Escribe el nuevo nombre:") },
                    maxLines = 3
                ) // OutlinedTextField
            }, // content
            changeValueInputText = { editInputText = task.name }
        )
    }
} // MenuTaskCard