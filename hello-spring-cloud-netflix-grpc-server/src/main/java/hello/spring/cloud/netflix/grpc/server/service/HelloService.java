package hello.spring.cloud.netflix.grpc.server.service;

import cn.springcloud.grpc.HelloRequest;
import cn.springcloud.grpc.HelloResponse;
import cn.springcloud.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // 打印 request 对象
        System.out.println(request);
        String greeting = "Hi " + request.getName() + " you are " + request.getAge()
                + "years old" +
                " your hoby is " + (request.getHobbiesList()) + " your tags " +
                request.getTagsMap();
        HelloResponse response = HelloResponse.newBuilder().setMessage(greeting).build();
        // 在流中 写入 response方法
        responseObserver.onNext(response);
        // 在流中写入结束
        responseObserver.onCompleted();
    }
}
