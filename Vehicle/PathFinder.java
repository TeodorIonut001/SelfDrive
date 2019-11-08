package vehicle;

import java.io.IOException;
import gui.MapViewer;

import io.OutputFile;

public class PathFinder {

	private Integer[][] mapArea;
	private int rStart;
	private int rEnd;
	private int obstacle = 1;
	//highest = biggest number on the map, fastTravel use
	private int highest = 0;
	private GenerateVehicle vehicle;
	private OutputFile out;
	private MapViewer view;
	//found =  'free space was found' / number 0 is found 
	private boolean found = false;
	

	public PathFinder(Integer[][] map) {
		out     = new OutputFile();
		vehicle = new GenerateVehicle();
		mapArea = map;
		rStart  = vehicle.getStartValue();
		rEnd    = vehicle.getEndValue();
	}
	
	public PathFinder() {
		
	}
	
	private void planRoutes(int searchFor) {
		found = false;
		for(int i=0; i<mapArea.length; i++) {
			for(int j=0; j<mapArea[0].length; j++) {

				if(mapArea[i][j] == searchFor) {
					// S/ N/ E/ W
					//test for out of bounds
					if(!(i+1 >= mapArea.length)) {

						//then do the thing
						if(mapArea[i+1][j] == 0) {
							mapArea[i+1][j] = searchFor + 1;
							found = true;
						}
					}
					if(!(i-1 < 0)) {

						if(mapArea[i-1][j] == 0) {
							mapArea[i-1][j] = searchFor + 1;
							found = true;
						}
					}
					if(!(j+1 >= mapArea.length)) {	

						if(mapArea[i][j+1] == 0) {
							mapArea[i][j+1] = searchFor + 1;
							found = true;
						}
					}
					if(!(j-1 < 0)) {	

						if(mapArea[i][j-1] == 0) {
							mapArea[i][j-1] = searchFor + 1;
							found = true;
						}
					}
					
					//NE / NW/ SE/ SW
					if(!(j+1 >=mapArea.length || i-1 < 0)) {

						if(mapArea[i-1][j+1] == 0) {
							mapArea[i-1][j+1] = searchFor + 1;
							found = true;
						}
					}
					
					if(!(j-1 < 0 || i-1 < 0)) {

						if(mapArea[i-1][j-1] == 0) {
							mapArea[i-1][j-1] = searchFor + 1;
							found = true;
						}
					}
					
					if(!(j+1 >=mapArea.length || i+1 >= mapArea.length)) {

						if(mapArea[i+1][j+1] == 0) {
							mapArea[i+1][j+1] = searchFor + 1;
							found = true;
						}

					}
					
					if(!(i+1 >=mapArea.length || j-1 < 0)) {

						if(mapArea[i+1][j-1] == 0) {
							mapArea[i+1][j-1] = searchFor + 1;
							found = true;
						}
					}	
					//end / \  movement
				}					
			}
		}//end 2xfor
		searchFor++;
		highest = searchFor;
		if(found) planRoutes(searchFor);
	}
	
	// < v0.6.1 > 
	public void fastTravel() {
		System.out.println("Start fastTravel");

		int x = 0;
		int y = 0;
		//test
		for(int i=0; i<mapArea.length; i++) {
			for(int j=0; j<mapArea.length; j++) {
				if(mapArea[i][j] == rEnd) {
					x = i;
					y = j;
					break;
				}
			}
		}
		
		int testNum = 60; //higher color gradient
		
		mapArea[x][y] = highest;
		System.out.printf("%s route start", rStart);
		for(int i=0; i<50; i++) {
			//Search N
			if(!(x-1 <= 0)) {
				if(highest > mapArea[x-1][y]) {
					highest = mapArea[x-1][y];
					mapArea[x-1][y] = testNum;
					
					x--;
				}
			}
			
			//Search S
			if(!(x+1 >= mapArea.length)) {
				if(highest > mapArea[x+1][y]) {
					highest = mapArea[x+1][y];
					mapArea[x+1][y] = testNum;
					
					x++;
				}
			}
			
			//Search E
			if(!(y+1 >= mapArea.length)) {
				if(highest > mapArea[x][y+1]) {
					highest = mapArea[x][y+1];
					mapArea[x][y+1] = testNum;
					
					y++;
				}
			}
			
			//Search W
			if(!(y-1 <= 0)) {
				if(highest > mapArea[x][y-1]) {
					highest = mapArea[x][y-1];
					mapArea[x][y-1] = testNum;
					
					y--;
				}
			}
			
			//Search NE
			if(!((x-1 <= 0) && (y+1 >= mapArea.length))) {
				if(highest > mapArea[x-1][y+1]) {
					highest = mapArea[x-1][y+1];
					mapArea[x-1][y+1] = testNum;
					
					x--;
					y++;
				}
			}
			
			//Search NW
			if(!((x-1 <= 0) && (y-1 <= 0))) {
				if(highest > mapArea[x-1][y-1]) {
					highest = mapArea[x-1][y+1];
					mapArea[x-1][y+1] = testNum;
					
					x--;
					y--;
				}
			}
			
			//Search SE
			if(!((x+1 >= mapArea.length) && (y+1 >= mapArea.length))) {
				if(highest > mapArea[x+1][y+1]) {
					highest = mapArea[x+1][y+1];
					mapArea[x+1][y+1] = testNum;
					
					x++;
					y++;
				}
			}
			
			//Search SW
			if(!((x+1 >= mapArea.length) && (y-1 <= 0))) {
				if(highest > mapArea[x+1][y-1]) {
					highest = mapArea[x+1][y-1];
					mapArea[x+1][y-1] = testNum;
					
					x++;
					y--;
				}
			}
		}
		
	}
	
	public int getHighest() {
		return highest;
	}
	// </ v0.6.1 >
	public void setRoute() {
		//heatMap
		planRoutes(rStart);
		//TODO: get fastest route
		fastTravel();
		view = new MapViewer(mapArea);
		try {
			out.toFile(mapArea, "full-routeMap.txt", "Vehicle", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
