package com.github.fedverdev.services

import com.github.fedverdev.Profile
import com.github.fedverdev.ProfileCreationResponse
import com.github.fedverdev.ProfileServiceGrpc
import io.grpc.stub.StreamObserver

class GrpcProfileServiceImpl(
    val profileService: ProfileService,
) : ProfileServiceGrpc.ProfileServiceImplBase() {
    override fun attemptRegisterProfile(
        request: Profile?,
        responseObserver: StreamObserver<ProfileCreationResponse?>?
    ) {
        if (request != null) {
            profileService.registerProfile(request)
            responseObserver?.onNext(ProfileCreationResponse.newBuilder().setOk(true).build())
        } else {
            responseObserver?.onNext(ProfileCreationResponse.newBuilder().setOk(false).build())
        }
        responseObserver?.onCompleted()
    }
}