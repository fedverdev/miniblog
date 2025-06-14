package com.github.fedverdev.grpc

import com.github.fedverdev.services.ProfileServiceImpl
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import io.ktor.server.application.Application
import java.net.InetSocketAddress


fun Application.runGrpcServer() {
    val profileServer = NettyServerBuilder.forAddress(InetSocketAddress("0.0.0.0", 9090))
        .addService(ProfileServiceImpl())
        .build()

    profileServer.start()
    Runtime.getRuntime().addShutdownHook( Thread {
        profileServer.shutdown()
    } )


    profileServer.awaitTermination()
}
