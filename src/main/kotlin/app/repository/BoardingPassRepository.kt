package app.repository

import app.models.BoardingPass

interface BoardingPassRepository {
    suspend fun findAll(): List<BoardingPass>
    suspend fun findById(id: Int): BoardingPass?
    suspend fun findByFlightId(flightId: Int): List<BoardingPass>
    suspend fun findByFlightNumber(flightNumber: String): List<BoardingPass>
    suspend fun findByPassenger(passenger: String): List<BoardingPass>
    suspend fun create(boardingPass: BoardingPass): BoardingPass
    suspend fun delete(id: Int): Boolean
}