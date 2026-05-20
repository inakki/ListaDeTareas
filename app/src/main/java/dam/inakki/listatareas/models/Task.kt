package dam.inakki.listatareas.models

import kotlinx.serialization.Serializable

@Serializable
data class Task (
    val id: Int,
    val name: String,
    val state: Boolean = false
)