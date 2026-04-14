package app.models

import kotlinx.serialization.Serializable

@Serializable
data class Airports(
    val IATA: String,
    val ICAO: String,
    val NAME: String,
    val CITY: String
)
