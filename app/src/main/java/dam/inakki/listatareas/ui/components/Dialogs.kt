@file:OptIn(ExperimentalMaterial3Api::class)

package dam.inakki.listatareas.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.LaunchedEffect

@Composable
fun BaseDialog (
    title: String,
    textDismissButton: String = "Cancelar",
    onSave: () -> Unit,
    onClose: (String) -> Unit,
    content: @Composable () -> Unit,
    changeValueInputText: () -> Unit = { }
) {
    LaunchedEffect(Unit) { // hace que esto solo se ejecute 1 vez
        changeValueInputText()
    }

    AlertDialog(
        onDismissRequest = { onClose("") },

        title = { Text(text = title) },

        text = { content() },

        confirmButton = {
            Button(onClick = { onSave() }) { Text(text = "Guardar") }
        }, // confirmButton

        dismissButton = {
            Button(onClick = { onClose("DISMISS") }) { Text(text = textDismissButton) }
        } // dismissButton
    ) // AlertDialog
} // DialogNewTask

@Composable
fun BaseDialog ( // tengo que hacer Overloading para que el BaseDialog funcione tambien para los filtros
    title: String,
    textDismissButton: String = "Cancelar",
    onSave: () -> Unit,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
    changeValueInputText: () -> Unit = {}
) {
    BaseDialog(
        title = title,
        textDismissButton = textDismissButton,
        onSave = onSave,
        onClose = { _ -> onClose() }, // el _ significa que ignora el parametro String
        content = content,
        changeValueInputText = changeValueInputText
    )
} // BaseDialog

@Composable
fun DialogEdit(
    onSave: () -> Unit,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
    changeValueInputText: () -> Unit
) {
    BaseDialog(
        onSave = onSave,
        onClose = onClose,
        title = "Modificar Tarea",
        content = content,
        changeValueInputText = changeValueInputText
    )
} // DialogEdit

@Composable
fun DialogNewTask(
    onSave: () -> Unit,
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    BaseDialog(
        onSave = onSave,
        onClose = onClose,
        title = "Nueva tarea",
        content = content
    )
} // DialogNewTask

@Composable
fun DialogFilter(
    onSave: () -> Unit,
    onClose: (String) -> Unit,
    content: @Composable () -> Unit
) {
    BaseDialog(
        onSave = onSave,
        onClose = onClose,
        title = "Filtros",
        content = content,
        textDismissButton = "Mostrar todo"
    )
} // DialogFilter