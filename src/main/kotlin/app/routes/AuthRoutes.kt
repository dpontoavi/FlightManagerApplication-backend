package app.routes

import app.models.AdminCredentials
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureAuth() {
    val adminLogin = System.getenv("ADMIN_LOGIN")
    val adminPassword = System.getenv("ADMIN_PASSWORD")
    val jwtSecret = environment.config.property("jwt.secret").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtDomain = environment.config.property("jwt.domain").getString()

    val randomPath = java.util.UUID.randomUUID().toString().replace("-", "")
    log.info("Admin panel available at: /auth/$randomPath")

    routing {
        post("/auth/$randomPath") {
            val credentials = call.receive<AdminCredentials>()
            if (credentials.login != adminLogin || credentials.password != adminPassword) {
                call.respond(HttpStatusCode.Unauthorized, "Invalid Credentials")
                return@post
            }

            val token = JWT.create()
                .withAudience(jwtAudience)
                .withIssuer(jwtDomain)
                .withExpiresAt(java.util.Date(System.currentTimeMillis() + 3_600_000))
                .sign(Algorithm.HMAC256(jwtSecret))

            call.respond(mapOf("token" to token))
        }
    }
}