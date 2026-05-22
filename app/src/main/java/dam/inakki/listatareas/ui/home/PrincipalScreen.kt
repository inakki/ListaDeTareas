package dam.inakki.listatareas.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dam.inakki.listatareas.config.AppElevation
import dam.inakki.listatareas.config.AppPaddings
import dam.inakki.listatareas.config.AppRoundedCorner
import dam.inakki.listatareas.data.TaskManager
import dam.inakki.listatareas.data.local.TaskDao

@Composable
fun PrincipalScreen(taskDao: TaskDao, modifier: Modifier = Modifier, onShowError: () -> Unit){
    val tasksManager: TaskManager = remember { TaskManager(taskDao) } // con remember esta pendiente de si cambia para actualizar la pantalla

    val taskList by tasksManager.tasksFlow.collectAsState(initial = emptyList())

    var filter: String by remember { mutableStateOf(value = "ALL") }

    val taskListFiltered = remember (taskList, filter) {
        when(filter) {
            "DONE" -> taskList.filter{ it.state } // si es true...
            "NOTDONE" -> taskList.filter{ !it.state } // si es false ...
            else -> taskList // ALL
        } // when
    }

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
            items(taskListFiltered) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppPaddings.Small),
                    shape = RoundedCornerShape(AppRoundedCorner.Medium), // Esquinas redondas
                    elevation = CardDefaults.cardElevation(defaultElevation = AppElevation.ExtraSmall)
                ) {
                    CardOfTask(tasks = tasksManager, task = task, onError = { onShowError() })
                } // Card
            } // items
            item{ // esta card es para hacer espacio en la parte de abajo de la lista y que se pueda pulsar el boton de opciones
                Card(modifier = Modifier.padding(AppPaddings.SuperLarge)) { }
            }
        } // LazyColumn

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(AppPaddings.ExtraLarge)
        ) {
            TaskFilterDialog(
                onApplyFilters = { showDone, showNotDone ->
                    filter = if(showDone and showNotDone) "ALL"
                    else if(showDone) "DONE"
                    else if(showNotDone) "NOTDONE"
                    else "NONE"
                } // onApplyFilters
            )

            ButtonNewTask(tasksManager, onError = { onShowError() })
        } // Column
    } // Box
} // PrincipalDisplay