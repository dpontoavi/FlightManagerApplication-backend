package app.repository

import app.models.Flights
import app.models.Manufacturer
import app.models.flightStatus

class FlightRepositoryImplemenation : FlightRepository {

    private val flights = mutableListOf<Flights>()
    private var nextId = 1

    override suspend fun findAll() = flights.toList()
    override suspend fun findById(id: Int) = flights.find { it.id == id }

    override suspend fun findByFlightNum(flnum: String): Flights? {
        return flights.find { it.flightNumber == flnum }
    }

    override suspend fun findByAircraft(
        model: String?,
        regNumber: String?,
        airline: String?,
        manufacturer: Manufacturer?
    ): List<Flights> {
        return flights.filter {
            flight ->
            (model == null || flight.aircraft.model == model) &&
                    (regNumber == null || flight.aircraft.regNumber == regNumber) &&
                    (airline == null || flight.aircraft.airline == airline) &&
                    (manufacturer == null || flight.aircraft.manufacturer == manufacturer)
        }
    }

    override suspend fun findyByOrigin(
        iata: String?,
        icao: String?,
        name: String?,
        city: String?
    ): List<Flights> {
        return flights.filter {
            flight ->
            (iata == null || flight.origin.IATA == iata)
            (icao == null || flight.origin.ICAO == icao)
            (name == null || flight.origin.NAME == name)
            (city == null || flight.origin.CITY == city)
        }

    }

    override suspend fun findyByDestination(
        iata: String?,
        icao: String?,
        name: String?,
        city: String?
    ): List<Flights> {
        return flights.filter {
                flight ->
            (iata == null || flight.destination.IATA == iata)
            (icao == null || flight.destination.ICAO == icao)
            (name == null || flight.destination.NAME == name)
            (city == null || flight.destination.CITY == city)
        }
    }

    override suspend fun findByStatus(status: flightStatus): List<Flights> {
        return flights.filter { it.status == status }
    }

    override suspend fun create(flight: Flights): Flights {
        val newFlight = flight.copy(id = nextId++)
        flights.add(newFlight)
        return newFlight
    }

    override suspend fun update(id: Int, flight: Flights): Flights? {
        val index = flights.indexOfFirst { it.id == id}
        if (index == -1) return null
        val updated = flight.copy(id = id)
        flights[index] = updated
        return updated
    }

    override suspend fun delete(id: Int): Boolean {
        return flights.removeIf { it.id == id }
    }

}