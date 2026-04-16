package com.dpontoavi.app.plugins


import app.repository.FlightRepositoryImplemenation
import app.routes.flightRoutes
import app.services.FlightService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }

    val flightRepository = FlightRepositoryImplemenation()
    val flightService = FlightService(flightRepository)

    routing {
        get("/api/v1/flights") {
            call.respond(flightRoutes(flightService))
        }
    }
}
