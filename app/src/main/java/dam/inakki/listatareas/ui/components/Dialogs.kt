@file:OptIn(ExperimentalMaterial3Api::class)

package dam.inakki.listatareas.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog

@Composable
fun BaseDialog (
    title: String,
    textDismissButton: String = "Cancelar",
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onChangeShowDialog: () -> Unit,
    content: @Composable () -> Unit,
    changeValueInputText: () -> Unit = { }
) {
    changeValueInputText()

    AlertDialog(
        onDismissRequest = { onChangeShowDialog() },

        title = { Text(text = title) },

        text = { content() },

        confirmButton = {
            Button(
                onClick = {
                    onSave()
                    onChangeShowDialog()
                }
            ) { Text(text = "Guardar") }
        }, // confirmButton

        dismissButton = {
            Button(
                onClick = {
                    onCancel()
                    onChangeShowDialog()
                } // onClick
            ) { Text(text = textDismissButton) }
        } // dismissButton
    ) // AlertDialog
} // DialogNewTask

@Composable
fun DialogEdit(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onChangeDialog: () -> Unit,
    content: @Composable () -> Unit,
    changeValueInputText: () -> Unit
) {
    BaseDialog(
        onSave = onSave,
        onCancel = onCancel,
        onChangeShowDialog = onChangeDialog,
        title = "Modificar Tarea",
        content = content,
        changeValueInputText = changeValueInputText
    )
} // DialogEdit

@Composable
fun DialogNewTask(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onChangeDialog: () -> Unit,
    content: @Composable () -> Unit
) {
    BaseDialog(
        onSave = onSave,
        onCancel = onCancel,
        onChangeShowDialog = onChangeDialog,
        title = "Nueva tarea",
        content = content
    )
} // DialogNewTask

@Composable
fun DialogFilter(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onChangeDialog: () -> Unit,
    content: @Composable () -> Unit
) {
    BaseDialog(
        onSave = onSave,
        onCancel = onCancel,
        onChangeShowDialog = onChangeDialog,
        title = "Filtros",
        content = content,
        textDismissButton = "Mostrar todo"
    )
} // DialogFilter