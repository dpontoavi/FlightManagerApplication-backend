package app.models

import kotlinx.serialization.Serializable

@Serializable
data class BoardingPass(
    val id: Int = 0,
    val flightId: Int,
    val flightNumber: String,
    val passenger: String,
    val seat: String,
    val terminal: String,
    val gate: String,
    val group: Int,
    val date: String,
    val flightDepart: String,
    val flightArrival: String,
    val status: flightStatus = flightStatus.SCHEDULED

)
