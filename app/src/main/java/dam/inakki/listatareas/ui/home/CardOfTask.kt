package dam.inakki.listatareas.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextDecoration
import dam.inakki.listatareas.config.AppFontSize
import dam.inakki.listatareas.config.AppPaddings
import dam.inakki.listatareas.data.TaskManager
import dam.inakki.listatareas.ui.components.IconDone
import dam.inakki.listatareas.ui.components.IconOptions
import dam.inakki.listatareas.ui.components.MenuTaskCard
import dam.inakki.listatareas.ui.theme.TextTaskDone
import dam.inakki.listatareas.ui.theme.TextTaskNotDone

@Composable
fun CardOfTask(
    tasks: TaskManager,
    index: Int,
    onError: () -> Unit // meterle el showError

) {
    var showDownMenu: Boolean by remember { mutableStateOf(false) }
    var editInputText: String by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { tasks.taskChangeState(position = index) }){
            IconDone(isDone = tasks.taskList[index].state)
        }

        Text(
            text = tasks.taskList[index].name,
            fontSize = AppFontSize.Medium,
            modifier = Modifier
                .padding(AppPaddings.Large)
                .weight(1f),
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = if(tasks.taskList[index].state) TextDecoration.LineThrough else TextDecoration.None,
                color = if(tasks.taskList[index].state) MaterialTheme.colorScheme.TextTaskDone else MaterialTheme.colorScheme.TextTaskNotDone
            )
        ) // Text

        Box(
            modifier = Modifier.wrapContentSize(Alignment.TopEnd)
        ) {
            IconButton(onClick = { showDownMenu = true }) {
                IconOptions()
            } // IconButton

            MenuTaskCard(
                onChangeMenu = { showDownMenu = false },
                onSaveEdit = {
                    if(editInputText.isNotBlank()){
                        tasks.renameTask(position = index, newName = editInputText)
                        editInputText = ""
                    }
                    else onError()
                },
                onCancel = { editInputText = "" },
                showMenu = showDownMenu,
                onClickDelete = { tasks.removeTask(position = index) },
                changeValueInputText = { editInputText = tasks.taskList[index].name },
                content = {
                    OutlinedTextField(
                        value = editInputText,
                        onValueChange = { editInputText = it },
                        label = { Text(text = "Escribe el nuevo nombre:") },
                        maxLines = 3
                    ) // OutlinedTextField
                } // content
            ) // MenuTaskCard
        } // Box
    } // Row
} // CardOfTask