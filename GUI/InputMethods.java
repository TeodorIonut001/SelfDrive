package gui;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import map.LimitsGenerator;
import vehicle.GenerateVehicle;

public class InputMethods {
	
	private LimitsGenerator limitsGenerator;
	private GenerateVehicle vehicle;
	private int numOfWalls = 0;
	private int mapLength;
	private int wallLength;
	private int vehicleX;
	private int vehicleY;
	private int endRouteX;
	private int endRouteY;
	int mapViewCount = 0;

	
	public void finishButton(String wallNumber, String mapSize, String wallSize, String choice, 
			                 String vX, String vY, String eX, String eY) throws IOException {
		try {
			numOfWalls = ((Number)NumberFormat.getInstance().parse(wallNumber)).intValue();
			mapLength  = ((Number)NumberFormat.getInstance().parse(mapSize)).intValue();
			wallLength = ((Number)NumberFormat.getInstance().parse(wallSize)).intValue();
			vehicleX   = ((Number)NumberFormat.getInstance().parse(vX)).intValue();
			vehicleY   = ((Number)NumberFormat.getInstance().parse(vY)).intValue();
			endRouteX  = ((Number)NumberFormat.getInstance().parse(eX)).intValue();
			endRouteY  = ((Number)NumberFormat.getInstance().parse(eY)).intValue();
			// This crashes if example: mapLength 21; wallLength = 10;
			//if(mapLength <= wallLength*2 + 1) mapLength = wallLength*2 + 3;
			
			//First phase - get the variables in place and choose map.
			//v0.5 edit, added mapChoice, vehicle Position choice
			if(choice == "Generare automata") {	
				limitsGenerator = new LimitsGenerator();
				limitsGenerator.buildTestWall(numOfWalls, mapLength, wallLength);
				vehicle = new GenerateVehicle(limitsGenerator.getMap());
				}
			else if (choice == "Predefinit"){
				//TODO: predefined choice
				System.exit(0);
				}
			//Last mapping phase 
			//1. Pass vehicle coordinates
			vehicle.mapVehicle(vehicleX, vehicleY, endRouteX, endRouteY);	
			System.out.println(numOfWalls + " was loaded.");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.exit(0);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public int getMapViewCount() {
		return mapViewCount;
	}
	
}
