package app.repository

import app.database.FlightTable
import app.models.Aircraft
import app.models.Airports
import app.models.Flights
import app.models.Manufacturer
import app.models.flightStatus
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class FlightRepositoryExposed : FlightRepository {

    private fun ResultRow.toFlight() = Flights(
        id = this[FlightTable.id],
        flightNumber = this[FlightTable.flightnumber],
        terminal = this[FlightTable.terminal],
        gate = this[FlightTable.gate],
        group = this[FlightTable.group],
        seat = this[FlightTable.seat],
        passenger = this[FlightTable.passengerName],
        status = this[FlightTable.status],
        origin = Airports(
            IATA = this[FlightTable.originIata],
            ICAO = this[FlightTable.originIcao],
            NAME = this[FlightTable.originName],
            CITY = this[FlightTable.originCity],
        ),
        destination = Airports(
            IATA = this[FlightTable.destinationIata],
            ICAO = this[FlightTable.destinationIcao],
            NAME = this[FlightTable.destinationName],
            CITY = this[FlightTable.destinationCity],
        ),
        aircraft = Aircraft(
            model = this[FlightTable.aircraftModel],
            regNumber = this[FlightTable.aircraftRegistration],
            airline = this[FlightTable.aircraftAirline],
            manufacturer = this[FlightTable.aircraftManufacturer],
        ),
        date = this[FlightTable.flightDepartDate],
        flightDepart = this[FlightTable.flightDepartTime],
        flightArr = this[FlightTable.flightArrivalTime]
    )

    override suspend fun findAll(): List<Flights> = transaction {
        FlightTable.selectAll().map { it.toFlight() }
    }
    override suspend fun findById(id: Int): Flights? = transaction {
        FlightTable.selectAll()
            .where { FlightTable.id eq id }
            .map { it.toFlight() }
            .singleOrNull()
    }

    override suspend fun findByFlightNum(flnum: String): Flights? = transaction {
        FlightTable.selectAll()
            .where { FlightTable.flightnumber eq flnum }
            .map { it.toFlight() }
            .singleOrNull()
    }

    override suspend fun findByAircraft (
        model: String?,
        regNumber: String?,
        airline: String?,
        manufacturer: Manufacturer?
    ): List<Flights> = transaction {
        FlightTable.selectAll()
            .where {
                val conditions = mutableSetOf<Op<Boolean>>()
                if (model != null) conditions.add(FlightTable.aircraftModel eq model)
                if (regNumber != null) conditions.add(FlightTable.aircraftRegistration eq regNumber)
                if (airline != null) conditions.add(FlightTable.aircraftAirline eq airline)
                if (manufacturer != null) conditions.add(FlightTable.aircraftManufacturer eq manufacturer)
                conditions.reduceOrNull { a, b -> a and b } ?: Op.TRUE
        }
            .map { it.toFlight() }
    }

    override suspend fun findyByOrigin(
        iata: String?,
        icao: String?,
        name: String?,
        city: String?
    ): List<Flights> = transaction {
        FlightTable.selectAll()
            .where {
                val conditions = mutableSetOf<Op<Boolean>>()
                if (iata != null) conditions.add(FlightTable.originIata eq iata)
                if (icao != null) conditions.add(FlightTable.originIcao eq icao)
                if (name != null) conditions.add(FlightTable.originName eq name)
                if (city != null) conditions.add(FlightTable.originCity eq city)
                conditions.reduceOrNull { a, b -> a and b } ?: Op.TRUE
            }
            .map { it.toFlight() }

    }

    override suspend fun findyByDestination(
        iata: String?,
        icao: String?,
        name: String?,
        city: String?
    ): List<Flights> = transaction {
        FlightTable.selectAll()
            .where {
                val conditions = mutableSetOf<Op<Boolean>>()
                if (iata != null) conditions.add(FlightTable.destinationIata eq iata)
                if (icao != null) conditions.add(FlightTable.destinationIcao eq icao)
                if (name != null) conditions.add(FlightTable.destinationName eq name)
                if (city != null) conditions.add(FlightTable.destinationCity eq city)
                conditions.reduceOrNull { a, b -> a and b } ?: Op.TRUE
            }
            .map { it.toFlight() }

    }

    override suspend fun findByStatus(status: flightStatus): List<Flights> = transaction {
        FlightTable.selectAll()
            .where { FlightTable.status eq status }
            .map { it.toFlight() }
    }

    override suspend fun create(flight: Flights): Flights = transaction {
        val insertedId = FlightTable.insert {
            it[flightnumber] = flight.flightNumber
            it[originIata] = flight.origin.IATA
            it[originIcao] = flight.origin.ICAO
            it[originName] = flight.origin.NAME
            it[originCity] = flight.origin.CITY
            it[destinationIata] = flight.destination.IATA
            it[destinationIcao] = flight.destination.ICAO
            it[destinationName] = flight.destination.NAME
            it[destinationCity] = flight.destination.CITY
            it[aircraftModel] = flight.aircraft.model
            it[aircraftRegistration] = flight.aircraft.regNumber
            it[aircraftAirline] = flight.aircraft.airline
            it[aircraftManufacturer] = flight.aircraft.manufacturer
            it[terminal] = flight.terminal
            it[gate] = flight.gate
            it[group] = flight.group
            it[seat] = flight.seat
            it[passengerName] = flight.passenger
            it[flightDepartTime] = flight.flightDepart
            it[flightDepartDate] = flight.date
            it[flightArrivalTime] = flight.flightArr
            it[status] = flight.status
        } get FlightTable.id
        FlightTable.selectAll()
            .where { FlightTable.id eq insertedId }
            .map { it.toFlight() }
            .single()
    }

    override suspend fun update(id: Int, flight: Flights): Flights? = transaction {
        val updated = FlightTable.update({ FlightTable.id eq id}) {
            it[flightnumber] = flight.flightNumber
            it[originIata] = flight.origin.IATA
            it[originIcao] = flight.origin.ICAO
            it[originName] = flight.origin.NAME
            it[originCity] = flight.origin.CITY
            it[destinationIata] = flight.destination.IATA
            it[destinationIcao] = flight.destination.ICAO
            it[destinationName] = flight.destination.NAME
            it[destinationCity] = flight.destination.CITY
            it[aircraftModel] = flight.aircraft.model
            it[aircraftRegistration] = flight.aircraft.regNumber
            it[aircraftAirline] = flight.aircraft.airline
            it[aircraftManufacturer] = flight.aircraft.manufacturer
            it[terminal] = flight.terminal
            it[gate] = flight.gate
            it[group] = flight.group
            it[seat] = flight.seat
            it[passengerName] = flight.passenger
            it[flightDepartTime] = flight.flightDepart
            it[flightDepartDate] = flight.date
            it[flightArrivalTime] = flight.flightArr
            it[status] = flight.status
        }
        if (updated == 0) null
        else FlightTable.selectAll()
            .where { FlightTable.id eq id }
            .map { it.toFlight() }
            .singleOrNull()
    }

    override suspend fun delete(id: Int): Boolean = transaction {
        FlightTable.deleteWhere { FlightTable.id eq id } > 0
    }

}