package dam.inakki.listatareas.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dam.inakki.listatareas.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Task>> // lee la BDD y lo saca ordenado por ID, crea un Flow

    @Insert(onConflict = OnConflictStrategy.REPLACE) // inserta fila y si hay conflicto con ID elimina el antiguo y deja el nuevo
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
} // TaskDao