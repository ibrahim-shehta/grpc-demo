package com.grpc.demo;

import com.grpc.demo.grpc.HelloGrpcServiceGrpc;
import com.grpc.demo.grpc.HelloRequest;
import com.grpc.demo.grpc.HelloResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends HelloGrpcServiceGrpc.HelloGrpcServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {


        // construct response message
        HelloResponse res = HelloResponse.newBuilder().setMessage("hello from gRPC demo to " + request.getName()).build();

        // set response message in response
        responseObserver.onNext(res);

        responseObserver.onCompleted();
    }
}
