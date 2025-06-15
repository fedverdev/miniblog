package com.github.fedverdev

import com.github.fedverdev.config.AppConfig
import com.github.fedverdev.database.DatabaseFactory
import com.github.fedverdev.grpc.runGrpcServerInBackground
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val appConfig = AppConfig(environment.config)
    val database = DatabaseFactory()
    database.init(appConfig.db)
    configureRouting()
    runGrpcServerInBackground()
}
