package com.github.fedverdev.authservice.services.clients.grpc;

import com.github.fedverdev.Profile;
import com.github.fedverdev.ProfileCreationResponse;
import com.github.fedverdev.ProfileServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceGrpcClient {

    @Value("${grpc.profile-service.domain}")
    private String profileServiceDomain;
    private ProfileServiceGrpc.ProfileServiceBlockingStub stub;

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(profileServiceDomain, 9090)
                .usePlaintext()
                .build();
        stub = ProfileServiceGrpc.newBlockingStub(channel);
    }

    public ProfileCreationResponse attemptRegisterProfile(Profile profile) {
        return stub.attemptRegisterProfile(profile);
    }
}
