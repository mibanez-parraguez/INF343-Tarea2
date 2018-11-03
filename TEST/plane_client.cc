#include <iostream>
#include <memory>
#include <string>

#include <grpcpp/grpcpp.h>
#include "planecontrol.grpc.pb.h"

using grpc::Channel;
using grpc::ChannelArguments;
using grpc::ClientContext;
using grpc::Status;
using tareados::LandRequest;
using tareados::LandResponse;
using tareados::TakeoffRequest;
using tareados::TakeoffResponse;
using tareados::InfoRequest;
using tareados::InfoResponse;
using tareados::PlaneControlService;
using tareados::plane;

class PlaneClient {
 public:
  PlaneClient(std::shared_ptr<Channel> channel)
      : stub_(PlaneControlService::NewStub(channel)) {}

  // Assembles the client's payload, sends it and presents the response back
  // from the server.
  std::string TakeoffReq() {
    // Data we are sending to the server.
    TakeoffRequest request;
    int currentFuel(request.fuel());

    // Container for the data we expect from the server.
    TakeoffResponse reply;

    // Context for the client. It could be used to convey extra information to
    // the server and/or tweak certain RPC behaviors.
    ClientContext context;

    // The actual RPC.
    Status status = stub_->Takeoff(&context, request, &reply);

    // Act upon its status.
    if (status.ok()) {
      return std::to_string(reply.runway());
    } else {
      std::cout << status.error_code() << ": " << status.error_message()
                << std::endl;
      return "RPC failed";
    }
  }

 private:
  std::unique_ptr<PlaneControlService::Stub> stub_;
};

int main(int argc, char** argv) {
  // Instantiate the client. It requires a channel, out of which the actual RPCs
  // are created. This channel models a connection to an endpoint (in this case,
  // localhost at port 50051). We indicate that the channel isn't authenticated
  // (use of InsecureChannelCredentials()).
  PlaneClient plane(grpc::CreateChannel(
      "localhost:50051", grpc::InsecureChannelCredentials()));
  std::string reply = plane.TakeoffReq();
  std::cout << "Plane received: " << reply << std::endl;

  return 0;
}