package io;

import java.io.*;

//READ ME
// fileMatcher(STRING filename) :  Return a boolean: true/false if a .txt file is found

// writeToFile(STRING message, STRING filename) : creates in directory a file containing
// 				message of choice

//toFile(STRING message, STRING fileName, STRING directory)
//				does the same thing, but organizes files in specific folders
//				folders are created in master directory and subdirectoy
//				USE "\\" TO CREATE A SUBFOLDER 
//TODO:
//toFile(INTEGER[][] message, STRING fileName, STRING DIRECTORY, boolean append)
//				does the same thing, but with a double Integer matrix

//toFile(int[][] message, STRING fileName, STRING DIRECTORY, boolean append)
//does the same thing, but with a 2D int matrix

//toFile(DOUBLE[][][] message, STRING fileName, STRING DIRECTORY, boolean append)
//does the same thing, but with a 3D int matrix
// WRITES AS STRING

//toFile(INT[][][] message, STRING fileName, STRING DIRECTORY, boolean append)
//does the same thing, but with a 3D int matrix
//WRITES AS INT

//Change FileWriter(file, true));   - the "true" to "false" to switch append "on" / "off"

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

public class OutputFile {

	
	// - FOLDER\\SUBFOLDER - important to use the double " // "
	
	
	public boolean fileMatcher(String fileName) {
		PathMatcher matcher = 
				FileSystems.getDefault().getPathMatcher("glob:*.{txt}");
		Path path = Paths.get(fileName);
		boolean fileState = false;
		if(matcher.matches(path)) {
			System.out.println("Found file!");
			fileState = true;
		}else {
			System.out.println("File not found!");
		}
		//Edit1
		return fileState;
	}
	
	//Writes a file in master directory
	public void writeToFile(String message, String fileName) throws IOException {
		File file = new File(fileName);
		if(!file.exists()) 
			file.createNewFile();	
		PrintWriter pw = new PrintWriter(file);
		String[] out =message.split(";");
		for (String o : out) {
			pw.println(o);
		}
			pw.close();
	}
	
	//this took forever to make
	public void toFile(String message, String fileName, String directory, boolean append) throws IOException {
		//find master directory
		String dir = System.getProperty("user.dir");
		//add path to new folder
		dir += "\\" + directory;
		Path path = Paths.get(dir);
		
		
		if(!Files.exists(path)) {
			Files.createDirectory(path);
			System.out.println("New directory at " +dir);
			
			//create file
			File file = new File(dir, fileName);
			if (file.createNewFile())
			{
			    System.out.println("File is created!");  
				try { 
		            // Open given file in append mode. 
		            BufferedWriter out = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            out.write(message); 
		            out.close(); 
		        } 
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }

			} else {
			    System.out.println("File already exists.");	 
			    try { 
		            // Open given file in append mode. 
		            BufferedWriter out = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            out.write(message); 
		            out.close(); 
		        } 
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }
			}
			
		}else {
			System.out.println("Directory already exists for " + dir);
			//create file
			File file = new File(dir, fileName);
			if (file.createNewFile())
			{
			    System.out.println("File is created!");
			    try { 
		            // Open given file in append mode. 
		            BufferedWriter out = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            out.write(message); 
		            out.close(); 
		        } 
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }

			} else {
			    System.out.println("File already exists.");
			    try { 
		            // Open given file in append mode. 
		            BufferedWriter out = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            out.write(message); 
		            out.close(); 
		        } 
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }   
			}
		}			
	}
	
	//FOR INTEGER[][] MAP EXPORT
	public void toFile(Integer[][]mapArea, String fileName, String directory, boolean append) throws IOException {

		//find master directory
		String dir = System.getProperty("user.dir");
		//add path to new folder
		dir += "\\" + directory;
		Path path = Paths.get(dir);
		
		
		if(!Files.exists(path)) {
			Files.createDirectory(path);
			System.out.println("New directory at " +dir);
			
			//create file
			File file = new File(dir, fileName);
			if (file.createNewFile())
			{
			    System.out.println("File is created!");  
				try { 
		            // Open given file in append mode. 
		            BufferedWriter bw = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            for(int i=0; i < mapArea.length; i++) {
						for(int j=0; j < mapArea[0].length; j++) {
							bw.write(mapArea[i][j].toString());
							}
						bw.newLine();
						}
					bw.close();
					}	 
		         
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }

			} else {
			    System.out.println("File already exists.");	 
			    try { 
		            // Open given file in append mode. 
		            BufferedWriter bw = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            for(int i=0; i < mapArea.length; i++) {
						for(int j=0; j < mapArea[0].length; j++) {
							bw.write(mapArea[i][j].toString());
							}
						bw.newLine();
						}
					bw.close();
					}	 
		         
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }

			}
			
		}else {
			System.out.println("Directory already exists for " + dir);
			//create file
			File file = new File(dir, fileName);
			if (file.createNewFile())
			{
			    System.out.println("File is created!");
			    try { 
		            // Open given file in append mode. 
		            BufferedWriter bw = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            for(int i=0; i < mapArea.length; i++) {
						for(int j=0; j < mapArea[0].length; j++) {
							bw.write(mapArea[i][j].toString());
							}
						bw.newLine();
						}
					bw.close();
					}	
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }

			} else {
			    System.out.println("File already exists.");
			    try { 
		            // Open given file in append mode. 
		            BufferedWriter bw = new BufferedWriter( 
		                   new FileWriter(file, append)); 
		            for(int i=0; i < mapArea.length; i++) {
						for(int j=0; j < mapArea[0].length; j++) {
							bw.write(mapArea[i][j].toString());
							}
						bw.newLine();
						}
					bw.close();
					}	
		        catch (IOException e) { 
		            System.out.println("exception occoured" + e); 
		        }   
			}
		}			
	}
	
	//FOR INT[][]  MAP EXPORT
		public void toFile(int[][]mapArea, String fileName, String directory, boolean append) throws IOException {
			//find master directory
			String dir = System.getProperty("user.dir");
			//add path to new folder
			dir += "\\" + directory;
			Path path = Paths.get(dir);
			
			
			if(!Files.exists(path)) {
				Files.createDirectory(path);
				System.out.println("New directory at " +dir);
				
				//create file
				File file = new File(dir, fileName);
				if (file.createNewFile())
				{
				    System.out.println("File is created!");  
					try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
							for(int j=0; j < mapArea[0].length; j++) {
								bw.write(mapArea[i][j]);
								}
							bw.newLine();
							}
						bw.close();
						}	 
			         
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }

				} else {
				    System.out.println("File already exists.");	 
				    try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
							for(int j=0; j < mapArea[0].length; j++) {
								bw.write(mapArea[i][j]);
								}
							bw.newLine();
							}
						bw.close();
						}	 
			         
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }

				}
				
			}else {
				System.out.println("Directory already exists for " + dir);
				//create file
				File file = new File(dir, fileName);
				if (file.createNewFile())
				{
				    System.out.println("File is created!");
				    try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
							for(int j=0; j < mapArea[0].length; j++) {
								bw.write(mapArea[i][j]);
								}
							bw.newLine();
							}
						bw.close();
						}	
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }

				} else {
				    System.out.println("File already exists.");
				    try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
							for(int j=0; j < mapArea[0].length; j++) {
								bw.write(mapArea[i][j]);
								}
							bw.newLine();
							}
						bw.close();
						}	
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }   
				}
			}			
		}
		
		//FOR DOUBLE[][][]  MAP EXPORT
		public void toFile(double[][][] mapArea, String fileName, String directory, boolean append) throws IOException {
			//find master directory
			String dir = System.getProperty("user.dir");
			//add path to new folder
			dir += "\\" + directory;
			Path path = Paths.get(dir);
			
			
			if(!Files.exists(path)) {
				Files.createDirectory(path);
				System.out.println("New directory at " +dir);
				
				//create file
				File file = new File(dir, fileName);
				if (file.createNewFile())
				{
				    System.out.println("File is created!");  
					try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
							for(int j=0; j < mapArea[0].length; j++) {
								for(int k=0; k < mapArea[0][0].length; k++) {
									bw.write(mapArea[i][j][k] + "		");
									}
								}
							bw.newLine();
							}
						bw.close();
						}	 
			         
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }

				} else {
				    System.out.println("File already exists.");	 
				    try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
			            	for(int j=0; j < mapArea[0].length; j++) {
								for(int k=0; k < mapArea[0][0].length; k++) {
									bw.write(mapArea[i][j][k]+ "");
									}
								}
							bw.newLine();
							}
						bw.close();
						}	        
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }
				}	
			}else {
				System.out.println("Directory already exists for " + dir);
				//create file
				File file = new File(dir, fileName);
				if (file.createNewFile())
				{
				    System.out.println("File is created!");
				    try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
			            	for(int j=0; j < mapArea[0].length; j++) {
								for(int k=0; k < mapArea[0][0].length; k++) {
									bw.write(mapArea[i][j][k]+ "");
									}
								}
							bw.newLine();
							}
						bw.close();
						}	
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }
				} else {
				    System.out.println("File already exists.");
				    try { 
			            // Open given file in append mode. 
			            BufferedWriter bw = new BufferedWriter( 
			                   new FileWriter(file, append)); 
			            for(int i=0; i < mapArea.length; i++) {
			            	for(int j=0; j < mapArea[0].length; j++) {
								for(int k=0; k < mapArea[0][0].length; k++) {
									bw.write(mapArea[i][j][k]+ "");
									}
								}
							bw.newLine();
							}
						bw.close();
						}	
			        catch (IOException e) { 
			            System.out.println("exception occoured" + e); 
			        }   
				}
			}			
		}
		
		//FOR INT[][][]  MAP EXPORT
				public void toFile(int[][][] mapArea, String fileName, String directory, boolean append) throws IOException {
					//find master directory
					String dir = System.getProperty("user.dir");
					//add path to new folder
					dir += "\\" + directory;
					Path path = Paths.get(dir);
					
					
					if(!Files.exists(path)) {
						Files.createDirectory(path);
						System.out.println("New directory at " +dir);
						
						//create file
						File file = new File(dir, fileName);
						if (file.createNewFile())
						{
						    System.out.println("File is created!");  
							try { 
					            // Open given file in append mode. 
					            BufferedWriter bw = new BufferedWriter( 
					                   new FileWriter(file, append)); 
					            for(int i=0; i < mapArea.length; i++) {
									for(int j=0; j < mapArea[0].length; j++) {
										for(int k=0; k < mapArea[0][0].length; k++) {
											bw.write(mapArea[i][j][k]);
											}
										}
									bw.newLine();
									}
								bw.close();
								}	       
					        catch (IOException e) { 
					            System.out.println("exception occoured" + e); 
					        }

						} else {
						    System.out.println("File already exists.");	 
						    try { 
					            // Open given file in append mode. 
					            BufferedWriter bw = new BufferedWriter( 
					                   new FileWriter(file, append)); 
					            for(int i=0; i < mapArea.length; i++) {
					            	for(int j=0; j < mapArea[0].length; j++) {
										for(int k=0; k < mapArea[0][0].length; k++) {
											bw.write(mapArea[i][j][k]);
											}
										}
									bw.newLine();
									}
								bw.close();
								}	 		         
					        catch (IOException e) { 
					            System.out.println("exception occoured" + e); 
					        }
						}
						
					}else {
						System.out.println("Directory already exists for " + dir);
						//create file
						File file = new File(dir, fileName);
						if (file.createNewFile())
						{
						    System.out.println("File is created!");
						    try { 
					            // Open given file in append mode. 
					            BufferedWriter bw = new BufferedWriter( 
					                   new FileWriter(file, append)); 
					            for(int i=0; i < mapArea.length; i++) {
					            	for(int j=0; j < mapArea[0].length; j++) {
										for(int k=0; k < mapArea[0][0].length; k++) {
											bw.write(mapArea[i][j][k]);
											}
										}
									bw.newLine();
									}
								bw.close();
								}	
					        catch (IOException e) { 
					            System.out.println("exception occoured" + e); 
					        }
						} else {
						    System.out.println("File already exists.");
						    try { 
					            // Open given file in append mode. 
					            BufferedWriter bw = new BufferedWriter( 
					                   new FileWriter(file, append)); 
					            for(int i=0; i < mapArea.length; i++) {
					            	for(int j=0; j < mapArea[0].length; j++) {
										for(int k=0; k < mapArea[0][0].length; k++) {
											bw.write(mapArea[i][j][k]);
											}
										}
									bw.newLine();
									}
								bw.close();
								}	
					        catch (IOException e) { 
					            System.out.println("exception occoured" + e); 
					        }   
						}
					}			
				}
}

	