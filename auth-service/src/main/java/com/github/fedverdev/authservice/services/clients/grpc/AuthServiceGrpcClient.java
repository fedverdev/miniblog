package com.github.fedverdev.authservice.services.clients.grpc;

import com.github.fedverdev.Profile;
import com.github.fedverdev.ProfileCreationResponse;
import com.github.fedverdev.ProfileServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceGrpcClient {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
            .usePlaintext()
            .build();
    ProfileServiceGrpc.ProfileServiceBlockingStub stub = ProfileServiceGrpc.newBlockingStub(channel);

    public ProfileCreationResponse attemptRegisterProfile(Profile profile) {
        return stub.attemptRegisterProfile(profile);
    }
}
