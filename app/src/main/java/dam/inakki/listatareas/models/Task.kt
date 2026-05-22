package dam.inakki.listatareas.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "tasks_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // al tener el autogenerate si se pone 0 por defecto se SQLite genera automatico el ID

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "state")
    val state: Boolean = false
)