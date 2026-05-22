package dam.inakki.listatareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dam.inakki.listatareas.data.local.AppDatabase
import dam.inakki.listatareas.ui.components.Header
import dam.inakki.listatareas.ui.home.PrincipalScreen
import dam.inakki.listatareas.ui.theme.ListaTareasTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getInstance(this) // inicializo la BDD y le paso el contexto de la Activity
        val taskDao = database.taskDao()

        setContent {
            ListaTareasTheme{
                val snackHostState = remember { SnackbarHostState() } // contiene la cola de avisos a poner en pantalla y su estado, como oculto, visible o animando
                val scope = rememberCoroutineScope() // lanza un hilo asincrono cnd se ejecuta el aviso para que el resto de la app no se quede congelada
                                                        // puesto que es una suspend function y tiene que quedarse suspendida 2/3 segundos, y sin ella en ese tiempo el resto de la app no se podria usar
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { Header() },
                    snackbarHost = { SnackbarHost(hostState = snackHostState)}
                ){ innerPadding -> // el sistema le pasa a esta variable las dimensiones de la persiana de notis y de los botones de abajo
                    PrincipalScreen(
                        taskDao = taskDao,
                        modifier = Modifier.padding(innerPadding),
                        onShowError = { // funcion lambda, kt al parecer trata las funciones como objetos y se pueden pasar como parametros, y con esta lambda puedes definir la funcion aqui
                            scope.launch { snackHostState.showSnackbar("No se puede dejar el campo vacío.") } // landa el hilo paralelo y muestra el mensaje por pantalla
                        }
                    ) // PrincipalScreen
                } // Scaffold
            } // Lista Tareas Theme
        } // setContent
    } // onCreate
} // class




