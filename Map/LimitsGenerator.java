package map;
import io.OutputFile;
import java.io.IOException;

public class LimitsGenerator {
	
	private OutputFile out = new OutputFile();

	private Integer[][] mapArea;
	private double[][][] wallData;
	private double slope;
	private int wallNumber;
	private int wallSize;
	private int xMax;
	private int yMax;
	private int numOfWalls;
	private String output= "";
	
	//CONSTRUCTOR 
	public LimitsGenerator() {
		numOfWalls = 0;
		//xMax = yMax = 100;
		//wallSize = 10;
		
		//EDIT v0.3 - deleted
		//mapArea   = new Integer[xMax][yMax];  
		//Slope still having value of -1 means error of calculation
		//Test it by measuring if the slope is 0 (horizontal) or Infinity (vertical)
		//For integer use m = (double)(<slope formula>);
		slope = -1;
		//For the counter to know how many walls were created
		wallNumber = 0;
		wallData = null;
	}
	//creates empty map with side borders
	private void createBorderedMap() {
		
		
		for(int i=0; i < xMax; i++) {
			for(int j=0; j < yMax; j++) {
				//Make empty area
				mapArea[i][j] = 0;
				
				//Make borders
				// EDIT v0.3  THIS SHOULD BE if((i==0)||(j==0)||(i==xMax-1)||(j==yMax-1)) { ... } 
				if((i==0)||(j==0)||(i==xMax-1)||(j==yMax-1))
					mapArea[i][j] = 1;
			}			
		}
	}
	
	public void outputMapArea() {
		for(int i=0; i< xMax; i++) {
			for(int j=0; j < yMax; j++) {
				System.out.print(mapArea[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean findSpace(int x, int y) {
		//determines if point x on map is free (0)
		boolean isFree = false;
		if(mapArea[x][y] == 0)
			isFree = true;
		
		return isFree;
	}
	
	private Integer generateXY() {
		//Math.random()*x = one random number from [0, .99) multiplied by x. 
		//Map should have borders at all edges. createBorderedMap() takes care of it.
		
		//V0.3 EDIT.  Why [11, 99] and not [10, 89] to fill a 100/100 map instead of 101/101
		// DO NOT DELETE:  int xy = (int)(Math.random()*(80))+10;
		//int xy = (int)(Math.random()*(80+1))+10;
		//above return a value between [11, 89]   eliminate the 1 from (80+1) to give [10, 89]
		
		//Is this crashing the program?
		int xy = (int)(Math.random()*(mapArea.length-(2*wallSize + 1))+wallSize-1);
		
		return xy;
	}
	
	private Integer[][] wallPosition() {
		//Test if building a wall will be inside the map area. Any x/y should be 10 units away from any margin.
		//xLimit/yLimit tells if starting point of a wall is <wallSize> away from margin
		//xMax = yMax = 100;
		//wSize = 10;
		
		Integer coordinates[][] = new Integer[1][2];
		boolean freeSpace = false;
		int xPos = -1;
		int yPos = -1;
		
		while(freeSpace == false) {
			xPos =  generateXY();
			yPos =  generateXY();
			freeSpace = findSpace(xPos, yPos);
		}
		//If output is -1, consider error
		coordinates[0][0] = xPos;
		coordinates[0][1] = yPos;
				
		return coordinates;	
	}
	
	
	//UPDATE IN v0.3 - DELETED TRUMP();  Left void trump(int ...)
	//SELECTIVE NUMBER OF WALLS
	private void trump(int numOfWalls) throws IOException {
		//Updated in v0.3 to work with input data
		this.numOfWalls = numOfWalls;
		if(numOfWalls == 0) this.numOfWalls = (int)(Math.random()*50);
		//Draws a line of wallSize longitude/latitude
		Integer[][] coordinates = new Integer[1][2];
		int xPos, yPos;
		wallData = new double[this.numOfWalls][3][1];
		for(int iteration = 0; iteration < this.numOfWalls; iteration++) {
			coordinates = wallPosition();
			//Wall starts here
			xPos = coordinates[0][0];
			yPos = coordinates[0][1];
			//0 = origin to x
			//1 = origin to y
			//2 = origin to -x
			//3 = origin to -y
			//4 = oblique with slope .5
			//5 = oblizue with slope -.5
			int direction = (int)(Math.random()*6);
			
			for(int k=0; k<wallSize; k++) {
				switch (direction) {
				case 0:
					mapArea[xPos][yPos] = 1;
					xPos++;
					slope = 0;
					break;
				case 1:
					mapArea[xPos][yPos] = 1;
					yPos++;
					slope = 1;
					break;
				case 2:
					mapArea[xPos][yPos] = 1;
					xPos--;
					slope = -0;
					break;
				case 3:
					mapArea[xPos][yPos] = 1;
					yPos--;
					slope = -1;
					break;
				
				case 4:
					mapArea[xPos][yPos] = 1;
					xPos++;
					yPos--;
					slope = 0.5;
					break;
				
				case 5:
					mapArea[xPos][yPos] = 1;
					slope = -0.5;
					xPos--;
					yPos--;
					break;
				}
			}
			wallData[wallNumber][0][0] =coordinates[0][0];
			wallData[wallNumber][1][0] =coordinates[0][1];
			wallData[wallNumber][2][0] = slope;
			wallNumber++;
		}
	}
	
	public int getWallNumber() {
		return wallNumber;
	}
	//Testing mapArea
	private void buildMap(int numOfWalls, int mapSize, int wallSize) throws IOException {
		//Set limits v0.3
		xMax = yMax = mapSize;
		this.wallSize = wallSize; 
		mapArea   = new Integer[xMax][yMax];  
		createBorderedMap();
		output += "Empty map created.\n";
		trump(numOfWalls);
		output += "Walls added.\n";
		output += "Event: " + getWallNumber()  + " walls created.\n";
		out.toFile("Map size: " + xMax +  " by " + yMax + ". \n Wall size : " + this.wallSize + "\n", "log.txt", "log", false);
		System.out.println("Map size: " + xMax +  " by " + yMax + ".  Wall size : " + this.wallSize);
		//For test only - Note: This outputs a 101/101 matrix on the console.
		//outputMapArea();
		System.out.println(mapArea.length);
		System.out.println(mapArea[0].length);
		//Export mapArea methods
		//NEW  
		//out.toFile(MESSAGE, FILE.EXTENSION, APPEND 
		out.toFile(mapArea, "mapArea.txt", "Map", false);
		output += "Map data exported to .txt file\n";
		//NEW
		out.toFile(wallData, "wallData.txt", "Map", false);
		output += "Walls data exported to .txt file\n";
		//NEW
		out.toFile(output, "log.txt", "log", false);
	}
	
	public void buildTestWall(int numOfWalls, int mapSize, int wallSize) throws IOException {
		buildMap(numOfWalls, mapSize, wallSize);
	}

	
	public Integer[][] getMap() {
		return mapArea;
	}
	
	public double[][][] getWallData() {
		return wallData;
	}

}