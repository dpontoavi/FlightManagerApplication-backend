package com.dpontoavi.app.database

import app.database.BoardingPassTable
import app.database.FlightTable
import io.ktor.server.application.*
import java.sql.Connection
import java.sql.DriverManager
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {

    try {
        val url = environment.config.property("postgres.url").getString()
        val user = environment.config.property("postgres.user").getString()
        val password = environment.config.property("postgres.password").getString()

        Database.connect(
            url = url,
            user = user,
            driver = "org.postgresql.Driver",
            password = password,
        )
        transaction {
            SchemaUtils.create(FlightTable)
            SchemaUtils.create(BoardingPassTable)
        }
        log.info("Connected to db at $url")
        //val dbConnection: Connection = connectToPostgres(embedded = true)
    }
    catch (e: Exception) {
        log.error("db config failed: ${e.message}")
        log.error("Cause: ${e.cause?.message}")
        throw e
    }

}
/*fun Application.connectToPostgres(embedded: Boolean): Connection {
    Class.forName("org.postgresql.Driver")
    if (embedded) {
        log.info("Using embedded H2 database for testing; replace this flag to use postgres")
        return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "root", "")
    } else {
        val url = environment.config.property("postgres.url").getString()
        log.info("Connecting to postgres database at $url")
        val user = environment.config.property("postgres.user").getString()
        val password = environment.config.property("postgres.password").getString()

        return DriverManager.getConnection(url, user, password)
    }
}*/
