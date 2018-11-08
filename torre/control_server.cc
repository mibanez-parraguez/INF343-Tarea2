#include <iostream>
#include <memory>
#include <string>
#include <atomic>
#include <thread>
#include <limits>
#include <list>
#include <sstream>
#include <mutex>
#include <deque>
#include <algorithm>

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
using tareados::LandRequest;
using tareados::LandResponse;
using tareados::TakeoffRequest;
using tareados::TakeoffResponse;
using tareados::PlaneControlService;
using tareados::PlaneMsge;
using tareados::InfoRequest;
using tareados::InfoResponse;

std::mutex serverOK;
std::mutex inputOK;
std::string serverAddress;

// Clase torre de control
class ControlTower {
  public:
    std::string name;
    std::string address;
    // int arrivalRunway;
    // int departureRunway;
    std::map<int, bool> arrivalRunway;
    std::map<int, bool> departureRunway;
};

// Clase destinos del aeropuerto
class Dest {
  public:
    // Constructor
    Dest(std::string name, std::string address) {
      this->name = name;
      this->address = address;
      // this->assignedPlaneMsge = NULL;
    }
    ~Dest() {}

    std::string getName() {
      return this->name;
    }

    std::string getAddress() {
      return this->address;
    }

    // std::string getPlaneMsge() {
    //   return this->assignedPlaneMsge->destaddress();
    // }

    // void setPlaneMsge() {
    //   this->assignedPlaneMsge = new PlaneMsge();
    //   this->assignedPlaneMsge->set_destaddress(this->address);
    // }

  private:
    std::string name;
    std::string address;
    // PlaneMsge *assignedPlaneMsge; //avion asignado a este destino
};

ControlTower ct;                                // atributos de ésta torre de control
std::list<Dest> destList;                       // lista de destinos agregados por el usuario
std::deque<PlaneMsge> arrivalDeque;                 // cola de aviones que quieren aterrizar
std::deque<PlaneMsge> departureDeque;               // cola de aviones que quieren despegar
std::vector<std::string> firstArrival;          // nombre del primer avion en la cola de aterrizaje
std::vector<std::string> firstDeparture;        // nombre del primer avion en la cola de despegue
std::map<int, bool> heightMap;                  // Hash que guarda la altura y si esta disponible en el espacio aéreo del aeropuerto
std::vector<PlaneMsge> arrivalPlaneMsge;                 // vector de aviones que en las pistas de aterrizaje
std::vector<PlaneMsge> departurePlaneMsge;               // vector de aviones que en las pistas de despegue
bool runwayFreed = false;
int minHeight = 5;
int maxHeight = 100;

/* Helpers */

// Revisa si existe una pista disponible del tipo arrival (0) o departure (1)
// Retorna 0 si no hay pista disponible y el número de la pista disponible si existe
int checkAvailability(int type) {
  int freeRunway = 0;
  if (type == 0) {
    //Se revisan todas las pistas
    for (std::pair<int, bool> runway : ct.arrivalRunway) {
      //Si encuentra una pista libre, se retorna
      if (runway.second == false) {
        freeRunway = runway.first; //Se retornara el numero de la pista
        //Se marca la pista como ocupda
        ct.arrivalRunway[freeRunway] = true;
        break;
      }
    }
  } else if (type == 1) {
    for (std::pair<int, bool> runway : ct.departureRunway) {
      if (runway.second == false) {
        freeRunway = runway.first;
        ct.departureRunway[freeRunway] = true;
        break;
      }
    }
  } else {
    std::cout << "Error en el tipo de cola." << std::endl;
    return -1;
  }
  return freeRunway;
}

//Entrega la primera altura disponible
int checkAvailabilityHeight() {
  int freeHeight = 0;
  for (std::pair<int, bool> height : heightMap) {
    //Si encuentra una altura libre, la elige
    if (height.second == false) {
      freeHeight = height.first;
      heightMap[freeHeight] = true;
      break;
    }
  }
  return freeHeight;
}

/* Requests */

class PlaneControlServiceImpl final : public PlaneControlService::Service {
	Status Land(ServerContext* context,
                   ServerReaderWriter<LandResponse, LandRequest>* stream) override {
    LandRequest lr;
    LandResponse towerResponse;
    // Mientras el stream exista se leera
    while (stream->Read(&lr)) {
      std::cout << "\n[Torre de control - " << ct.name << "] Nuevo Avión " << lr.plane().planenumber() << " en el Aeropuerto" << std::endl; 
      std::cout << "[Torre de control - " << ct.name << "] Asignando pista de aterrizaje..." << std::endl;
      // Se revisa disponibilidad de pistas de aterrizaje
      int availableRunway = checkAvailability(0);
      // Si existe una pista disponible entonce se asigna el avion
      if (availableRunway != 0) {
        std::cout << "[Torre de control - " << ct.name << "] La pista de aterrizaje asignada es la " << availableRunway << std::endl;
        towerResponse.set_runway(availableRunway);
        stream->Write(towerResponse);
        arrivalPlaneMsge.push_back(lr.plane());
      } 
      // Si no hay pistas de aterrizaje disponibles entonces el avion se envia a la cola de espera hasta que se disponga una pista
      else {
        std::cout << "[Torre de control - " << ct.name << "] Todas las pistas están ocupadas, encolando avión." << std::endl;
        arrivalDeque.push_back(lr.plane());
        int availableHeight = checkAvailabilityHeight(); //Retorna la primera altura disponible
        towerResponse.set_altitude(availableHeight);
        stream->Write(towerResponse);
        firstArrival.push_back(lr.plane().planenumber());
        // Request queda en espera hasta que se libere un espacio
        while (true) {
          if (runwayFreed && firstArrival.front() == arrivalDeque.front().planenumber()) {
            // Se revisa disponibilidad de pistas de aterrizaje
            int availableRunway = checkAvailability(0);
            // Si existe una pista disponible entonce se asigna el avion
            if (availableRunway != 0) {
              std::cout << "[Torre de control - " << ct.name << "] Pista de aterrizaje " << availableRunway << " ha sido desocupada." << std::endl;
              std::cout << "[Torre de control - " << ct.name << "] La pista de aterrizaje asignada al avión " << arrivalDeque.front().planenumber() << " es la " << availableRunway << std::endl;
              towerResponse.set_runway(availableRunway);
              stream->Write(towerResponse);
              runwayFreed = false;
              firstArrival.pop_back();
              arrivalDeque.pop_front();
              heightMap[availableHeight] = false; // Deja libre el espacio aereo ocupado
              arrivalPlaneMsge.push_back(lr.plane());
              return Status::OK;
            } 
          }
        }
      }
    }
    return Status::OK;
  }

  Status Takeoff(ServerContext* context,
                   ServerReaderWriter<TakeoffResponse, TakeoffRequest>* stream) override {
    TakeoffRequest tr;
    TakeoffResponse towerResponse;
    bool desOK = false;     //Para el ciclo de preguntar destino
    bool restrOK = false;   //Para el ciclo de preguntar restricciones
    bool instOK = false;     //Para enviar instrucciones
    bool runwayOK = false;  //Para el ciclo de espera de pista disponible
    int freeRunway;         //Pista que se libera al despegar el avion
    while (stream->Read(&tr)) {
      if (!desOK) {
        std::cout << "\n[Torre de control - " << ct.name << "] Avión " << tr.plane().planenumber() << " quiere despegar" << std::endl; 
        // Mientras el stream exista se leera
        std::cout << "[Torre de control - " << ct.name << "] Consultando destino..." << std::endl;
      }
      // Se espera a que el avion responda con un destino existente en la lista de destinos de la torre de control
      if (!desOK) {
        for (Dest dest : destList) {
          if (dest.getName() == tr.dest()) {
            // dest.setPlaneMsge();
            desOK = true;
            std::cout << "[Torre de control - " << ct.name << "] Enviando dirección de " << tr.dest() << std::endl;
            towerResponse.set_destok(true);
            stream->Write(towerResponse);
            std::cout << "[Torre de control - " << ct.name << "] Consultando restricciones de pasajeros y combustible." << std::endl;
          }
        }
        if (!desOK)  {
          std::cout << "[Torre de control - " << ct.name << "] Avión " << tr.plane().planenumber() << " ha ingresado un destino inexistente." << std::endl;
          towerResponse.set_destok(false);
          stream->Write(towerResponse);
        }
      }
      // Se espera a que el avión cumpla con las restricciones
      if (desOK && !restrOK) {
        // El avion debe tener un peso menor al maximo y tener la cantidad maxima de combustible
        if ((tr.plane().currload() <= tr.plane().maxload()) && (tr.plane().currcapacity() == tr.plane().maxcapacity())) {
          restrOK = true;
          std::cout << "[Torre de control - " << ct.name << "] Avión cumple con las restricciones de pasajeros y combustible" << std::endl;
          towerResponse.set_restrok(true);
          stream->Write(towerResponse);
        } else {
          std::cout << "[Torre de control - " << ct.name << "] Avión no cumple con las restricciones de pasajeros y combustible" << std::endl;
          towerResponse.set_restrok(false);
          stream->Write(towerResponse);
        }
      }

      //Se espera instrucciones (presionar enter para despegar)
      if (desOK && restrOK && tr.instok() && !instOK) {
        std::cout << "[Torre de control - " << ct.name << "] Enviando instrucciones..."  << std::endl;
        instOK = true;
        towerResponse.set_instok(true);
        stream->Write(towerResponse);
      } 

      //Se espera una pista de despegue disponible
      if(desOK && restrOK && instOK && !runwayOK) {
        // Se revisa disponibilidad de pistas de despegue
        int availableRunway = checkAvailability(1);
        int availableHeight = checkAvailabilityHeight();
        // Si existe una pista disponible entonce se asigna el avion
        if (availableRunway != 0) {
          std::cout << "[Torre de control - " << ct.name << "] La pista de despegue asignada al avión " << tr.plane().planenumber() << " es la " << availableRunway << " y la altura " << availableHeight << std::endl;
          towerResponse.set_runway(availableRunway);
          towerResponse.set_altitude(availableHeight);
          stream->Write(towerResponse);
          runwayOK = true;
          freeRunway = tr.plane().runway();
          //se añade el avion a la pista de despegue
          departurePlaneMsge.push_back(tr.plane());
          //Se elimina el avión de la pista de aterrizaje
          for (unsigned int i = 0; i < arrivalPlaneMsge.size(); ++i) {
            if (arrivalPlaneMsge[i].planenumber() == tr.plane().planenumber()) {
              arrivalPlaneMsge.erase(arrivalPlaneMsge.begin()+i);
            }
          }
        } 
        // Si no hay pistas de aterrizaje disponibles entonces el avion se envia a la cola de espera hasta que se disponga una pista
        else {
          std::cout << "[Torre de control - " << ct.name << "] Todas las pistas están ocupadas, encolando avión." << std::endl;
          departureDeque.push_back(tr.plane());
          if (departureDeque.size() > 1) {
            for (unsigned int i = 0; i < departureDeque.size(); ++i) {
              if (departureDeque[i].planenumber() == tr.plane().planenumber()) {
                std::string prevPlaneMsge(departureDeque[i-1].planenumber());
                towerResponse.set_prevplane(prevPlaneMsge);
                towerResponse.set_queuepos(i);
              }
            }
          } else {
            towerResponse.set_queuepos(-1);
          }
          stream->Write(towerResponse);
        }
      }
    }
    ct.arrivalRunway[freeRunway] = false; // se libera la pista
    // Se revisa si hay aviones en la cola de espera
    if (!arrivalDeque.empty()) {
      firstArrival.clear();
      firstArrival.push_back(arrivalDeque.front().planenumber());
      runwayFreed = true;
      //Se elimina el avión de la pista de despegue
      for (unsigned int i = 0; i < departurePlaneMsge.size(); ++i) {
        if (departurePlaneMsge[i].planenumber() == tr.plane().planenumber()) {
          departurePlaneMsge.erase(departurePlaneMsge.begin()+i);
        }
      }
    }
    return Status::OK;
  }

  Status Info(ServerContext* context, InfoRequest* request, ServerWriter<InfoResponse>* writer) {
    InfoRequest ir;
    std::cout << "something is coming :O " << arrivalPlaneMsge.size() << std::endl;
    std::cout << "something is coming :O " << departurePlaneMsge.size() << std::endl;
    InfoResponse infoRes;
    infoRes.set_control_tower(ct.name);
    for (PlaneMsge p : arrivalPlaneMsge) {
      // infoRes.add_arrivalplane()->CopyFrom(p);
      infoRes.mutable_arrivalplane()->CopyFrom(p);
    }
    for (PlaneMsge p : departurePlaneMsge) {
      // infoRes.add_departureplane()->CopyFrom(p);
      infoRes.mutable_departureplane()->CopyFrom(p);
    }
    writer->Write(infoRes);
    return Status::OK;
  };
};

void RunServer() {
  bool OK = false;
  while(serverAddress.empty() || !OK) {
    std::cout << "Ingrese dirección IP de la torre de control: ";
    getline(std::cin, serverAddress);
    std::string server_address(serverAddress+":50051"/*"0.0.0.0:50051"*/);
    PlaneControlServiceImpl service;

    ServerBuilder builder;
    builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
    builder.RegisterService(&service);
    std::unique_ptr<Server> server(builder.BuildAndStart());
    if (server) {
      OK = true;
      std::cout << "Server listening on " << server_address << std::endl;
      serverOK.unlock(); //Desbloquea el thread que maneja el input de usuario
      server->Wait();
      break;
    } else {
      std::cout << "Direccion inválida, intente otra vez \n";
    }
  }
}

/* Thread */
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
  int nArrivalRunway = 0;
  while (nArrivalRunway == 0) {
    std::cout << "[Torre de control - " << name << "]" << " Cantidad de pistas de aterrizaje:\n";
    std::cin >> nArrivalRunway;
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
  }
  // Se insertan las pistas en la tabla key : value
  for (int i = 1; i <= nArrivalRunway; ++i) {
    ct.arrivalRunway.insert(std::pair<int, bool>(i, false));
  }
  int nDepartureRunway = 0;
  while (nDepartureRunway == 0) {
    std::cout << "[Torre de control - " << name << "]" << " Cantidad de pistas de despegue:\n";
    std::cin >> nDepartureRunway;
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
  }
  // Se insertan las pistas en la tabla key : value
  for (int i = 1; i <= nDepartureRunway; ++i) {
    ct.departureRunway.insert(std::pair<int, bool>(i, false));
  }
  inputOK.unlock(); // desbloquea las consultas de la pantalla de informacion
  std::string destName;
  std::string destAddress;
  while (run.load()) {
    std::cout << "[Torre de control - " << ct.name << "] Para agregar destino presione enter";
    if (std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n')) {
      std::cout << "[Torre de control - " << ct.name << "] Ingrese nombre y direccion IP del destino:" << std::endl;
      while(getline(std::cin, buffer) && !buffer.empty()) {
        if (buffer == "exit") {
          run.store(false);
          return;
        }
        std::stringstream ss(buffer);
        ss >> destName >> destAddress;
        if (!ss) {
          std::cout << "[Torre de control - " << ct.name << "] Destino inválido" << std::endl;
          break;
        } else {
          std::cout << "[Torre de control - " << ct.name << "] Destino agregado con éxito" << std::endl;
          // Se llena la lista de destinos
          Dest *dest = new Dest(destName, destAddress);
          destList.push_back(*dest);
          // for (Dest n : destList)
          // {
          //   n.setPlaneMsge();
          //   std::cout << n.getName() << " : " << n.getAddress() << " : " /*<< n.getPlaneMsge()*/ << '\n';
          // }
          break;
        }
      } 
    }
  }
}

//Iniciar alturas disponibles
void initHeight() {  
  for (int i = minHeight; i < maxHeight+1; ++i) {
    heightMap.insert(std::pair<int, bool> (i, false));
  }
}

int main() {
  std::atomic<bool> run(true);
  serverOK.lock();
  std::thread userInputThread(WaitInput, std::ref(run));
  initHeight();
  RunServer();
  run.store(false);
  userInputThread.join();
  return 0;
}