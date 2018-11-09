package avion;

import java.io.Console;
import java.io.IOException;
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
	private int altitude;
	private PlaneMsge.Builder plane;
	private static final String APROMPT="[Avion%1$s%2$s]: %3$s";
	
	private boolean desOK;
	private boolean resOK;
	private boolean insOK;
	private boolean flyOK;

	private ManagedChannel channel;
	private PlaneControlServiceStub asyncStub;
	private StreamObserver<LandRequest> landRequestObserver;
	private StreamObserver<TakeoffRequest> takeoffRequestObserver;

	public Avion(){
		Console c = System.console();
		this.plane = PlaneMsge.newBuilder();
		String input;

		input = c.readLine(APROMPT, "", "", "Nombre de la Aerolínea y número de Avión:\n> ");
		this.plane.setPlaneNumber(input.split(" ")[1]);
		this.plane.setAirline(input.split(" ")[0]);
		this.id = input.split(" ")[1];

		input = c.readLine(APROMPT, " - ", this.id,  "Peso máximo de carga [kg]:\n> ");
		this.plane.setMaxLoad(Integer.parseInt(input));

		input = c.readLine(APROMPT, " - ", this.id,  "Capacidad del tanque de combustible [l]:\n> ");
		this.plane.setMaxCapacity(Integer.parseInt(input));

		String dest = c.readLine(APROMPT, " - ", this.id,  "Torre de Control inicial:\n> ");
		this.plane.setDestAddress(dest);
		this.plane.setSourceAddress(dest);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	private TakeoffRequest makeRequest(boolean inst_ok, boolean fly_ok){
		TakeoffRequest request = 
			TakeoffRequest.newBuilder()
				.setPlane(this.plane.build())
				.setDest(this.plane.getDestName())
				.setInstOK(inst_ok)
				.setFlyOK(fly_ok).build();
		return request;
	}
	
	private int handleResponse(TakeoffResponse resp){
		Console c = System.console();
		c.printf("------\n");
		c.printf("[resp] altitude: " + resp.getAltitude()+"\n");
		c.printf("[resp]   runway: " + resp.getRunway()+"\n");
		c.printf("[resp] queuepos: " + resp.getQueuePos()+"\n");
		c.printf("[resp] destname: " + resp.getDestName()+"\n");
		c.printf("[resp]   destOK: " + resp.getDestOK()+"\n");
		c.printf("[resp]  restrOK: " + resp.getRestrOK()+"\n");
		c.printf("[resp]   instOK: " + resp.getInstOK()+"\n");
		c.printf("[this]  t.desOK: " + this.desOK+"\n");
		c.printf("[this]  t.resOK: " + this.resOK+"\n");
		c.printf("[this]  t.insOK: " + this.insOK+"\n");
		c.printf("------\n");
		if(!resp.getDestOK()){
			this.plane.setDestName(c.readLine(APROMPT, " - ", this.id,  "Destino inválido. Por favor ingrese un destino valido\n> "));
			TakeoffRequest request = makeRequest(false,false);
			this.takeoffRequestObserver.onNext(request);
			return -1;
		}
		if(resp.getDestOK() && !this.desOK){ // Recibe ip de destino
			this.plane.setDestAddress(resp.getDest());
			this.desOK = true;
			c.printf(APROMPT, " - ", this.id,  "Recibida IP de destino.\n");
// 			TakeoffRequest request = makeRequest(false,false);
// 			this.takeoffRequestObserver.onNext(request);
			return 1;
		}
		if(!resp.getRestrOK() && !this.resOK){ // Debo pasar por gate
			this.gate();
			
			TakeoffRequest request = makeRequest(true,false);
			c.printf("[request] fuel: " + request.getPlane().getCurrCapacity()+"\n");
			c.printf("[request] load: " + request.getPlane().getCurrLoad()+"\n");
			this.takeoffRequestObserver.onNext(request);
			return 2;
		}
		if(resp.getRestrOK() && !this.resOK){ // Se cumple con restricciones
			c.printf(APROMPT, " - ", this.id,  "Pasajeros a bordo y combustible cargado.\n");
			this.resOK = true;
			return 3;
		}
		if(resp.getQueuePos()!=0){ // Esperando a la habilitación de una pista.
			c.printf(APROMPT, " - ", this.id,  "Las pistas de despegue están ocupadas.\n");
			c.printf(APROMPT, " - ", this.id,  "Avión predecesor: "+ resp.getPrevPlane() +".\n");
			return 4;
		}
		if(resp.getRunway()!=0 && !this.flyOK /*&& !this.insOK*/){ // Despegue
			this.altitude = resp.getAltitude();
			c.printf(APROMPT, " - ", this.id,  "Pista "+ resp.getRunway() + " y altura de "+ resp.getAltitude()+" [km].\n");
			c.readLine(APROMPT, " - ", this.id,  "Todo listo. Presione enter para despegar...");
			c.printf(APROMPT, " - ", this.id,  "Despegando...\n");
// 			this.insOK = true;
			this.flyOK = true;
			TakeoffRequest request = makeRequest(false,true);
			this.takeoffRequestObserver.onNext(request);
			return 5;
		}
// 		if(resp.getRunway()!=0 && !this.flyOK){
// 			this.flyOK = true;
// 			return 6;
// 		}
		if(resp.getRunway()!=0 && this.flyOK){ // Se confirma despegue y cierra comunicación.
			System.console().printf("fin.\n");
			c.printf(APROMPT, " - ", this.id,  "");
			this.takeoffRequestObserver.onCompleted();
			return 0;
		}
		return -1;
	}
	
	private CountDownLatch takeoffProcedure(){
		final CountDownLatch finishLatch = new CountDownLatch(1);
		Console c = System.console();
		
		this.desOK = false;
		this.resOK = false;
		this.insOK = false;
		this.flyOK = false;
		
		this.plane.setDestName(c.readLine(APROMPT, " - ", this.id,  "Ingrese destino:\n> "));
		
		String host = this.plane.getSourceAddress().split(":")[0];
		int port = Integer.parseInt(this.plane.getSourceAddress().split(":")[1]);
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		asyncStub = PlaneControlServiceGrpc.newStub(channel);
		
		this.takeoffRequestObserver =
			asyncStub.takeoff(new StreamObserver<TakeoffResponse>() {
				@Override
				public void onNext(TakeoffResponse resp) {
					System.out.println("[onNext] "+handleResponse(resp)+'\n');
				}
				@Override
				public void onError(Throwable t) {
					System.console().printf("Takeoff error\n");
					finishLatch.countDown();
				}
				@Override
				public void onCompleted() {
					System.console().printf("Despegue realizado.\n");
					finishLatch.countDown();
				}
			});
		TakeoffRequest request = makeRequest(false,false);
		System.console().printf("[takeoff] first request.\n");
		this.takeoffRequestObserver.onNext(request);
		return finishLatch;
	}

	private void handleResponse(LandResponse resp){
		Console c = System.console();
		if(resp.getRunway() != 0){
			//Aterrizar
			c.printf(APROMPT, " - ", this.id,  "*Aterrizando en pista: "+ resp.getRunway() +"\n");
			c.printf(APROMPT, " - ", this.id,  "");
			this.plane.setRunway(resp.getRunway());
			this.plane.setSourceAddress(this.plane.getDestAddress());
			this.plane.setCurrLoad(0);
			this.plane.setCurrCapacity(0);
			LandRequest request = 
				LandRequest.newBuilder()
					.setPlane(this.plane.build())
					.setDest(this.plane.getDestName()).build();
			this.landRequestObserver.onNext(request);
			this.landRequestObserver.onCompleted();
		} else{
			// Establecer altura de vuelo mientras se espera.
			c.printf(APROMPT, " - ", this.id,  "Estableciendo altura de espera\n");
			this.altitude = resp.getAltitude();
			c.printf(APROMPT, " - ", this.id,  "Vuelo a: "+ this.altitude +" [m] de altura.\n");
		}
	}

	private CountDownLatch landProcedure(){
		Console c = System.console();
		final CountDownLatch finishLatch = new CountDownLatch(1);
		c.printf(APROMPT, " - ", this.id,  "Esperando pista de aterrizaje.\n");
		
		String host = this.plane.getDestAddress().split(":")[0];
		int port = Integer.parseInt(this.plane.getDestAddress().split(":")[1]);
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
					finishLatch.countDown();
				}
				@Override
				public void onCompleted() {
					System.console().printf("aterrizaje completado.\n");
					finishLatch.countDown();
				}
			});
		LandRequest request = 
			LandRequest.newBuilder()
				.setPlane(this.plane.build())
				.setDest(this.plane.getDestAddress()).build(); //TODO IP o name?
				
		try {
			this.landRequestObserver.onNext(request);
		} catch (RuntimeException e) {
			this.landRequestObserver.onError(e);
			throw e;
		}
		return finishLatch;
	}


	private void gate() {
		Console c = System.console();
		c.printf(APROMPT, " - ", this.id,  "Pasando por gate...\n");
		this.plane.setCurrLoad(this.plane.getMaxLoad());
		this.plane.setCurrCapacity(this.plane.getMaxCapacity());
	}

	public static void main(java.lang.String[] args) throws InterruptedException {
		Console c = System.console();
		Avion le_avion = new Avion();
		CountDownLatch finishLatch = le_avion.landProcedure();
		if (!finishLatch.await(10, TimeUnit.MINUTES)) {
			c.printf("Error en comunicación");
		}
		while(true){
			try {
				c.readLine(APROMPT, " - ", le_avion.id,  "Presione enter para despegar...");
				finishLatch = le_avion.takeoffProcedure();
				if (!finishLatch.await(10, TimeUnit.MINUTES)) {
					c.printf("Error en comunicación");
				}
				c.readLine(APROMPT, " - ", le_avion.id,  "Presione enter para aterrizar...");
				finishLatch = le_avion.landProcedure();
				if (!finishLatch.await(10, TimeUnit.MINUTES)) {
					c.printf("Error en comunicación");
				}
			} finally {
				le_avion.shutdown();
			}
		}
	}
}
