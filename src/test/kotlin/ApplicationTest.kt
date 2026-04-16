package com.dpontoavi

import com.dpontoavi.app.module
import com.dpontoavi.app.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        client.get("/api/v1/flights").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}
