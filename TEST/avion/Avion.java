package avion;

import java.io.Console;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import avion.proto.PlaneControlServiceGrpc.PlaneControlServiceStub;
import avion.proto.PlaneControlServiceGrpc;
import avion.proto.TakeoffRequest;
import avion.proto.TakeoffResponse;
import avion.proto.LandRequest;
import avion.proto.LandResponse;
import avion.proto.PlaneMsge;

public class Avion {
	private String id;
	private String dest;
	private String dest_addr;
	private String curr_addr;
	private int max_load;
	private int max_fuel;
	private int load;
	private int fuel;
	private int altitude;
	private PlaneMsge plane;
	private static final String APROMPT="[Avion%1$s%2$s]: %3$s";
	
	private int aux_int;
	private String aux_str;
	
	private boolean desOK;
	private boolean resOK;
	private boolean insOK;
	
// 	private final ManagedChannel channel;
// 	private final PlaneControlServiceBlockingStub blockingStub;
	private ManagedChannel channel;
	private PlaneControlServiceStub asyncStub;
	private StreamObserver<LandRequest> landRequestObserver;
	private StreamObserver<TakeoffRequest> takeoffRequestObserver;

	public Avion(){
		Console c = System.console();

		PlaneMsge.Builder pb = PlaneMsge.newBuilder();
		String input;

		input = c.readLine(APROMPT, "", "", "Nombre de la Aerolínea y número de Avión:\n> ");
		pb.setPlaneNumber(input.split(" ")[1]);
		pb.setAirline(input.split(" ")[0]);
		this.id = input.split(" ")[1];

		input = c.readLine(APROMPT, " - ", this.id,  "Peso máximo de carga [kg]:\n> ");
		pb.setMaxLoad(Integer.parseInt(input));
		this.max_load = Integer.parseInt(input);

		input = c.readLine(APROMPT, " - ", this.id,  "Capacidad del tanque de combustible [l]:\n> ");
		pb.setMaxCapacity(Integer.parseInt(input));
		this.max_fuel = Integer.parseInt(input);

		this.plane = pb.build();

		String dest = c.readLine(APROMPT, " - ", this.id,  "Torre de Control inicial:\n> ");
		this.curr_addr = dest;
		this.dest_addr = dest;
		this.aux_int = -9;
		this.landProcedure();
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	private TakeoffRequest makeRequest(String dest, boolean inst_ok){
		PlaneMsge.Builder pmsg = PlaneMsge.newBuilder();
		pmsg.mergeFrom(this.plane);
		pmsg.setCurrCapacity(this.fuel);
		pmsg.setCurrLoad(this.load);
		pmsg.setDestAddress(dest);

		TakeoffRequest request = 
			TakeoffRequest.newBuilder()
				.setPlane(pmsg.build())
				.setDest(dest)
				.setInstOK(inst_ok).build();
		return request;
	}
	
	private int handleResponse(TakeoffResponse resp){
		Console c = System.console();
		if(resp.getDestOK() && !this.desOK){
			// Recibe ip de destino
			this.dest_addr = resp.getDest();
			this.desOK = true;
			c.printf(APROMPT, " - ", this.id,  "Recibida IP de destino.\n");
			TakeoffRequest request = makeRequest("", false);
			this.takeoffRequestObserver.onNext(request);
			return 1;
		}
		if(resp.getRestrOK() && !this.resOK){
			this.gate();
			this.resOK = true;
			TakeoffRequest request = makeRequest("", false);
			this.takeoffRequestObserver.onNext(request);
			return 2;
		}
		if(resp.getQueuePos()!=0){
			// Esperando a la habilitación de una pista.
			c.printf(APROMPT, " - ", id,  "Las pistas de despegue están ocupadas.\n");
			c.printf(APROMPT, " - ", id,  "Avión predecesor: "+ resp.getPrevPlane() +".\n");
			// Hay que hacer request aca??
// 			TakeoffRequest request = makeRequest("", false);
// 			takeoffRequestObserver.onNext(request);
			return 3;
		}
		if(resp.getRunway()!=0){
			// Despegue
			this.altitude = resp.getAltitude();
			c.printf(APROMPT, " - ", this.id,  "Pista "+ resp.getRunway() + " y altura de "+ resp.getAltitude()+" [km].\n");
			c.printf(APROMPT, " - ", this.id,  "Despegando.\n");
			this.takeoffRequestObserver.onCompleted();
			this.landProcedure();
			return 0;
		}
		return -1;
	}
	
	private void takeoffProcedure(){
		Console c = System.console();
		
		this.dest_addr = "";
		this.desOK = false;
		this.resOK = false;
		this.insOK = false;
		
		c.printf(APROMPT, " - ", this.id,  "Ingrese destino:\n");
		String dest = c.readLine(APROMPT, " - ", this.id,  "");
		
		String host = this.curr_addr.split(":")[0];
		int port = Integer.parseInt(this.curr_addr.split(":")[1]);
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		asyncStub = PlaneControlServiceGrpc.newStub(channel);
		
		this.takeoffRequestObserver =
			asyncStub.takeoff(new StreamObserver<TakeoffResponse>() {
				@Override
				public void onNext(TakeoffResponse resp) {
					int op = handleResponse(resp);
				}
				@Override
				public void onError(Throwable t) {
					System.console().printf("Takeoff error\n");
				}
				@Override
				public void onCompleted() {
					System.console().printf("Despegue realizado.\n");
				}
			});
		TakeoffRequest request = makeRequest(dest, false);
		this.takeoffRequestObserver.onNext(request);
	}

	private void handleResponse(LandResponse resp){
		Console c = System.console();
		c.printf("[land.onNext.handler] getQueue: "+resp.getQueue()); //Ver si sale antes de "... enter para continuar"
		if(resp.getRunway() != 0){
			//Aterrizar
			c.printf(APROMPT, " - ", this.id,  "*Aterrizando en pista: "+ resp.getRunway() +"\n");
			this.landRequestObserver.onCompleted(); //!!
			this.takeoffProcedure();
		} else{
			// Establecer altura de vuelo mientras se espera.
			c.printf(APROMPT, " - ", this.id,  "*Estableciendo altura de espera\n");
			this.altitude = resp.getAltitude();
			c.printf(APROMPT, " - ", this.id,  "*Vuelo a: "+ this.altitude +" [m] de altura.\n");
		}
	}

	private void landProcedure(){
		Console c = System.console();
		c.printf(APROMPT, " - ", this.id,  "Esperando pista de aterrizaje.\n");
		
		String host = this.curr_addr.split(":")[0];
		int port = Integer.parseInt(this.curr_addr.split(":")[1]);
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		asyncStub = PlaneControlServiceGrpc.newStub(channel);
		
		this.landRequestObserver =
			asyncStub.land(new StreamObserver<LandResponse>() {
				@Override
				public void onNext(LandResponse resp) {
					handleResponse(resp);
				}
				@Override
				public void onError(Throwable t) {
					System.console().printf("Landing error\n");
				}
				@Override
				public void onCompleted() {
					System.console().printf("aterrizaje completado.\n");
				}
			});
		
		PlaneMsge.Builder pmsg = PlaneMsge.newBuilder();
		pmsg.setSourceAddress(this.curr_addr);
		pmsg.setDestAddress(this.dest_addr);
		LandRequest request = 
			LandRequest.newBuilder()
				.setPlane(pmsg.build())
				.setDest(this.dest_addr).build();
		this.landRequestObserver.onNext(request);
	}


	private void gate(){
		Console c = System.console();
		c.printf(APROMPT, " - ", this.id,  "Pasando por gate...\n");
		this.load = this.max_load;
		this.fuel = this.max_fuel;
		c.printf(APROMPT, " - ", this.id,  "Pasajeros a bordo y combustible cargado.\n");
	}

	public static void main(java.lang.String[] args){
		Console c = System.console();
		Avion le_avion = new Avion();
		c.readLine(APROMPT, " - ", le_avion.id,  "Presione enter para continuar...");
// 		while(true){
// 			c.readLine(APROMPT, " - ", le_avion.id,  "Presione enter para despegar...");
// 			le_avion.takeoffProcedure();
// 			c.readLine(APROMPT, " - ", le_avion.id,  "Presione enter para aterrizar...");
// 			le_avion.landProcedure();
// 		}
	}
}
