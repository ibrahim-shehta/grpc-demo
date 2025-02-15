package com.grpc.demo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class GrpcConfig {

    private final GrpcServerProperties grpcServerProperties;
    private ManagedChannel managedChannel;

    public GrpcConfig(GrpcServerProperties grpcServerProperties) {
        this.grpcServerProperties = grpcServerProperties;
    }

    @Bean
    public ManagedChannel managedChannel() {
        managedChannel = ManagedChannelBuilder
                .forAddress(grpcServerProperties.getHost(), grpcServerProperties.getPort())
                .usePlaintext().build();

        return managedChannel;
    }


    @PreDestroy
    public void shutdown() {
        if (managedChannel != null) {
            managedChannel.shutdown();
        }
    }

}
