package dam.inakki.listatareas.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dam.inakki.listatareas.config.AppRoundedCorner

@Composable
fun MenuTaskCard(
    showMenu: Boolean,
    onChangeMenu: () -> Unit,
    onSaveEdit: () -> Unit,
    onCancel: () -> Unit,
    onClickDelete: () -> Unit,
    content: @Composable () -> Unit,
    changeValueInputText: () -> Unit
) {
    var showDialog: Boolean by remember { mutableStateOf(false) }

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
            onSave = onSaveEdit,
            onCancel = onCancel,
            onChangeDialog = { showDialog = false },
            content = content,
            changeValueInputText = changeValueInputText
        )
    }
} // MenuTaskCard