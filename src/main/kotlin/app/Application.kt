package com.dpontoavi.app

import com.dpontoavi.app.database.configureDatabases
import com.dpontoavi.app.plugins.configureHTTP
import com.dpontoavi.app.plugins.configureMonitoring
import com.dpontoavi.app.plugins.configureRouting
import com.dpontoavi.app.plugins.configureSecurity
import com.dpontoavi.app.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureHTTP()
    configureRouting()
}
