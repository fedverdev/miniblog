package com.github.fedverdev.grpc

import com.github.fedverdev.repositories.UserProfileRepository
import com.github.fedverdev.services.GrpcProfileServiceImpl
import com.github.fedverdev.services.ProfileService
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import io.ktor.server.application.Application
import java.net.InetSocketAddress


fun Application.runGrpcServerInBackground() {
    val userProfileRepository = UserProfileRepository()
    val profileService = ProfileService(userProfileRepository)

    Thread {
        val profileServer = NettyServerBuilder.forAddress(InetSocketAddress("0.0.0.0", 9090))
            .addService(GrpcProfileServiceImpl(profileService))
            .build()

        profileServer.start()
        Runtime.getRuntime().addShutdownHook( Thread {
            profileServer.shutdown()
        } )


        profileServer.awaitTermination()
    }.start()

}
