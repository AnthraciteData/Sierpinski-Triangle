//Javier Sanchez Sanchez

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Integer n = 0; 
		
		ArrayList <point>cPoints = new ArrayList<point>();
		
		ArrayList <String>iString = new ArrayList<String>();
		
		ArrayList <Double>nString = new ArrayList<Double>();
			
		int check = 0;
		
		try {
			File input = new File("input.txt");
		      Scanner sc = new Scanner(input);
		      n = Integer.parseInt(sc.nextLine());
		      while (sc.hasNextLine()) {
		    	  String atLine  = sc.nextLine();
		    	  String [] Nums = atLine.split(" ");
		    	  
		    	  for(String i : Nums ) {
		    		nString.add(Double.parseDouble(i));
		    	  }
		    	  
		      }
		      sc.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		for (int x = 0 , y = 1 ; x < nString.size() && y <=nString.size() ; x+=2 , y+=2) {
			cPoints.add(new point(nString.get(x),nString.get(y)));
		}
		
		PrintWriter pw = null;
	
		try {
			pw = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		
		ArrayList <point>nth = new ArrayList<point>();
		ArrayList <point>nPoints = new ArrayList<point>();
		
		nth.add(new point(0, 0));
		nth.add(new point(1, 0));
		
		

		int times  = 0 ; 
		int firstA = 0 ; 
		int secondA = 0 ;
	
		for (int nT = 0 ; nT < n ; nT++) 
		{
			
			for(point b : nth) {
				nPoints.add(b.pF().pR(60));
				firstA = nPoints.size() - 1;
				
			}
			
			for(point b : nth) {
				nPoints.add(b.conectP(nPoints.get(firstA)));
				secondA = nPoints.size()-1;
				
			}
			
			for(point b : nth) {
				nPoints.add(b.pF().pR(300).conectP(nPoints.get(secondA)));
			}
			
			nth.clear();
			nth.addAll(nPoints);
			nPoints.clear();
			
		}
					
		ArrayList <Integer>results = new ArrayList<Integer>();
		
		for (int p0 = 0 , p1 = 1 ; p0 < cPoints.size() && p1 <=cPoints.size() ; p0+=2 , p1+=2) {
						
			for (int p2 = 0 , p3 = 1 ; p2 < nth.size() && p3 <= nth.size() ; p2+=2 , p3+=2 ) {
				
				results.add(lineInter(cPoints.get(p0), cPoints.get(p1), nth.get(p2),nth.get(p3)));
			}
			
			ArrayList<Integer>outPuts = new ArrayList<Integer>();

			for(int k = 0 ; k < results.size() ; k++) {
				
				if (results.get(k) == 1) {
					pw.println(1);
					results.clear();
					break;
				}
				
				 if(k == results.size()-1) {
					 pw.println(0);
					results.clear();
					break;
				}
			}		
		}
		pw.close();
	}
	
	public static int  lineInter(point p0, point p1 , point p2 , point p3) {
		double A1, B1 , C1 , A2 , B2 , C2 , denominator, x , y,maxX1, maxY1 , maxX2 , maxY2 ;
	
	
			A1 = p1.y - p0.y;
			B1 = p0.x - p1.x;
			C1 = A1 * p0.x + B1 * p0.y;
			A2 = p3.y - p2.y;
			B2 = p2.x - p3.x;
			C2 = A2 * p2.x + B2 * p2.y;
			denominator = A1 * B2 - A2 * B1;
			x = (B2 * C1 - B1 * C2) / denominator;
			y = (A1 * C2 - A2 * C1) / denominator;
			maxX1 = (x - p0.x) / (p1.x - p0.x);
			maxY1 = (y - p0.y) / (p1.y - p0.y);
			maxX2 = (x - p2.x) / (p3.x - p2.x);
			maxY2 = (y - p2.y) / (p3.y - p2.y);
			
			
			
			if (((maxX1 >= 0 && maxX1 <= 1)||(maxY1 >= 0 && maxY1 <= 1))
					&& ((maxX2 >= 0 && maxX2 <= 1)||(maxY2 >= 0 && maxY2 <= 1))) {
				return 1;
				
			}
			
			else
				return 0 ;			
}
	
}

class point {
	
	double x;
	double y; 
	
	public  point (double x,double y) {
		this.x = x; 
		this.y = y;
	}

	public void printP() {
		
		System.out.println("("+x+","+y+")");
		
	}
	
	public point pF() { 
		double nY = -y;
		return new point(this.x, nY);
	}
	
	
	
	
	
	public point pR(double d) {
		
		DecimalFormat df = new DecimalFormat("###.###");
		
		double mX = x*Math.cos(Math.toRadians(d)) - y*Math.sin(Math.toRadians(d));
		
		double mY = x*Math.sin(Math.toRadians(d)) + y*Math.cos(Math.toRadians(d));
		
		
		return new point(Math.round(mX*1000.0)/1000.0,Math.round(mY*1000.0)/1000.0);
		
	}
	
	
	
	public point nX (double i) {
		
		return new point(x+i,y);
	}
	

	
	public point nY (double i) {
		return new point(x,y + 1);
	}
	
	public point conectP  (point p) {
		
		double nX = x + p.x;
		
		double nY = y + p.y; 
		
		return new point(nX,nY);
		
	}
	
	
}








