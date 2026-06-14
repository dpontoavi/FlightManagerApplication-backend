package app.repository

import app.database.BoardingPassTable
import app.models.BoardingPass
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import io.ktor.server.application.log

class BoardingPassRepositoryExposed : BoardingPassRepository {
    private fun ResultRow.toBoardingPass() = BoardingPass(
        id = this[BoardingPassTable.id],
        flightId = this[BoardingPassTable.flightId],
        flightNumber = this[BoardingPassTable.flightNumber],
        passenger = this[BoardingPassTable.passenger],
        seat = this[BoardingPassTable.seat],
        terminal = this[BoardingPassTable.terminal],
        gate = this[BoardingPassTable.gate],
        group = this[BoardingPassTable.group],
        date = this[BoardingPassTable.flightDepartDate],
        flightDepart = this[BoardingPassTable.flightDepartTime],
        flightArrival = this[BoardingPassTable.flightArrivalTime],
        status = this[BoardingPassTable.status]
    )

    override suspend fun findAll(): List<BoardingPass> = transaction {
        BoardingPassTable.selectAll().map { it.toBoardingPass() }
    }

    override suspend fun findById(id: Int): BoardingPass? = transaction {
        BoardingPassTable.selectAll()
            .where { BoardingPassTable.id eq id }
            .map { it.toBoardingPass() }
            .singleOrNull()
    }

    override suspend fun findByFlightId(flightId: Int): List<BoardingPass> = transaction {
        BoardingPassTable.selectAll()
            .where { BoardingPassTable.flightId eq flightId }
            .map { it.toBoardingPass() }
    }

    override suspend fun findByFlightNumber(flightNumber: String): List<BoardingPass> = transaction {
        BoardingPassTable.selectAll()
            .where { BoardingPassTable.flightNumber eq flightNumber }
            .map { it.toBoardingPass() }
    }

    override suspend fun findByPassenger(passenger: String): List<BoardingPass> = transaction {
        BoardingPassTable.selectAll()
            .where { BoardingPassTable.passenger eq passenger }
            .map { it.toBoardingPass() }
    }

    override suspend fun create(boardingPass: BoardingPass): BoardingPass = transaction {
        val insertedId = BoardingPassTable.insert {
            it[flightId] = boardingPass.flightId
            it[flightNumber] = boardingPass.flightNumber
            it[passenger] = boardingPass.passenger
            it[seat] = boardingPass.seat
            it[terminal] = boardingPass.terminal
            it[gate] = boardingPass.gate
            it[group] = boardingPass.group
            it[flightDepartDate] = boardingPass.date
            it[flightDepartTime] = boardingPass.flightDepart
            it[flightArrivalTime] = boardingPass.flightArrival
            it[status] = boardingPass.status
        } get BoardingPassTable.id

        BoardingPassTable.selectAll()
            .where { BoardingPassTable.id eq insertedId }
            .map { it.toBoardingPass() }
            .single()
    }

    override suspend fun delete(id: Int): Boolean = transaction {
        val result = BoardingPassTable.deleteWhere { BoardingPassTable.id eq id } > 0
        result

    }


}