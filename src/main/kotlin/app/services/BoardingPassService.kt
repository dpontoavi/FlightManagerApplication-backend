package app.services

import app.models.BoardingPass
import app.repository.BoardingPassRepository

class BoardingPassService(private val repository: BoardingPassRepository) {
    suspend fun getAllBoardingPasses() = repository.findAll()
    suspend fun getBoardingPassById(id: Int) = repository.findById(id)
    suspend fun getBoardingPassByFlightNumber(flightnumber: String) = repository.findByFlightNumber(flightnumber)
    suspend fun getBoardingPassByFlightId(flightId: Int) = repository.findByFlightId(flightId)
    suspend fun getBoardingPassByPassenger(passenger: String) = repository.findByPassenger(passenger)
    suspend fun createBoardingPass(boardingPass: BoardingPass) = repository.create(boardingPass)
    suspend fun deleteBoardingPass(id: Int) = repository.delete(id)
}