package com.github.fedverdev.authservice.services.clients.grpc;

import com.github.fedverdev.Profile;
import com.github.fedverdev.ProfileCreationResponse;
import com.github.fedverdev.ProfileServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceGrpcClient {
    @GrpcClient("profile-service")
    private ProfileServiceGrpc.ProfileServiceBlockingStub stub;

    public ProfileCreationResponse attemptRegisterProfile(Profile profile) {
        return stub.attemptRegisterProfile(profile);
    }
}
