package dam.inakki.listatareas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import dam.inakki.listatareas.models.Task
import androidx.room.Room

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao // se conecta el DAO con la BDD

    companion object { // Singleton para que sólo haya 1 instancia
        @Volatile // almacena esta variable en la RAM para que todos los núcleos tengan acceso de forma actualizada
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(lock = this) { // synchronized hace que solo 1 hilo pueda acceder a esta funcion
                val instance = Room.databaseBuilder(
                    context.applicationContext, // cojo el contexto de la app
                    AppDatabase::class.java, // se traduce a archivo java pq Rooms es una libreria java
                    "tasks_database"
                )
                    .fallbackToDestructiveMigration() // prioriza que la app funcione a los datos, si mñ se actualiza las tablas y no se hace la migracion bien elimina la BDD y crea una nueva
                    .build() // Construye la BDD

                INSTANCE = instance
                instance // devuelve la instancia de la BDD
            }
        }
    }
} // AppDatabase