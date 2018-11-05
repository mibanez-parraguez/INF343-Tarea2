#include <iostream>
#include <memory>
#include <string>

#include <grpc/grpc.h>
#include <grpcpp/server.h>
#include <grpcpp/server_builder.h>
#include <grpcpp/server_context.h>
#include <grpcpp/security/server_credentials.h>
#include "planecontrol.grpc.pb.h"

using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::ServerReader;
using grpc::ServerReaderWriter;
using grpc::ServerWriter;
using grpc::Status;
using planecontrol::LandRequest;
using planecontrol::LandResponse;
using planecontrol::TakeoffRequest;
using planecontrol::TakeoffResponse;
using planecontrol::InfoRequest;
using planecontrol::InfoResponse;
using planecontrol::PlaneControlService;

class PlaneControlServiceImpl final : public PlaneControlService::Service {
	Status Takeoff(ServerContext* context, const TakeoffRequest* request, TakeoffResponse* response) override  {
		response->set_runway(1);
		return Status::OK;
	}
}

void RunServer() {
  std::string server_address("0.0.0.0:50051");
  PlaneControlServiceImpl service();

  ServerBuilder builder;
  builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
  builder.RegisterService(&service);
  std::unique_ptr<Server> server(builder.BuildAndStart());
  std::cout << "Server listening on " << server_address << std::endl;
  server->Wait();
}

int main(int argc, char** argv) {
  RunServer();

  return 0;
}