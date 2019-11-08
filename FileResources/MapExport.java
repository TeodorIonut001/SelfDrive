package io;

import java.io.*;
import java.text.DecimalFormat;

public class MapExport {
	
	private static DecimalFormat df;
	
	public MapExport() {
		df = new DecimalFormat(".#");
	}
	
	//FOR INTEGER[][]
	private void outputIntegerMatrix(Integer[][] mapArea, String fileName) throws IOException{
		
		String path = fileName + ".txt";
		File file = new File(path);
		//Create new file
		try(FileOutputStream fout = new FileOutputStream(file)){
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
			for(int i=0; i < mapArea.length; i++) {
				for(int j=0; j < mapArea[0].length; j++) {
					bw.write(mapArea[i][j].toString()+ ",");
					}
				bw.newLine();
				}
			bw.close();
			}	
		}
		
	//FOR INT[][]
	private void outputMatrix(int[][] message, String fileName) throws IOException{
		File file = new File(fileName);
		if(!file.exists()) 
			file.createNewFile();
		PrintWriter pw = new PrintWriter(file);
		for(int i=0; i < message.length; i++) {
			for(int j=0; j < message[0].length; j++) {
				pw.print(message[i][j] + " " );
			}
			pw.println();
		}			
		pw.close();
	}
		
	//FOR DOUBLE[][][]
	private void outputMatrix(double[][][] message, String fileName) throws IOException{
		File file = new File(fileName);
		if(!file.exists()) 
			file.createNewFile();
		PrintWriter pw = new PrintWriter(file);
		for(int i=0; i < message.length; i++) {
			for(int j=0; j < message[0].length; j++) {
				for(int k=0; k < message[0][0].length; k++)
					pw.print("(" + i + ") " + df.format(message[i][j][k]).toString() + ",			" );
			}
			pw.println();
		}		
		pw.close();
	}
		
	
	public void getFileOutput(Integer[][] mapArea, String fileName) throws IOException{
		outputIntegerMatrix(mapArea, fileName);
	}
	
	public void getFileOutput(int[][] message, String fileName) throws IOException{
		outputMatrix(message, fileName);
	}
	
	public void getFileOutput(double[][][] message, String fileName) throws IOException{
		outputMatrix(message, fileName);
	}
}
