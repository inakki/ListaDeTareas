package dam.inakki.listatareas.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import dam.inakki.listatareas.data.local.loadData
import dam.inakki.listatareas.data.local.saveData
import dam.inakki.listatareas.models.Task

class TaskManager(val context: Context) {
    private val _taskList: SnapshotStateList<Task> = mutableStateListOf<Task>().apply {
        addAll(loadData(context))
    }
    val taskList: List<Task> get() = _taskList // esto no es una list como tal, es una referencia a la lista privada, cuando se llama
                                                // a esta constante, esta actua como acceso directo y consulta la informacion en la
                                                // constante privada en modo solo lectura
    fun createNewTask(name: String) {
        this._taskList.add(Task(id = sizeTaskList(), name = name))
        this.saveTaskList()
    } // createNewTask

    fun removeTask(position: Int) {
        this._taskList.removeAt(position)
        this.saveTaskList()
    } //removeTask

    fun renameTask(position: Int, newName: String) {
        this._taskList[position] = _taskList[position].copy(name = newName) // se tiene que hacer esto para que compose vea en el remember que ha cambiado
        this.saveTaskList()                                                 // y asi actualice la pantalla
    } // renameTask

    fun taskChangeState(position: Int) {
        this._taskList[position] = _taskList[position].copy(state = !_taskList[position].state)
        this.saveTaskList()
    } // taskDone

    fun sizeTaskList(): Int{
        return this._taskList.size
    } // sizeTaskList

    fun saveTaskList() {
        saveData(this.context, this._taskList)
    } // saveTaskList
} // TaskManager
