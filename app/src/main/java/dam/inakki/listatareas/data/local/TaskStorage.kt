package dam.inakki.listatareas.data.local

import android.content.Context
import dam.inakki.listatareas.models.Task
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun saveData(context: Context, taskList: MutableList<Task>) {
    val jsonText = Json.encodeToString(taskList)

    // No se puede utilizar el File normal, android no lo reconoce (eso es solo para PC), por eso hay que utilizar el openFileOutput
    // El mode private crea una carpeta oculta que no se puede ver, que solo esta app puede gestionar
    context.openFileOutput("tasks.json", Context.MODE_PRIVATE).use { output ->
        output.write(jsonText.toByteArray())
    }

    // el use cierra el archivo automaticamente, para no consumir mas recursos
} // saveChanges

fun loadData(context: Context): MutableList<Task> {
    val tasks = File(context.filesDir, "tasks.json")

    if (!tasks.exists()) return mutableListOf()

    return try {
        val jsonText = tasks.readText() // lee el archivo
        Json.decodeFromString<MutableList<Task>>(jsonText) // decodifica y almacena en una lista mutable de tareas
    } catch(e: Exception){
        mutableListOf() // si por lo que sea surje 1 error, devuelve la lista vacia
    } // ¡ ¡ ¡ ¡ REVISAAAAAR ! ! ! !
} // loadData