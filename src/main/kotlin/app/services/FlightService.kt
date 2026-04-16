package app.services

import app.models.Flights
import app.models.Manufacturer
import app.models.flightStatus
import app.repository.FlightRepository

class FlightService (private val repository: FlightRepository) {
    suspend fun getAllFlights() = repository.findAll()
    suspend fun getFlightById(id: Int) = repository.findById(id)
    suspend fun findByFlightNum(flnum: String) = repository.findByFlightNum(flnum)
    suspend fun findByAircraft(
        model: String? = null,
        regNumber: String? = null,
        airline: String? = null,
        manufacturer: Manufacturer? = null
    ) = repository.findByAircraft(model, regNumber, airline, manufacturer)
    suspend fun findyByOrigin(
        iata: String? = null,
        icao: String? = null,
        name: String? = null,
        city: String? = null
    ) = repository.findyByOrigin(iata, icao, name, city)
    suspend fun findyByDestination(
        iata: String? = null,
        icao: String? = null,
        name: String? = null,
        city: String? = null
    ) = repository.findyByDestination(iata, icao, name, city)
    suspend fun findByStatus(status: flightStatus) = repository.findByStatus(status)
    suspend fun create(flight: Flights) = repository.create(flight)
    suspend fun update(id: Int, flight: Flights) = repository.update(id, flight)
    suspend fun delete(id: Int) = repository.delete(id)
}