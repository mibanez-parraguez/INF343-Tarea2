package avion.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: planecontrol.proto")
public final class InfoServiceGrpc {

  private InfoServiceGrpc() {}

  public static final String SERVICE_NAME = "tareados.InfoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<avion.proto.InfoRequest,
      avion.proto.InfoResponse> getInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Info",
      requestType = avion.proto.InfoRequest.class,
      responseType = avion.proto.InfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<avion.proto.InfoRequest,
      avion.proto.InfoResponse> getInfoMethod() {
    io.grpc.MethodDescriptor<avion.proto.InfoRequest, avion.proto.InfoResponse> getInfoMethod;
    if ((getInfoMethod = InfoServiceGrpc.getInfoMethod) == null) {
      synchronized (InfoServiceGrpc.class) {
        if ((getInfoMethod = InfoServiceGrpc.getInfoMethod) == null) {
          InfoServiceGrpc.getInfoMethod = getInfoMethod = 
              io.grpc.MethodDescriptor.<avion.proto.InfoRequest, avion.proto.InfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "tareados.InfoService", "Info"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  avion.proto.InfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  avion.proto.InfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new InfoServiceMethodDescriptorSupplier("Info"))
                  .build();
          }
        }
     }
     return getInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InfoServiceStub newStub(io.grpc.Channel channel) {
    return new InfoServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InfoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InfoServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InfoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InfoServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class InfoServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void info(avion.proto.InfoRequest request,
        io.grpc.stub.StreamObserver<avion.proto.InfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInfoMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                avion.proto.InfoRequest,
                avion.proto.InfoResponse>(
                  this, METHODID_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class InfoServiceStub extends io.grpc.stub.AbstractStub<InfoServiceStub> {
    private InfoServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InfoServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InfoServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InfoServiceStub(channel, callOptions);
    }

    /**
     */
    public void info(avion.proto.InfoRequest request,
        io.grpc.stub.StreamObserver<avion.proto.InfoResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class InfoServiceBlockingStub extends io.grpc.stub.AbstractStub<InfoServiceBlockingStub> {
    private InfoServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InfoServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InfoServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InfoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<avion.proto.InfoResponse> info(
        avion.proto.InfoRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class InfoServiceFutureStub extends io.grpc.stub.AbstractStub<InfoServiceFutureStub> {
    private InfoServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InfoServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InfoServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InfoServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InfoServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InfoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INFO:
          serviceImpl.info((avion.proto.InfoRequest) request,
              (io.grpc.stub.StreamObserver<avion.proto.InfoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class InfoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InfoServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return avion.proto.PlaneControlProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("InfoService");
    }
  }

  private static final class InfoServiceFileDescriptorSupplier
      extends InfoServiceBaseDescriptorSupplier {
    InfoServiceFileDescriptorSupplier() {}
  }

  private static final class InfoServiceMethodDescriptorSupplier
      extends InfoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    InfoServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (InfoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InfoServiceFileDescriptorSupplier())
              .addMethod(getInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
