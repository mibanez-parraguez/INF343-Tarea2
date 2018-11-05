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
public final class PlaneControlServiceGrpc {

  private PlaneControlServiceGrpc() {}

  public static final String SERVICE_NAME = "tareados.PlaneControlService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<avion.proto.LandRequest,
      avion.proto.LandResponse> getLandMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Land",
      requestType = avion.proto.LandRequest.class,
      responseType = avion.proto.LandResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<avion.proto.LandRequest,
      avion.proto.LandResponse> getLandMethod() {
    io.grpc.MethodDescriptor<avion.proto.LandRequest, avion.proto.LandResponse> getLandMethod;
    if ((getLandMethod = PlaneControlServiceGrpc.getLandMethod) == null) {
      synchronized (PlaneControlServiceGrpc.class) {
        if ((getLandMethod = PlaneControlServiceGrpc.getLandMethod) == null) {
          PlaneControlServiceGrpc.getLandMethod = getLandMethod = 
              io.grpc.MethodDescriptor.<avion.proto.LandRequest, avion.proto.LandResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "tareados.PlaneControlService", "Land"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  avion.proto.LandRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  avion.proto.LandResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PlaneControlServiceMethodDescriptorSupplier("Land"))
                  .build();
          }
        }
     }
     return getLandMethod;
  }

  private static volatile io.grpc.MethodDescriptor<avion.proto.TakeoffRequest,
      avion.proto.TakeoffResponse> getTakeoffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Takeoff",
      requestType = avion.proto.TakeoffRequest.class,
      responseType = avion.proto.TakeoffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<avion.proto.TakeoffRequest,
      avion.proto.TakeoffResponse> getTakeoffMethod() {
    io.grpc.MethodDescriptor<avion.proto.TakeoffRequest, avion.proto.TakeoffResponse> getTakeoffMethod;
    if ((getTakeoffMethod = PlaneControlServiceGrpc.getTakeoffMethod) == null) {
      synchronized (PlaneControlServiceGrpc.class) {
        if ((getTakeoffMethod = PlaneControlServiceGrpc.getTakeoffMethod) == null) {
          PlaneControlServiceGrpc.getTakeoffMethod = getTakeoffMethod = 
              io.grpc.MethodDescriptor.<avion.proto.TakeoffRequest, avion.proto.TakeoffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "tareados.PlaneControlService", "Takeoff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  avion.proto.TakeoffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  avion.proto.TakeoffResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PlaneControlServiceMethodDescriptorSupplier("Takeoff"))
                  .build();
          }
        }
     }
     return getTakeoffMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PlaneControlServiceStub newStub(io.grpc.Channel channel) {
    return new PlaneControlServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PlaneControlServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PlaneControlServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PlaneControlServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PlaneControlServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PlaneControlServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<avion.proto.LandRequest> land(
        io.grpc.stub.StreamObserver<avion.proto.LandResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getLandMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<avion.proto.TakeoffRequest> takeoff(
        io.grpc.stub.StreamObserver<avion.proto.TakeoffResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getTakeoffMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLandMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                avion.proto.LandRequest,
                avion.proto.LandResponse>(
                  this, METHODID_LAND)))
          .addMethod(
            getTakeoffMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                avion.proto.TakeoffRequest,
                avion.proto.TakeoffResponse>(
                  this, METHODID_TAKEOFF)))
          .build();
    }
  }

  /**
   */
  public static final class PlaneControlServiceStub extends io.grpc.stub.AbstractStub<PlaneControlServiceStub> {
    private PlaneControlServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PlaneControlServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlaneControlServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PlaneControlServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<avion.proto.LandRequest> land(
        io.grpc.stub.StreamObserver<avion.proto.LandResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getLandMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<avion.proto.TakeoffRequest> takeoff(
        io.grpc.stub.StreamObserver<avion.proto.TakeoffResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getTakeoffMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class PlaneControlServiceBlockingStub extends io.grpc.stub.AbstractStub<PlaneControlServiceBlockingStub> {
    private PlaneControlServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PlaneControlServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlaneControlServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PlaneControlServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class PlaneControlServiceFutureStub extends io.grpc.stub.AbstractStub<PlaneControlServiceFutureStub> {
    private PlaneControlServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PlaneControlServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlaneControlServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PlaneControlServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LAND = 0;
  private static final int METHODID_TAKEOFF = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PlaneControlServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PlaneControlServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LAND:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.land(
              (io.grpc.stub.StreamObserver<avion.proto.LandResponse>) responseObserver);
        case METHODID_TAKEOFF:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.takeoff(
              (io.grpc.stub.StreamObserver<avion.proto.TakeoffResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PlaneControlServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PlaneControlServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return avion.proto.PlaneControlProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PlaneControlService");
    }
  }

  private static final class PlaneControlServiceFileDescriptorSupplier
      extends PlaneControlServiceBaseDescriptorSupplier {
    PlaneControlServiceFileDescriptorSupplier() {}
  }

  private static final class PlaneControlServiceMethodDescriptorSupplier
      extends PlaneControlServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PlaneControlServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PlaneControlServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PlaneControlServiceFileDescriptorSupplier())
              .addMethod(getLandMethod())
              .addMethod(getTakeoffMethod())
              .build();
        }
      }
    }
    return result;
  }
}
