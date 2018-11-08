package avion;

import java.io.Console;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

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
	private String dest_addr;
	private String curr_addr;
	private int max_load;
	private int max_fuel;
	private int load;
	private int fuel;
	private PlaneMsge plane;
	private static final String APROMPT="[Avion%1$s%2$s]: %3$s";
	
	private int aux_int;
	private String aux_str;
	
// 	private final ManagedChannel channel;
// 	private final PlaneControlServiceBlockingStub blockingStub;
	private ManagedChannel channel;
	private PlaneControlServiceStub asyncStub;

	public Avion(){
		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}

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
		this.aux_int = -9;
		this.land(dest);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	private void auxFunc(TakeoffResponse resp) {
		System.out.println(resp.getQueuePos());
	}

	private void takeoff(String dest){
		PlaneMsge.Builder pmsg = PlaneMsge.newBuilder();
		pmsg.mergeFrom(this.plane);
		pmsg.setCurrCapacity(this.fuel);
		pmsg.setCurrLoad(this.load);
		pmsg.setDestAddress(dest);

		TakeoffRequest request = 
			TakeoffRequest.newBuilder()
				.setPlane(pmsg.build())
				.setDest(dest)
				.setInstOK(true).build();

		String host = this.curr_addr.split(":")[0];
		int port = Integer.parseInt(this.curr_addr.split(":")[1]);
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		asyncStub = PlaneControlServiceGrpc.newStub(channel);
		
		System.out.println(channel);
		
		StreamObserver<TakeoffRequest> requestObserver =
			asyncStub.takeoff(new StreamObserver<TakeoffResponse>() {
				@Override
				public void onNext(TakeoffResponse resp) {
					
					if(resp.getRunway() != 0){
						//despegar
					} else{
					
					}
					
				}
				@Override
				public void onError(Throwable t) {
					//warning("Take off Failed: {0}", Status.fromThrowable(t));
					System.console().printf("Takeoff error\n");
					//finishLatch.countDown();
				}
				@Override
				public void onCompleted() {
					// 
					System.console().printf("onCompleted.\n");
				}
			});
	}
	
	private void takeoffProcedure(){
		Console c = System.console();
		c.printf(APROMPT, " - ", this.id,  "Ingrese destino:\n");
		String dest = c.readLine(APROMPT, " - ", this.id,  "");

		c.printf(APROMPT, " - ", this.id,  "Pasando por gate.\n");
		this.gate();
		c.printf(APROMPT, " - ", this.id,  "Pasajeros a bordo y combustible cargado.\n");

		c.printf(APROMPT, " - ", this.id,  "Pidiendo instrucciones para despegar.\n");
		this.takeoff(dest);
	}

	private void land(){
		//TODO hacer output
		//TODO implmenentar llegada de respuesta
		Console c = System.console();

		PlaneMsge.Builder pmsg = PlaneMsge.newBuilder();
		pmsg.setSourceAddress(this.curr_addr);
		pmsg.setDestAddress(this.dest_addr);
		
		LandRequest request = 
			LandRequest.newBuilder()
				.setPlane(pmsg.build())
				.setDest(this.dest_addr).build();

		String host = this.curr_addr.split(":")[0];
		int port = Integer.parseInt(this.curr_addr.split(":")[1]);
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		asyncStub = PlaneControlServiceGrpc.newStub(channel);
		
		System.out.println(channel);
		
		StreamObserver<LandRequest> requestObserver =
			asyncStub.takeoff(new StreamObserver<LandResponse>() {
				@Override
				public void onNext(LandResponse resp) {
					//TODO aterrizar
					System.console().printf("asdf\n");
					System.out.println(resp.getQueue());
					auxFunc(resp);
					aux_int = resp.getQueue();
					
					if(resp.getRunway() != 0){
						//Aterrizar
					}
				}
				@Override
				public void onError(Throwable t) {
					System.console().printf("Landing error\n");
				}
				@Override
				public void onCompleted() {
					// 
					System.console().printf("(borrar)onCompleted.\n");
				}
			});
		requestObserver.onNext(request);
		System.out.println("post_next");
		System.out.println(this.aux_int);
		requestObserver.onCompleted();
	}


	private void gate(){
		this.load = this.max_load;
		this.fuel = this.max_fuel;
	}

	private void dump(){
// 		System.out.println("aerolinea: "+this.airline);
// 		System.out.println("vuelo: "+this.id);
		System.out.println("carga: "+this.max_load);
		System.out.println("fuel: "+this.max_fuel);
		System.out.println("curr_addr: "+this.curr_addr);
	}

	public static void main(java.lang.String[] args){
		Avion le_avion = new Avion();
		le_avion.dump();
		
		//le_avion.takeoffProcedure();
		
		while(true){
			break;
		}
	}
}
