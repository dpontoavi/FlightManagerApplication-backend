package app.models

import kotlinx.serialization.Serializable

@Serializable
data class Flights(
    val id: Int = 0,
    val origin: Airports,
    val destination: Airports,
    val flightNumber: String,
    val aircraft: Aircraft,
    val date: String,
    val flightDepart: String,
    val flightBoard: String,
    val duration: String,
    val terminal: Int,
    val gate: Int,
    val group: Int,
    val seat: String,
    val status: flightStatus = flightStatus.SCHEDULED


)

@Serializable
enum class flightStatus {
    SCHEDULED,
    BOARDING,
    IN_FLIGHT,
    COMPLETED,
    CANCELLED
}