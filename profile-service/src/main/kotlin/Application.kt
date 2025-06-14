package com.github.fedverdev

import com.github.fedverdev.grpc.runGrpcServer
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    runGrpcServer()
}
