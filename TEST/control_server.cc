#include <iostream>
#include <memory>
#include <string>
#include <atomic>
#include <thread>

#include <grpcpp/grpcpp.h>
#include <grpc/support/log.h>
#include "planecontrol.grpc.pb.h"

using grpc::Server;
using grpc::ServerAsyncResponseWriter;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::ServerCompletionQueue;
using grpc::Status;
using tareados::LandRequest;
using tareados::LandResponse;
using tareados::TakeoffRequest;
using tareados::TakeoffResponse;
using tareados::InfoRequest;
using tareados::InfoResponse;
using tareados::PlaneControlService;

// Clase base de request, todos los request son hijos de esta clase e implementan la funcion Process()
class WaitRequest {
   public:
    //Constructor
    WaitRequest(PlaneControlService::AsyncService* service, ServerCompletionQueue* cq)
        : service_(service), cq_(cq), status_(PROCESS) {}
    //Destructor
    virtual ~WaitRequest() {}

    //Funcion para procesar request
    void Proceed() {
      if (status_ == PROCESS) {
        status_ = FINISH;
        Process(); //Procesar el request
      } else {
        GPR_ASSERT(status_ == FINISH);
        delete this;
      }
    }

  protected:
    virtual void Process() = 0; //Procesar vacio
    PlaneControlService::AsyncService* service_;
    ServerCompletionQueue* cq_;

  private:
    enum CallStatus { PROCESS, FINISH };
    CallStatus status_;
};

/*
  User input request
*/

//Request que un avion hace para hacer takeoff (departure)
class CallNewDeparture : public WaitRequest {
  public:
    CallNewDeparture(PlaneControlService::AsyncService* service, ServerCompletionQueue* cq) : WaitRequest(service, cq), responder_(&ctx_) {
      service->RequestTakeoff(&ctx_, &request_, &responder_, cq, cq, this);
    }

    void Process() override {
      new CallNewDeparture(service_, cq_);
      reply_.set_runway(666);
      responder_.Finish(reply_, Status::OK, this);
    }

  private:
    ServerContext ctx_;
    TakeoffRequest request_;
    TakeoffResponse reply_;
    ServerAsyncResponseWriter<TakeoffResponse> responder_;
};

//Request que un avion hace para hacer takeoff (departure)
class CallTakeoffReq : public WaitRequest {
  public:
    CallTakeoffReq(PlaneControlService::AsyncService* service, ServerCompletionQueue* cq) : WaitRequest(service, cq), responder_(&ctx_) {
      service->RequestTakeoff(&ctx_, &request_, &responder_, cq, cq, this);
    }

    void Process() override {
      new CallTakeoffReq(service_, cq_);
      reply_.set_runway(666);
      responder_.Finish(reply_, Status::OK, this);
    }

  private:
    ServerContext ctx_;
    TakeoffRequest request_;
    TakeoffResponse reply_;
    ServerAsyncResponseWriter<TakeoffResponse> responder_;
};

class PlaneControlServiceImpl final : public PlaneControlService::Service {
 public:
  ~PlaneControlServiceImpl() {
    server_->Shutdown();
    // Always shutdown the completion queue after the server.
    cq_->Shutdown();
  }

  // There is no shutdown handling in this code.
  void Run() {
    std::string server_address("0.0.0.0:50051");

    ServerBuilder builder;
    // Listen on the given address without any authentication mechanism.
    builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
    // Register "service_" as the instance through which we'll communicate with
    // clients. In this case it corresponds to an *asynchronous* service.
    builder.RegisterService(&service_);
    // Get hold of the completion queue used for the asynchronous communication
    // with the gRPC runtime.
    cq_ = builder.AddCompletionQueue();
    // Finally assemble the server.
    server_ = builder.BuildAndStart();
    std::cout << "Server listening on " << server_address << std::endl;

    // Proceed to the server's main loop.
    HandleRpcs();
  }

private:
  // This can be run in multiple threads if needed.
  void HandleRpcs() {
    // Spawn a new WaitRequest instance to serve new clients.
    new CallTakeoffReq(&service_, cq_.get());
    void* tag;  // uniquely identifies a request.
    bool ok;
    while (true) {
      // Block waiting to read the next event from the completion queue. The
      // event is uniquely identified by its tag, which in this case is the
      // memory address of a WaitRequest instance.
      // The return value of Next should always be checked. This return value
      // tells us whether there is any kind of event or cq_ is shutting down.
      GPR_ASSERT(cq_->Next(&tag, &ok));
      GPR_ASSERT(ok);
      static_cast<WaitRequest*>(tag)->Proceed();
    }
  }

  std::unique_ptr<ServerCompletionQueue> cq_;
  PlaneControlService::AsyncService service_;
  std::unique_ptr<Server> server_;
};

void WaitInput(std::atomic<bool> &run) {
  std::cout << "Bienvenido a la Torre de control\n";
  std::string buffer;
  while (buffer.empty()){
    std::cout << "[Torre de control] Nombre del Aeropuerto:\n";
    getline(std::cin, buffer);
  }
  std::string nombre(buffer);
  int nDepartureRunway = 0;
  while (nDepartureRunway == 0) {
    std::cout << "[Torre de control - " << nombre << "]" << " Cantidad de pistas de aterrizaje:\n";
    std::cin >> nDepartureRunway;
  }
  int nArrivalRunway = 0;
  while (nArrivalRunway == 0) {
    std::cout << "[Torre de control - " << nombre << "]" << " Cantidad de pistas de despegue:\n";
    std::cin >> nArrivalRunway;
  }
  std::cout << "Para agregar destino presione enter";
  while (run.load()) {
    while(getline(std::cin, buffer)) {
      if (buffer == "exit") {
        run.store(false);
        return;
      } else if (buffer == "\n") {
        std::cout << "Ingrese nombre y direccion IP del destino:" << std::endl;
      } 
    }
  }
}

int main(int argc, char** argv) {
  PlaneControlServiceImpl server;
  std::atomic<bool> run(true);
  std::thread userInputThread(WaitInput, std::ref(run));
  server.Run();
  run.store(false);
  userInputThread.join();
  return 0;
}

/* OLD */

// class CallTakeoffReq {
//  public:
//   // Take in the "service" instance (in this case representing an asynchronous
//   // server) and the completion queue "cq" used for asynchronous communication
//   // with the gRPC runtime.
//   CallTakeoffReq(PlaneControlService::AsyncService* service, ServerCompletionQueue* cq)
//       : service_(service), cq_(cq), responder_(&ctx_), status_(CREATE) {
//     // Invoke the serving logic right away.
//     Proceed();
//   }

//   void Proceed() {
//     if (status_ == CREATE) {
//       // Make this instance progress to the PROCESS state.
//       status_ = PROCESS;

//       // As part of the initial CREATE state, we *request* that the system
//       // start processing SayHello requests. In this request, "this" acts are
//       // the tag uniquely identifying the request (so that different CallTakeoffReq
//       // instances can serve different requests concurrently), in this case
//       // the memory address of this CallTakeoffReq instance.
//       service_->RequestTakeoff(&ctx_, &request_, &responder_, cq_, cq_,
//                                 this);
//     } else if (status_ == PROCESS) {
//       // Spawn a new CallTakeoffReq instance to serve new clients while we process
//       // the one for this CallTakeoffReq. The instance will deallocate itself as
//       // part of its FINISH state.
//       new CallTakeoffReq(service_, cq_);

//       // The actual processing.
//       reply_.set_runway(5);

//       // And we are done! Let the gRPC runtime know we've finished, using the
//       // memory address of this instance as the uniquely identifying tag for
//       // the event.
//       status_ = FINISH;
//       responder_.Finish(reply_, Status::OK, this);
//     } else {
//       GPR_ASSERT(status_ == FINISH);
//       // Once in the FINISH state, deallocate ourselves (CallTakeoffReq).
//       delete this;
//     }
//   }

//  private:
//   // The means of communication with the gRPC runtime for an asynchronous
//   // server.
//   PlaneControlService::AsyncService* service_;
//   // The producer-consumer queue where for asynchronous server notifications.
//   ServerCompletionQueue* cq_;
//   // Context for the rpc, allowing to tweak aspects of it such as the use
//   // of compression, authentication, as well as to send metadata back to the
//   // client.
//   ServerContext ctx_;
// //     ServerContext ctx_;
//   TakeoffRequest request_;
//   TakeoffResponse reply_;
//   ServerAsyncResponseWriter<TakeoffResponse> responder_;

//   // Let's implement a tiny state machine with the following states.
//   enum CallStatus { CREATE, PROCESS, FINISH };
//   CallStatus status_;  // The current serving state.
// };


// class PlaneControlServiceImpl final : public PlaneControlService::Service {
//  public:
//   ~PlaneControlServiceImpl() {
//     server_->Shutdown();
//     // Always shutdown the completion queue after the server.
//     cq_->Shutdown();
//   }

//   // There is no shutdown handling in this code.
//   void Run() {
//     std::string server_address("0.0.0.0:50051");

//     ServerBuilder builder;
//     // Listen on the given address without any authentication mechanism.
//     builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
//     // Register "service_" as the instance through which we'll communicate with
//     // clients. In this case it corresponds to an *asynchronous* service.
//     builder.RegisterService(&service_);
//     // Get hold of the completion queue used for the asynchronous communication
//     // with the gRPC runtime.
//     cq_ = builder.AddCompletionQueue();
//     // Finally assemble the server.
//     server_ = builder.BuildAndStart();
//     std::cout << "Server listening on " << server_address << std::endl;

//     // Proceed to the server's main loop.
//     HandleRpcs();
//   }

// private:
//   // This can be run in multiple threads if needed.
//   void HandleRpcs() {
//     // Spawn a new WaitRequest instance to serve new clients.
//     new CallTakeoffReq(&service_, cq_.get());
//     void* tag;  // uniquely identifies a request.
//     bool ok;
//     while (true) {
//       // Block waiting to read the next event from the completion queue. The
//       // event is uniquely identified by its tag, which in this case is the
//       // memory address of a WaitRequest instance.
//       // The return value of Next should always be checked. This return value
//       // tells us whether there is any kind of event or cq_ is shutting down.
//       GPR_ASSERT(cq_->Next(&tag, &ok));
//       GPR_ASSERT(ok);
//       static_cast<CallTakeoffReq*>(tag)->Proceed();
//     }
//   }

//   std::unique_ptr<ServerCompletionQueue> cq_;
//   PlaneControlService::AsyncService service_;
//   std::unique_ptr<Server> server_;
// };


// int main(int argc, char** argv) {
//   PlaneControlServiceImpl server;
//   server.Run();

//   return 0;
// }