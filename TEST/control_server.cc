#include <iostream>
#include <memory>
#include <string>
#include <atomic>
#include <thread>
#include <sstream>
#include <limits>
#include <list>
#include <mutex>
#include <queue>

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
using tareados::Plane;

std::mutex serverOK;

// Clase torre de control
class ControlTower {
  public:
    std::string name;
    std::string address;
    int arrivalRunway;
    int departureRunway;
};

// Clase destinos del aeropuerto
class Dest {
  public:
    // Constructor
    Dest(std::string name, std::string address) {
      this->name = name;
      this->address = address;
    }
    ~Dest() {}

    std::string getName() {
      return this->name;
    }

    std::string getAddress() {
      return this->address;
    }

    std::string getPlane() {
      return this->assignedPlane->destaddress();
    }

    void setPlane() {
      this->assignedPlane = new Plane();
      this->assignedPlane->set_destaddress(this->address);
    }

  private:
    std::string name;
    std::string address;
    Plane *assignedPlane; //avion asignado a este destino
};

ControlTower ct;                  // atributos de ésta torre de control
std::list<Dest> destList;         // lista de destinos agregados por el usuario
std::queue<Plane> arrivalQueue;   // cola de aviones que quieren aterrizar
std::queue<Plane> departureQueue; // cola de aviones que quieren despegar

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

//Request que un avion hace para despegar (departure)
class CallTakeoff : public WaitRequest {
  public:
    CallTakeoff(PlaneControlService::AsyncService* service, ServerCompletionQueue* cq) : WaitRequest(service, cq), responder_(&ctx_) {
      service->RequestTakeoff(&ctx_, &request_, &responder_, cq, cq, this);
    }

    void Process() override {
      new CallTakeoff(service_, cq_);
      std::cout << "\n[Torre de control - " << ct.name << "] Avión " << request_.plane().planenumber() << " quiere despegar" << std::endl; 
      
      reply_.set_runway(666);
      responder_.Finish(reply_, Status::OK, this);
    }

  private:
    ServerContext ctx_;
    TakeoffRequest request_;
    TakeoffResponse reply_;
    ServerAsyncResponseWriter<TakeoffResponse> responder_;
};

//Request que un avion hace para aterrizar (arrival)
class CallLand : public WaitRequest {
  public:
    CallLand(PlaneControlService::AsyncService* service, ServerCompletionQueue* cq) : WaitRequest(service, cq), responder_(&ctx_) {
      service->RequestLand(&ctx_, &request_, &responder_, cq, cq, this);
    }

    void Process() override {
      new CallLand(service_, cq_);
      std::cout << "\n[Torre de control - " << ct.name << "] Avión " << request_.plane().planenumber() << " quiere aterrizar" << std::endl; 
      reply_.set_runway(666);
      responder_.Finish(reply_, Status::OK, this);
    }

  private:
    ServerContext ctx_;
    LandRequest request_;
    LandResponse reply_;
    ServerAsyncResponseWriter<LandResponse> responder_;
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
    std::string address;
    while(address.empty() || !server_) {
      std::cout << "Ingrese dirección IP de la torre de control: ";
      getline(std::cin, address);
      std::string server_address(/*address+":50051"*/"0.0.0.0:50051");

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
      if (server_) {
        std::cout << "Server listening on " << server_address << std::endl;
        serverOK.unlock(); //Desbloquea el thread que maneja el input de usuario
        break;
      } else {
        std::cout << "Direccion inválida, intente otra vez \n";
      }
    }
    // Proceed to the server's main loop.
    HandleRpcs();
  }

private:
  // This can be run in multiple threads if needed.
  void HandleRpcs() {
    // Spawn a new WaitRequest instance to serve new clients.
    new CallTakeoff(&service_, cq_.get());
    new CallLand(&service_, cq_.get());
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

// Funcion que acepta el input del usuario
void WaitInput(std::atomic<bool> &run) {
  serverOK.lock(); // Input de usuario bloqueado hasta que el servidor este funcionando correctamente
  std::cout << "Bienvenido a la Torre de control\n";
  std::string buffer;
  while (buffer.empty()){
    std::cout << "[Torre de control] Nombre del Aeropuerto:\n";
    getline(std::cin, buffer);
  }
  std::string name(buffer);
  ct.name = name;
  int nDepartureRunway = 0;
  while (nDepartureRunway == 0) {
    std::cout << "[Torre de control - " << name << "]" << " Cantidad de pistas de aterrizaje:\n";
    std::cin >> nDepartureRunway;
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
  }
  ct.departureRunway = nDepartureRunway;
  int nArrivalRunway = 0;
  while (nArrivalRunway == 0) {
    std::cout << "[Torre de control - " << name << "]" << " Cantidad de pistas de despegue:\n";
    std::cin >> nArrivalRunway;
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
  }
  ct.arrivalRunway = nArrivalRunway;
  std::string destName;
  std::string destAddress;
  while (run.load()) {
    std::cout << "Para agregar destino presione enter";
    if (std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n')) {
      std::cout << "Ingrese nombre y direccion IP del destino:" << std::endl;
      while(getline(std::cin, buffer) && !buffer.empty()) {
        if (buffer == "exit") {
          run.store(false);
          return;
        }
        std::stringstream ss(buffer);
        ss >> destName >> destAddress;
        if (!ss) {
          std::cout << "Destino inválido" << std::endl;
          break;
        } else {
          std::cout << "Destino agregado con éxito" << std::endl;
          // Se llena la lista de destinos
          Dest *dest = new Dest(destName, destAddress);
          destList.push_back(*dest);
          for (Dest n : destList)
          {
            n.setPlane();
            std::cout << n.getName() << " : " << n.getAddress() << " : " << n.getPlane() << '\n';
          }
          break;
        }
      } 
    }
  }
}

int main(int argc, char** argv) {
  PlaneControlServiceImpl server;
  std::atomic<bool> run(true);
  serverOK.lock();
  std::thread userInputThread(WaitInput, std::ref(run));
  server.Run();
  run.store(false);
  userInputThread.join();
  return 0;
}