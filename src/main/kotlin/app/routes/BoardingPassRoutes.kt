package app.routes

import app.models.BoardingPass
import app.services.BoardingPassService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.boardingPassRoutes(service: BoardingPassService) {
    route("/api/v1/boarding-passes") {
        get { call.respond(service.getAllBoardingPasses()) }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "InvalidId")
                return@get
            }
            val boardingPass = service.getBoardingPassById(id)
            if (boardingPass == null) {
                call.respond(HttpStatusCode.NotFound, "Boarding pass not found")
                return@get
            }
            call.respond(boardingPass)
        }
        get("/flights/{flightId}") {
        val flightId = call.parameters["flightId"]?.toIntOrNull()
            if (flightId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid flight ID")
                return@get
            }
            call.respond(service.getBoardingPassByFlightId(flightId))
        }
        get("/flights/{passenger}") {
            val passenger = call.parameters["passenger"]
            if (passenger == null) {
                call.respond(HttpStatusCode.BadRequest, "No such passenger name")
                return@get
            }
            call.respond(service.getBoardingPassByPassenger(passenger))
        }
        get("/flights/{flightNumber}") {
            val flightNumber = call.parameters["flightNumber"]
            if (flightNumber == null) {
                call.respond(HttpStatusCode.BadRequest, "No flight with assigned flight number")
                return@get
            }
            call.respond(service.getBoardingPassByFlightNumber(flightNumber))
        }
        authenticate {
            post {
                val boardingPass = call.receive<BoardingPass>()
                call.respond(HttpStatusCode.Created, service.createBoardingPass(boardingPass))
            }
            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Already deleted or non existent ID")
                    return@delete
                }
                if (!service.deleteBoardingPass(id)) {
                    call.respond(HttpStatusCode.NotFound, "Boarding Pass not found or non existent")
                    return@delete
                }
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}