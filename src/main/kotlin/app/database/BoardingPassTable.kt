package app.database

import app.models.flightStatus
import org.jetbrains.exposed.sql.Table

object BoardingPassTable : Table("boarding_pass"){
    val id = integer("id").autoIncrement()
    val flightId = integer("flight_id").references(FlightTable.id)
    val flightNumber = varchar("flight_number", 7)
    val passenger = varchar("passenger", 100)
    val seat = varchar("seat",5)
    val terminal = varchar("terminal", 10)
    val gate = varchar("gate", 5)
    val group = integer("group")
    val flightDepartTime = varchar("depart_time", 5)
    val flightDepartDate = varchar("depart_date", 10)
    val flightArrivalTime = varchar("arrival_time", 5)

    val status = enumerationByName("status", 20, flightStatus::class)
}