package dam.inakki.listatareas.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import dam.inakki.listatareas.ui.theme.editIcon

@Composable
fun BaseIcon(
    image: ImageVector,
    description: String,
    color: Color
) {
    Icon(
        imageVector = image,
        contentDescription = description,
        tint = color
    )
} // BaseIcon

@Preview(showBackground = true)
@Composable
fun IconOptions() {
    BaseIcon(
        image = Icons.Default.MoreVert,
        description = "Opciones",
        color = MaterialTheme.colorScheme.onSecondary
    )
} // IconOptions

@Preview(showBackground = true)
@Composable
fun IconEdit() {
    BaseIcon(
        image = Icons.Default.Edit,
        description = "Editar",
        color = MaterialTheme.colorScheme.editIcon
    )
} // IconEdit

@Preview(showBackground = true)
@Composable
fun IconDelete() {
    BaseIcon(
        image = Icons.Default.Delete,
        description = "Eliminar",
        color = MaterialTheme.colorScheme.error
    )
} // IconRemove

@Composable
fun IconDone(isDone: Boolean) {
    BaseIcon(
        image = if (isDone) Icons.Filled.CheckCircle else Icons.Default.RadioButtonUnchecked,
        description = "Tarea finalizada",
        color = MaterialTheme.colorScheme.tertiary
    )
} // IconDone

@Composable
fun IconFilter() {
    BaseIcon(
        image = Icons.Default.FilterList,
        description = "Filtros",
        color = MaterialTheme.colorScheme.onSecondary
    )
} // IconFilter
