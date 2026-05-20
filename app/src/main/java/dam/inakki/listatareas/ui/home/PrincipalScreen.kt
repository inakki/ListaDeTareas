package dam.inakki.listatareas.ui.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dam.inakki.listatareas.config.AppElevation
import dam.inakki.listatareas.config.AppPaddings
import dam.inakki.listatareas.config.AppRoundedCorner
import dam.inakki.listatareas.data.TaskManager
import dam.inakki.listatareas.ui.components.IconFilter
import dam.inakki.listatareas.ui.theme.editIcon

@Preview(showBackground = true)
@Composable
fun PreviewPrincipalDisplay(){
    PrincipalScreen(modifier = Modifier, onShowError = { })
} // PreviewPrincipalDisplay

@Composable
fun PrincipalScreen(modifier: Modifier = Modifier, onShowError: () -> Unit){
    val context: Context = LocalContext.current

    val tasks: TaskManager =  remember { TaskManager(context) } // con remember esta pendiente de si cambia para actualizar la pantalla
    var filter: String by remember { mutableStateOf(value = "ALL") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            //modifier = Modifier.weight(1f),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = AppPaddings.Large,
                end = AppPaddings.Large,
                bottom = AppPaddings.Large,
                top = AppPaddings.Large
            )
        ) {
            items(tasks.taskList.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppPaddings.Small),
                    shape = RoundedCornerShape(AppRoundedCorner.Medium), // Esquinas redondas
                    elevation = CardDefaults.cardElevation(defaultElevation = AppElevation.ExtraSmall)
                ) {
                    when(filter) {
                        "ALL" -> {
                            CardOfTask(tasks = tasks, index = index, onError = { onShowError() }) // CardOfTask
                        }
                        "DONE" -> {
                            if(tasks.taskList[index].state) {
                                CardOfTask(tasks = tasks, index = index, onError = { onShowError() }) // CardOfTask
                            }
                        }
                        "NOTDONE" -> {
                            if(!tasks.taskList[index].state) {
                                CardOfTask(tasks = tasks, index = index, onError = { onShowError() }) // CardOfTask
                            }
                        }
                    } // when
                } // Card
            } // items
        } // LazyColumn

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(AppPaddings.ExtraLarge)
        ) {
            var showFilterDialog: Boolean by remember { mutableStateOf(value = false) }

            IconButton( // Boton filtros
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(AppPaddings.Small)
                    .background(
                        color = MaterialTheme.colorScheme.editIcon, // CAMBIAR NOMBRE DEL COLOR Y CREAR COLOR PARA ESTE
                        shape = RoundedCornerShape(AppRoundedCorner.Medium)
                    ),
                onClick = { showFilterDialog = true }
            ) { IconFilter() } // IconButton

            if(showFilterDialog) {
                TaskFilterDialog(
                    onDismiss = { showFilterDialog = false },
                    onApplyFilters = { showDone, showNotDone ->
                        filter = if(showDone and showNotDone) "ALL"
                        else if(showDone) "DONE"
                        else if(showNotDone) "NOTDONE"
                        else "NONE"
                    } // onApplyFilters
                )
            } // if

            ButtonNewTask(tasks, onError = { onShowError() })
        } // Column
    } // Box
} // PrincipalDisplay