package com.dpontoavi.app.plugins


import app.repository.BoardingPassRepository
import app.repository.BoardingPassRepositoryExposed
import app.repository.FlightRepositoryExposed
import app.routes.boardingPassRoutes
import app.routes.flightRoutes
import app.services.BoardingPassService
import app.services.FlightService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            cause.printStackTrace()
            call.respondText(text = "500: $cause | cause: ${cause.cause?.message}" , status = HttpStatusCode.InternalServerError)
        }
    }

    val flightRepository = FlightRepositoryExposed()
    val flightService = FlightService(flightRepository)

    val boardingPassRepo = BoardingPassRepositoryExposed()
    val boardingPassService = BoardingPassService(boardingPassRepo)

    routing {
        flightRoutes(flightService)
        boardingPassRoutes(boardingPassService)
        /*get("/api/v1/flights") {
            call.respond(flightRoutes(flightService))
        }*/
    }
}
