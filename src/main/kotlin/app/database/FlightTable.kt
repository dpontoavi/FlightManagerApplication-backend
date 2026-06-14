package app.database

import app.models.Manufacturer
import app.models.flightStatus
import org.jetbrains.exposed.sql.Table

object FlightTable : Table("flights") {
    val id = integer("id").autoIncrement()
    val flightnumber = varchar("flight_number", 7)
    val originIata = varchar("origin_iata", 3)
    val originIcao = varchar("origin_icao", 4)
    val originName = varchar("origin_name", 60)
    val originCity = varchar("origin_city", 100)
    val destinationIata = varchar("destination_iata", 3)
    val destinationIcao = varchar("destination_icao", 4)
    val destinationName = varchar("destination_name", 60)
    val destinationCity = varchar("destination_city", 100)
    val aircraftModel = varchar("aircraft_model", 50)
    val aircraftRegistration = varchar("aircraft_registration", 6)
    val aircraftAirline = varchar("aircraft_airline", 50)
    val aircraftManufacturer = enumerationByName("aircraft_manufacturer", 50, Manufacturer::class)
    val terminal = varchar("terminal", 10)
    val gate = varchar("gate", 5)
    val group = integer("group")
    val seat = varchar("seat", 5)
    val passengerName = varchar("passenger_name", 100)
    val flightDepartTime = varchar("depart_time", 5)
    val flightDepartDate = varchar("depart_date", 10)
    val flightArrivalTime = varchar("arrival_time", 5)

    val status = enumerationByName("status", 20, flightStatus::class)

    override val primaryKey = PrimaryKey(id)
}