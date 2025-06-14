package com.github.fedverdev.services

import com.github.fedverdev.Profile
import com.github.fedverdev.ProfileCreationResponse
import com.github.fedverdev.ProfileServiceGrpc
import io.grpc.stub.StreamObserver

class ProfileServiceImpl : ProfileServiceGrpc.ProfileServiceImplBase() {
    override fun attemptRegisterProfile(
        request: Profile?,
        responseObserver: StreamObserver<ProfileCreationResponse?>?
    ) {
        responseObserver?.onNext(ProfileCreationResponse.newBuilder().setOk(true).build())
        println("Profile registered successfully")
        println(request.toString())
        responseObserver?.onCompleted()
    }
}