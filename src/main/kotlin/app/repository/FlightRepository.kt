package app.repository

import app.models.Flights
import app.models.Manufacturer
import app.models.flightStatus

interface FlightRepository {

    //finding flights
    suspend fun findAll(): List<Flights>
    suspend fun findById(id: Int): Flights?
    suspend fun findByFlightNum(flnum: String): Flights?
    suspend fun findByAircraft(
        model: String? = null,
        regNumber: String? = null,
        airline: String? = null,
        manufacturer: Manufacturer? = null
    ): List<Flights>
    suspend fun findyByOrigin(
        iata: String? = null,
        icao: String? = null,
        name: String? = null,
        city: String? = null
    ): List<Flights>
    suspend fun findyByDestination(
        iata: String? = null,
        icao: String? = null,
        name: String? = null,
        city: String? = null
    ): List<Flights>
    suspend fun findByStatus(status: flightStatus): List<Flights>
    //general operators
    suspend fun create(flight: Flights): Flights
    suspend fun update(id: Int, flight: Flights): Flights?
    suspend fun delete(id: Int): Boolean
}