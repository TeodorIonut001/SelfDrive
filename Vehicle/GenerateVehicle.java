package vehicle;

import java.io.IOException;
import io.OutputFile;
public class GenerateVehicle {

	private OutputFile out = new OutputFile();
	private PathFinder path;
	private Integer[][] mapArea;
	//For vehicle position
	private int startValue = 2;
	private int xPos = -1;
	private int yPos = -1;
	//For route end position
	private int endValue = -1;
	private int endX = -1;
	private int endY = -1;
	
	public GenerateVehicle(Integer[][] map) {
		this.mapArea = map;
		path = new PathFinder(mapArea);
	}
	
	public GenerateVehicle() {
		//Do nothing
	}
	
	private void findSpace(){
		//boolean, mapArea[xPos][yPos] is not already 1
		boolean place = false;
		while(!place) {
			xPos = (int)(Math.random()*mapArea.length); 
			yPos = (int)(Math.random()*mapArea[0].length); 
			if(mapArea[xPos][yPos] == 0) {
				place = true;
			}
		}
	}
	
	private void findEnd() {
		boolean place = false;
		while(!place) {
			endX = (int)(Math.random()*mapArea.length); 
			endY = (int)(Math.random()*mapArea[0].length); 
			if(mapArea[endX][endY] == 0) {
				place = true;
			}
		}
	}
	
	private void placeRouteCoordinates() {
		//2 = 'vehicle start'
		mapArea[xPos][yPos] =  startValue;
		//-1= 'route end'
		mapArea[endX][endY] = endValue;
	}
	
	public void printInfo() {
		
		System.out.println(xPos + " " + yPos);
		
		for (int i=0; i < mapArea.length; i++){
			for(int j=0; j < mapArea[0].length; j++) {
				System.out.print(mapArea[i][j]);
			}
			System.out.println();
		}
	}
	
	public Integer[][] getMap(){
		return mapArea;
	}
	
	public int getStartValue() {
		return startValue;
	}
	
	public int getEndValue() {
		return endValue;
	}
	
	public int getEndX() {
		return endX;
	}
	
	public int getEndY() {
		return endY;
	}
	
	public void mapVehicle(int vX, int vY, int eX, int eY) throws IOException {
		//v0.5 update
		//Random space finder
		if(vX == 0 || vY == 0 || vX >= mapArea.length || vY >= mapArea[0].length) {
			findSpace();
		}else {
			//Overrides wall infrastructure
			xPos = vX;
			yPos = vY;
		}
		if(eX == 0 || eY == 0 || eX >= mapArea.length || eY >= mapArea[0].length) {
			findEnd();
		}else {
			//Overrides wall infrastructure
			endX = eX;
			endY = eY;
		}
		placeRouteCoordinates();
		// INPUT - FILE.EXTENSION - FOLDER\\SUBFOLDER - important to use the double " // "
		out.toFile(mapArea, "vMap.txt", "Vehicle", false);
		out.toFile("Vehicle(x, y): " + xPos + ", " + yPos + 
				   "\nEnd(x, y): "   + endX + ", " + endY, 
				   "vehiclePos.txt", "Vehicle", false);
		
		path.setRoute();
	}
}
