package hello.spring.cloud.netflix.grpc.server;

import hello.spring.cloud.netflix.grpc.server.service.HelloService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MyGrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8086)
                .addService(new HelloService())
                .build();
        System.out.println("starting server...");
        server.start();
        System.out.println("Server start! ");
        server.awaitTermination();
    }
}
