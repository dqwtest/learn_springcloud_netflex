package hello.spring.cloud.netflix.grpc.client;

import cn.springcloud.grpc.HelloRequest;
import cn.springcloud.grpc.HelloResponse;
import cn.springcloud.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MyGrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8086)
                // 使用纯文本创建链接
                .usePlaintext()
                .build();
        HelloServiceGrpc.HelloServiceBlockingStub stub =
                HelloServiceGrpc.newBlockingStub(channel);
        HelloResponse helloResponse = stub.sayHello(
                HelloRequest.newBuilder()
                .setName("forezp")
                .setAge(17)
                .addHobbies("fooball").putTags("how?", "wonderful")
                .build()
        );
        System.out.println(helloResponse);
        channel.shutdown();
    }
}
