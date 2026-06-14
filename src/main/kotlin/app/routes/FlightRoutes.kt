package app.routes

import app.models.*
import app.services.FlightService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Route.flightRoutes(service: FlightService) {
    route("/api/v1/flights") {
        get {
            call.respond(HttpStatusCode.OK, service.getAllFlights())
        }
        get ("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "invalid ID")
            val flight = service.getFlightById(id)
            if (flight == null) {
                return@get call.respond(HttpStatusCode.NotFound, "Flight not Found. maybe mistyped Id?")
            }
            call.respond(flight)
        }
        authenticate {
            post {
                val flight = call.receive<Flights>()
                call.respond(HttpStatusCode.Created, service.create(flight))
            }
            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    return@put call.respond(HttpStatusCode.BadRequest)
                }
                val flight = call.receive<Flights>()
                val updated = service.update(id, flight)
                if (updated == null) {
                    return@put call.respond(HttpStatusCode.NotFound)
                }
                call.respond(updated)
            }
            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    return@delete call.respond(HttpStatusCode.BadRequest, "invalid ID")
                }
                if (!service.delete(id)) {
                    return@delete call.respond(HttpStatusCode.NotFound, "Flight not Found, maybe mistyped?")
                }
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}