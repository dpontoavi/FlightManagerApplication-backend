package com.dpontoavi

import com.dpontoavi.app.database.configureDatabases
import com.dpontoavi.app.module
import com.dpontoavi.app.plugins.configureRouting
import com.dpontoavi.app.plugins.configureSerialization
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        environment {
            config = ApplicationConfig("application.yaml")
        }
        application {
            module()
        }
        client.get("/api/v1/flights").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}
