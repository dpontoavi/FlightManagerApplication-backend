package app.models

import kotlinx.serialization.Serializable

@Serializable
data class AdminCredentials(
    val login: String,
    val password: String
)
