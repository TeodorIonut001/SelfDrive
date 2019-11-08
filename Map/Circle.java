package map;

public class Circle{
//	cercul lui Bresenham
	 private char[][] px;
	    private char cEmpty='0';
	    private char cFilled='1';
	    
	    public static void main(String[] args) {
	        new Circle(20);
	    }
	    
	    public Circle(int size)
	    {
	        px=new char[size][size];
	        for(int i=0;i<size;i++)
	            for(int j=0;j<size;j++)
	                px[i][j]=cEmpty;
	        calc(size/2,size/2,size/2-1);
	        for(int i=0;i<size;i++){
	            for(int j=0;j<size;j++)
	                System.out.print(px[i][j]);
	            System.out.println();
	        }
	    }

	    public void calc(int cx, int cy, int radius)
	    {
	      int error = -radius;
	      int x = radius;
	      int y = 0;
	      while (x >= y)
	      {
	        plot8points(cx, cy, x, y);   
	        error += y;
	        ++y;
	        error += y;
	        if (error >= 0)
	        {
	          --x;
	          error -= x;
	          error -= x;
	        }
	      }
	    }

	    void plot8points(int cx, int cy, int x, int y)
	    {
	      plot4points(cx, cy, x, y);
	      if (x != y) plot4points(cx, cy, y, x);
	    }
	    void plot4points(int cx, int cy, int x, int y)
	    {
	      setPixel(cx + x, cy + y);
	      if (x != 0) setPixel(cx - x, cy + y);
	      if (y != 0) setPixel(cx + x, cy - y);
	      if (x != 0 && y != 0) setPixel(cx - x, cy - y);
	    }
	    void setPixel(int x, int y){
	        px[x][y]=cFilled;
	    }
}
