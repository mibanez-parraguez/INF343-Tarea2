package avion;

import java.io.Console;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import avion.proto.PlaneControlServiceGrpc.PlaneControlServiceBlockingStub;
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
	
// 	private final ManagedChannel channel;
// 	private final PlaneControlServiceBlockingStub blockingStub;
	private ManagedChannel channel;
	private PlaneControlServiceBlockingStub blockingStub;

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

		//this.land(dest);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}


	private void takeoff(){
		Console c = System.console();
		c.printf(APROMPT, " - ", this.id,  "Ingrese destino:");
		String dest = c.readLine(APROMPT, " - ", this.id,  "");

		this.gate();

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
		int port = Integer.parseInt(this.curr_addr.split(" ")[1]);

		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		blockingStub = PlaneControlServiceGrpc.newBlockingStub(channel);

		Iterator<TakeoffResponse> resps;
// 		try{
// 			resps = blockingStub.takeoff(request);
// 			for (int i = 1; resps.hasNext(); i++) {
// 				c.printf(APROMPT, " - ", this.id,  "takeoff_resp"+i);
// 			}
// 		} catch (StatusRuntimeException e) {
// // 			warning("RPC failed: {0}", e.getStatus());
// 				c.printf(APROMPT, " - ", this.id,  "RPC failed: " + e.getStatus());
// 		}
	}

	private void land(){
		//TODO hacer output
		//TODO implmenentar llegada de respuesta
		Console c = System.console();

		String host = this.dest_addr.split(":")[0];
		int port = Integer.parseInt(this.dest_addr.split(" ")[1]);

		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		blockingStub = PlaneControlServiceGrpc.newBlockingStub(channel);

		PlaneMsge.Builder pmsg = PlaneMsge.newBuilder();
		pmsg.setSourceAddress(this.curr_addr);
		pmsg.setDestAddress(this.dest_addr);
		
		LandRequest request = 
			LandRequest.newBuilder()
				.setPlane(pmsg.build())
				.setDest(this.dest_addr).build();

		Iterator<LandResponse> resps;
// 		try{
// 			resps = blockingStub.land(request);
// 			for (int i = 1; resps.hasNext(); i++) {
// 				c.printf(APROMPT, " - ", this.id,  "land_resp"+i);
// 			}
// 		} catch (StatusRuntimeException e) {
// // 			warning("RPC failed: {0}", e.getStatus());
// 			c.printf(APROMPT, " - ", this.id,  "RPC failed: " + e.getStatus());
// 		}
	}


	private void gate(){
		Console c = System.console();
		c.printf(APROMPT, " - ", this.id,  "Pasando por gate.");
		this.load = this.max_load;
		this.fuel = this.max_fuel;
		c.printf(APROMPT, " - ", this.id,  "Pasajeros a bordo y combustible cargado.");
	}

	private void dump(){
// 		System.out.println("aerolinea: "+this.airline);
// 		System.out.println("vuelo: "+this.id);
		System.out.println("carga: "+this.max_load);
		System.out.println("fuel: "+this.max_fuel);
	}

	public static void main(String[] args){
		Avion le_avion = new Avion();
		le_avion.dump();
		
		while(true){
			break;
		}
	}
}
