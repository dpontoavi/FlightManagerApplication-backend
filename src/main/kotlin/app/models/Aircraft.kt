package app.models

import kotlinx.serialization.Serializable

@Serializable
data class Aircraft(
    val model: String,
    val regNumber: String,
    val airline: String,
    val manufacturer: Manufacturer,
    val paxCapacity: Int
    )

@Serializable
enum class Manufacturer {
    AIRBUS,
    BOEING,
    EMBRAER,
    CESSNA,
    CIRRUS,
    BOMBARDIER,
    ATR,
    COMAC
}