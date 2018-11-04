//package avion;

import java.io.Console;

//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import io.grpc.StatusRuntimeException;


public class Avion {
	private String id;
	private String airline;
	private int max_carga; // pasajeros == carga? o agregar attrib adicional?
	private int carga;
	private int max_fuel;
	private int fuel;
	private String dest_name;
	private static final String APROMPT="[Avion%1$s%2$s]: %3$s";

	public Avion(){
		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		String input;
		input = c.readLine(APROMPT, "", "", "Nombre de la Aerolínea y número de Avión:\n> ");
		this.id = input.split(" ")[1];
		this.airline = input.split(" ")[0];

		input = c.readLine(APROMPT, " - ", this.id,  "Peso máximo de carga [kg]:\n> ");
		this.max_carga = Integer.parseInt(input);

		input = c.readLine(APROMPT, " - ", this.id,  "Capacidad del tanque de combustible [l]:\n> ");
		this.max_fuel = Integer.parseInt(input);

		this.dest_name = c.readLine(APROMPT, " - ", this.id,  "Torre de Control inicial:\n> ");

		//input = c.readLine(APROMPT, " - ", this.id,  ":\n>");
	}

	private void takeoff(){
		//channel = ManagedChannelBuilder.forAddress(host, port).build();
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		blockingStub = PlaneControlServiceGrpc.newBlockingStub(channel);
		asyncStub = PlaneControlServiceGrpc.newStub(channel);
		
		TakeoffRequest request = TakeoffRequest.newBuilder();
		request.setPlaneId(this.id);
		request.setPassengers(this.carga); // ver esto
		request.setFuel(this.fuel);
		request.setMaxLoad(this.max_carga);
		request.setDestName(this.dest_name)
		request.build();

		TakeoffResponse resp;
		try{
			resp = blockingStub.takeoff(request)
		} catch (StatusRuntimeException e) {
			System.out.println("RPC failed: " + e.getStatus());
			return;
		}
	}

	private void gate(){
		
	}

	private void dump(){
		System.out.println("aerolinea: "+this.airline);
		System.out.println("vuelo: "+this.id);
		System.out.println("carga: "+this.max_carga);
		System.out.println("fuel: "+this.max_fuel);
	}

	public static void main(String[] args){
		Avion le_avion = new Avion();
		le_avion.dump();
	}
}
