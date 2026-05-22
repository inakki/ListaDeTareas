package dam.inakki.listatareas.data

import dam.inakki.listatareas.data.local.TaskDao
import dam.inakki.listatareas.models.Task
import kotlinx.coroutines.flow.Flow

class TaskManager(private val taskDao: TaskDao) {

    val tasksFlow: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun createNewTask(task: Task) {
        taskDao.insertTask(task)
    } // createNewTask

    suspend fun removeTask(task: Task) {
        taskDao.deleteTask(task)
    } //removeTask

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    } // renameTask
} // TaskManager
