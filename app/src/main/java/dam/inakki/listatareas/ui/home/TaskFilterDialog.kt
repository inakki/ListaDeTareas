package dam.inakki.listatareas.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dam.inakki.listatareas.config.AppFontSize
import dam.inakki.listatareas.config.AppPaddings
import dam.inakki.listatareas.config.AppRoundedCorner
import dam.inakki.listatareas.ui.components.DialogFilter
import dam.inakki.listatareas.ui.components.IconFilter
import dam.inakki.listatareas.ui.theme.editIcon

@Composable
fun TaskFilterDialog(
    onApplyFilters: (showDone: Boolean, showNotDone: Boolean) -> Unit
) {
    var showFilterDialog: Boolean by remember { mutableStateOf(value = false) }
    var checkboxTasksDone: Boolean by remember { mutableStateOf(value = false) }
    var checkboxTasksNotDone: Boolean by remember { mutableStateOf(value = false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppPaddings.Small)
    ) {
        IconButton( // Boton filtros
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(
                    color = MaterialTheme.colorScheme.editIcon, // CAMBIAR NOMBRE DEL COLOR Y CREAR COLOR PARA ESTE
                    shape = RoundedCornerShape(AppRoundedCorner.Medium)
                ),
            onClick = { showFilterDialog = true }
        ) { IconFilter() } // IconButton
    } // Box

    if(showFilterDialog) {
        /*TaskFilterDialog(
            onApplyFilters = { showDone, showNotDone ->
                filter = if(showDone and showNotDone) "ALL"
                else if(showDone) "DONE"
                else if(showNotDone) "NOTDONE"
                else "NONE"
            } // onApplyFilters
        )*/

        DialogFilter(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppPaddings.Small)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = checkboxTasksDone,
                                onValueChange = { checkboxTasksDone = it}
                            )
                            .padding(AppPaddings.Small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checkboxTasksDone,
                            onCheckedChange = null
                        )
                        Text(
                            text = "Mostrar tareas hechas.",
                            Modifier.padding(start = AppPaddings.Small),
                            fontSize = AppFontSize.Small
                        )
                    } // Row

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = checkboxTasksNotDone,
                                onValueChange = { checkboxTasksNotDone = it }
                            )
                            .padding(AppPaddings.Small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checkboxTasksNotDone,
                            onCheckedChange = null
                        )
                        Text(
                            text = "Mostrar tareas sin terminar",
                            Modifier.padding(start = AppPaddings.Small),
                            fontSize = AppFontSize.Small
                        )
                    } // Row
                } // Column
            },

            onSave = {
                onApplyFilters(checkboxTasksDone, checkboxTasksNotDone)
                showFilterDialog = false
            },

            onClose = { action ->
                if(action == "DISMISS") {
                    checkboxTasksDone = true
                    checkboxTasksNotDone = true

                    onApplyFilters(checkboxTasksDone, checkboxTasksNotDone)
                }
                showFilterDialog = false
            } // onClose
        ) // DialogFilter
    } // if
} // TaskFilterDialog