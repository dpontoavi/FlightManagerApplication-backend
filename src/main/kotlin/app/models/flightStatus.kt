package app.models

import kotlinx.serialization.Serializable

@Serializable
enum class flightStatus {
    SCHEDULED,
    BOARDING,
    IN_FLIGHT,
    COMPLETED,
    CANCELLED
}