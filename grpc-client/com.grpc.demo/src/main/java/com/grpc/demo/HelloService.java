package com.grpc.demo;

import com.grpc.demo.grpc.HelloGrpcServiceGrpc;
import com.grpc.demo.grpc.HelloRequest;
import com.grpc.demo.grpc.HelloResponse;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final ManagedChannel channel;

    public HelloService(final ManagedChannel channel) {
        this.channel = channel;
    }

    public String getHelloMessage(String name) {

        HelloGrpcServiceGrpc.HelloGrpcServiceBlockingStub stub = HelloGrpcServiceGrpc.newBlockingStub(channel);
        HelloResponse response = stub.sayHello(HelloRequest.newBuilder().setName(name).build());

        System.out.println(response.getMessage());
        return response.getMessage();

    }
}
