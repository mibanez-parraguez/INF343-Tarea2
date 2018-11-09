#include <iostream>
#include <memory>
#include <string>
#include <thread>
#include <sstream>

#include <grpc/grpc.h>
#include <grpcpp/channel.h>
#include <grpcpp/client_context.h>
#include <grpcpp/create_channel.h>
#include <grpcpp/security/credentials.h>
#include "planecontrol.grpc.pb.h"

using grpc::Channel;
using grpc::ClientContext;
using grpc::ClientReader;
using grpc::ClientReaderWriter;
using grpc::ClientWriter;
using grpc::Status;
using tareados::LandRequest;
using tareados::LandResponse;
using tareados::TakeoffRequest;
using tareados::TakeoffResponse;
using tareados::PlaneControlService;
using tareados::PlaneMsge;

PlaneMsge MakePlane(std::string airline, std::string planeNumber, int maxLoad, int maxCapacity, std::string sourceAddress) {
  PlaneMsge plane;
  plane.set_airline(airline);
  plane.set_planenumber(planeNumber);
  plane.set_maxload(maxLoad);
  plane.set_currload(0);
  plane.set_maxcapacity(maxCapacity);
  plane.set_currcapacity(0);
  plane.set_sourceaddress(sourceAddress);
  plane.set_runway(-1);
  plane.set_time("horaaa");
  return plane;
}

// static std::string airline("LAN");
// static std::string planeNumber("CLB1234");
static std::string sourceAddress("0.0.0.0");
// static std::string destName("USM");
// static int maxLoad(100000);
// static int maxCapacity(200);

PlaneMsge plane;
// MakePlane(airline, planeNumber, maxLoad, maxCapacity, sourceAddress);

class PlaneClient {
 public:
  PlaneClient(std::shared_ptr<Channel> channel)
      : stub_(PlaneControlService::NewStub(channel)) {}

  // Assembles the client's payload, sends it and presents the response back
  // from the server.
  void LandReq() {
    ClientContext context;

    std::shared_ptr<ClientReaderWriter<LandRequest, LandResponse> > stream(
        stub_->Land(&context));

    std::thread writer([stream]() {
      LandRequest lr;
      lr.set_dest(sourceAddress);
      lr.mutable_plane()->CopyFrom(plane);
      stream->Write(lr);
    });
    std::cout << "[Avion - " << plane.planenumber() << "] Esperando pista de aterrizaje..." << std::endl;

    LandResponse towerResponse;
    while (stream->Read(&towerResponse)) {
      if (towerResponse.runway() != 0) {
        std::cout << "[Avion - " << plane.planenumber() << "] Aterrizando en la pista " << towerResponse.runway() << "..." << std::endl;
        LandRequest lr;
        plane.set_runway(towerResponse.runway());
        std::thread writer2([stream, towerResponse]() {
          LandRequest lr;
          plane.set_destname(towerResponse.destname());
          lr.mutable_plane()->CopyFrom(plane);
          stream->Write(lr);
        });
        writer2.join();
        stream->WritesDone();
      } else if (towerResponse.runway() == 0){
        std::cout << "[Avion - " << plane.planenumber() << "] Todas las pistas ocupadas, encolando avión." << std::endl; 
      }
    }
    writer.join();
    Status status = stream->Finish();
    if (!status.ok()) {
      std::cout << "Comunicación rpc fallida." << std::endl;
    }
  }

  std::string TakeoffReq() {
    ClientContext context;

    std::shared_ptr<ClientReaderWriter<TakeoffRequest, TakeoffResponse> > stream(
        stub_->Takeoff(&context));

    // TakeoffResponse towerResponse;
    // while(stream->Read(&towerResponse)) {
    //   if (towerResponse.dest() == tr.dest()) {
    //     std::cout << "Pasando por el Gate..."  << std::endl;
    //   }
    // }
    // Thread que maneja el envio de destino
    static bool desOK = false;
    static bool restrOK = false;
    static bool instOK = false;
    static bool runwayOK = false;
    static bool finish = false;
    std::thread writer([stream]() {
      TakeoffRequest tr;
      std::string dest;
      while (dest.empty()){
        std::cout << "[Avion] Ingrese destino:\n";
        getline(std::cin, dest);
      }
      tr.set_dest(dest);
      plane.set_destname(dest);
      tr.set_instok(false);
      tr.set_flyok(false);
      tr.mutable_plane()->CopyFrom(plane);
      stream->Write(tr);
    });
    writer.join();

    TakeoffResponse towerResponse;
    while (stream->Read(&towerResponse)) {
      //Si el destino es correcto
      if (towerResponse.destok() && !desOK) {
        plane.set_destname(towerResponse.destname());
        std::cout << "[Avion - " << plane.planenumber() << "] Pasando por el Gate..."  << std::endl;
        desOK = true;
      } 
      //Si el destino es incorrecto
      else if (!towerResponse.destok() && !restrOK){
        std::cout << "[Avion - " << plane.planenumber() << "] Destino no existente en el aeropuerto." << std::endl;
        std::thread writer2([stream]() {
          TakeoffRequest tr;
          std::string dest;
          while (dest.empty()){
            std::cout << "[Avion - " << plane.planenumber() << "] Ingrese destino:\n";
            getline(std::cin, dest);
          }
          tr.set_dest(dest);
          plane.set_destname(dest);
          tr.mutable_plane()->CopyFrom(plane);
          stream->Write(tr);
        });
        writer2.join();
      } 
      //Si se cumplen las restricciones de combustible y pasajeros
      else if (desOK && towerResponse.restrok() && !restrOK) {
        std::cout << "[Avion - " << plane.planenumber() << "] Todos los pasajeros a bordo y combustible cargado."  << std::endl;
        restrOK = true;
      } 
      //Si se debe llenar el estanque de combustible y/o esperar pasajeros
      else if (desOK && !restrOK) {
        std::cout << "[Avion - " << plane.planenumber() << "] Llenando combustible y/o esperando pasajeros..."  << std::endl;
        std::thread writer3([stream]() {
          TakeoffRequest tr;
          plane.set_currload(plane.maxload());
          plane.set_currcapacity(plane.maxcapacity());
          tr.mutable_plane()->CopyFrom(plane);
          stream->Write(tr);
        });
        writer3.join();
      }
      //Se revisa si hay pistas disponibles
      else if (desOK && restrOK && instOK && towerResponse.runway() != 0 && !finish) {
        std::cout << "[Avion - " << plane.planenumber() << "] Pista " << towerResponse.runway() << " asignada y altura de "  << towerResponse.altitude() << "." << std::endl;
        plane.set_runway(towerResponse.runway());
        runwayOK = true;
      } 
      else if (desOK && restrOK && instOK && towerResponse.runway() == 0){
        if (towerResponse.queuepos() != -1) {
          std::cout << "[Avion - " << plane.planenumber() << "] Todas las pistas están ocupadas, avión predecesor es: " << towerResponse.prevplane() << std::endl; 
        } else {
          std::cout << "[Avion - " << plane.planenumber() << "] Todas las pistas están ocupadas, no hay más aviones en cola " << std::endl; 
        }
      } 
      /* SE ESPERA ENTER */
      //Se piden las instrucciones
      if (desOK && restrOK && towerResponse.instok() && !instOK) {
        std::cout << "[Avion - " << plane.planenumber() << "] Instrucciones recibidas..."  << std::endl;
        instOK = true;
      } 
      else if (desOK && restrOK && !instOK) {
        std::cout << "[Avion - " << plane.planenumber() << "] Pidiendo instrucciones para despegar...";
        while(true) {
          if (std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n')){
            std::thread writer4([stream]() {
              TakeoffRequest tr;
              tr.set_instok(true);
              tr.mutable_plane()->CopyFrom(plane);
              stream->Write(tr);
            });
            writer4.join();
            break;
          }
        }
      }
      //Avion listo para despegar, se espera enter
      if (desOK && restrOK && instOK && runwayOK && !finish) {
        std::cout << "[Avion - " << plane.planenumber() << "] Despegar?...";
        while (true) {
          if (std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n')){
            std::thread writer5([stream]() {
              TakeoffRequest tr;
              tr.set_flyok(true);
              tr.mutable_plane()->CopyFrom(plane);
              stream->Write(tr);
            });
            writer5.join();
            finish = true;
            break;
          }
        }
        finish = true;
        stream->WritesDone();
      }
    }
    std::cout << "[Avion - " << plane.planenumber() << "] Despegando..." << std::endl;
    Status status = stream->Finish();
    if (!status.ok()) {
      std::cout << "Comunicación rpc fallida." << std::endl;
    }
    return plane.destaddress(); //Se retorna la direccion de la proxima torre de control
  }

 private:
  std::unique_ptr<PlaneControlService::Stub> stub_;
  PlaneMsge plane_;
};

int main() {
  // Instantiate the client. It requires a channel, out of which the actual RPCs
  // are created. This channel models a connection to an endpoint (in this case,
  // localhost at port 50051). We indicate that the channel isn't authenticated
  // (use of InsecureChannelCredentials()).

  // planeReq.LandReq();
  std::string airline;
  while (airline.empty()){
    std::cout << "[Avion] airline:\n";
    getline(std::cin, airline);
  }

  std::string planeNumber;
  while (planeNumber.empty()){
    std::cout << "[Avion] planeNumber:\n";
    getline(std::cin, planeNumber);
  }

  int maxLoad = 0;
  while (maxLoad == 0) {
    std::cout << "[Avion] maxLoad:\n";
    std::cin >> maxLoad;
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
  }

  int maxCapacity = 0;
  while (maxCapacity == 0) {
    std::cout << "[Avion] maxCapacity:\n";
    std::cin >> maxCapacity;
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
  }

  // std::string destAddress;
  // while (destAddress.empty()){
  //   std::cout << "[Avion] destAddress:\n";
  //   getline(std::cin, destAddress);
  // }
  std::string destAddress("localhost");
  std::atomic<bool> run(true);

  PlaneClient planeReq(grpc::CreateChannel(
      destAddress+":50051", grpc::InsecureChannelCredentials()));
  plane = MakePlane(airline, planeNumber, maxLoad, maxCapacity, destAddress);
  planeReq.LandReq();
  std::cout << "\nPresione enter para aterrizar";
  while (run.load()) {
    if (std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n')) {
      std::string destination;
      destination = planeReq.TakeoffReq();
      PlaneClient planeReq(grpc::CreateChannel(
          destination+":50051", grpc::InsecureChannelCredentials()));
      planeReq.LandReq();
      std::cout << "\nPresione enter para aterrizar";
    }
  }

  return 0;
}