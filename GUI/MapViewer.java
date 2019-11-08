package gui;

import vehicle.PathFinder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapViewer extends JPanel{
	
	private PathFinder path = new PathFinder();
	private static final long serialVersionUID = 1L;
	private Integer[][] mapArea;
	//ALB
	private Color freeSpace = new Color(255, 255, 255);
	//ALBASTRU
	private Color wallSpace = new Color(66, 78, 198);
	//ROSU
	private Color vehicle   = new Color(238,50,49);
	private int width, height;
	private Color heatMap;
	//Windows size compared to mapArea.length, proportion = 8 by default. //RoadMap tests > 30.
	private int proportion = 8;
	private int size;
	private int highest;
	
	public MapViewer(Integer[][] map) {

		mapArea = map;
		//window size in pixels
		width  = (mapArea.length* proportion);
		height = (mapArea.length* proportion);
		//tile size in pixels
		size = proportion;
		highest = path.getHighest();
		JFrame frame = new JFrame();
		setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		frame.setTitle("MapView 0.1");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	
	}
	
	public MapViewer() {}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0,  0,  width, height);	
		for(int i=0; i<mapArea.length; i++) {
			for(int j=0; j<mapArea.length; j++) {
				if(mapArea[i][j] == 0) {
					g.setColor(freeSpace);
					g.fillRect(i*size, j*size, size, size);
				}
				
				if(mapArea[i][j] == 1) {
					g.setColor(wallSpace);
					g.fillRect(i*size, j*size, size, size);
				}
				
				if(mapArea[i][j] == 2) {	

					g.setColor(Color.GREEN);
					g.fillRect(i*size, j*size, size, size);
				}
				
				if(mapArea[i][j] == highest) {
					g.setColor(Color.YELLOW);
					g.fillRect(i*size, j*size, size, size);

				}
				
				if(mapArea[i][j] != 2 && mapArea[i][j] != 1 && mapArea[i][j] != -1 && mapArea[i][j] != -0 && mapArea[i][j] != highest) {
					//HEATMAP  (mapArea[i][j]*2,  50,  100)
					heatMap = new Color(mapArea[i][j]*2,  50,  100);
					g.setColor(heatMap);
					g.fillRect(i*size, j*size, size, size);
				}
				
			}
		}//end 2x four

		
	}
}
