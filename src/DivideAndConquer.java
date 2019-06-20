import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DivideAndConquer {
	
	static int xMiddle;
	
	public static void bubbleSort(ArrayList<Point> list, char selector) {
		if(selector == 'x') { //sorting by X
			for(int i = 0; i < list.size()-1; i++) {//will move biggest number to end
				for (int j = 0; j < list.size()-i-1; j++) {
					if (list.get(j).getX() > list.get(j+1).getX()){ //if bigger swap
						Point temp = list.get(j);
						list.set(j, list.get(j+1)); 
						list.set(j+1, temp); 
	                }
				}
			}
		}
		
		if(selector == 'y') {//sort by Y
			for(int i = 0; i < list.size()-1; i++) {//same as X
				for (int j = 0; j < list.size()-i-1; j++) {
					if (list.get(j).getY() > list.get(j+1).getY()) 
	                { 
						Point temp = list.get(j);
						list.set(j, list.get(j+1)); 
						list.set(j+1, temp);  
	                }
				}
			}
		}
		
	}
	
	public static Pair simpleSearch(ArrayList<Point> list) {//faster results for Divide and Conquer
		if(list.size() < 2) {
			return null;
		}
		if(list.size() == 2) {
			return new Pair(list.get(0), list.get(1));
		}
		if(list.size() > 2) {
			
			return bruteForce(list);			
		}
		return null;
		}	
	    
	
	public static Pair DACDiver(ArrayList<Point> list){//start of Divide and Conquer
		ArrayList<Point> listByX = new ArrayList<Point>(list);
		bubbleSort(listByX, 'x'); // sort by X
		ArrayList<Point> listByY = new ArrayList<Point>(list);
		bubbleSort(listByY, 'y'); //sort by Y		
		xMiddle = list.get(list.size()/2).x;
		Pair xclosest =  dacRecursive(listByX); //find the closest Pair regarding the x position
		
	    ArrayList<Point> middle = new ArrayList<Point>();//find points that are within the minimum Distance from the middle
	    for (Point p : listByY) {
	        if ((int) Math.abs(xMiddle - p.x) <= xclosest.distance) 
	        	middle.add(p);
	    }
	    Pair yclosest = null;
	    if(middle.size() > 1) {//should this be bigger, check it with bruteForce, as it will most of the time be small
	    	yclosest = bruteForce(middle);
	    }
	    if(yclosest != null && yclosest.distance < xclosest.distance) {//see if there is a Pair within the middle that is smaller
	    	return yclosest;
	    }
	    else {
	    	return xclosest;
	    }
	    
	    
	}
		
		
	public static Pair dacRecursive(ArrayList<Point> listByX){//the recursive search for min distance X
		int xMidPoint = listByX.size()/2;
		if(listByX.size() <= 3) {//Lists of this size don't need a complex algorithm
			return simpleSearch(listByX);
		}
		
		//create a list of the left side
		ArrayList<Point> left = new ArrayList<Point>();
		List<Point> leftSub= listByX.subList(0, xMidPoint +1);
		for(Point p : leftSub) {
			left.add(p);
		}
		
		//create a list of the right side
		ArrayList<Point> right = new ArrayList<Point>();
		List<Point> rightSub= listByX.subList(xMidPoint , listByX.size());
		for(Point p : rightSub) {
			right.add(p);
		}
				
		//find the closest points in the left
		ArrayList<Point> findInLeft = new ArrayList<Point>(left);
		Pair closestLeft = dacRecursive(findInLeft);
		
		
		//find the closest points in the right
		ArrayList<Point> findInRight = new ArrayList<Point>(right);
		Pair closestRight = dacRecursive(findInRight);
		
		//determine closest between left and right
		Pair closest = new Pair();
		if (closestLeft.distance <  closestRight.distance) {
			closest = closestLeft;
		}
		else {
			closest = closestRight;
		}
		return closest;
	      
		
		
	}
	
	public static Pair bruteForce(ArrayList<Point> list) {//compare every possible pair
		double minDist = Integer.MAX_VALUE;
		Point p = list.get(0);
		Point q = list.get(1);
		Pair pq = new Pair(p, q);
		Pair closestPair = pq;
		for(int i = 0; i < list.size() - 1; i++){
			for(int j = i + 1; j < list.size();j++) {
				p = list.get(i);
				q = list.get(j);
				pq.updatePair(p, q);
				if (pq.distance < minDist) {
					minDist = pq.distance;
					closestPair = new Pair(pq);
				}
			}
					  
		}
		return closestPair;
	}
	
	//utility debug for printing a List of Points
	public static void print(ArrayList<Point> list) {
		for(Point p : list) {
			System.out.println("[" + p.x + ", " + p.y + "]");
		}
	}

	public static void main(String[] args) {
		//big block for input handling and error prevention
		if(args.length < 2) {
			System.out.println("Your call was invalid: programm [int NumberOfItems] [int RadomNumberRangeMax]");
		}
		try
	    {
	        Integer.parseInt(args[0]);
	        Integer.parseInt(args[1]);
	    } catch (NumberFormatException ex)
	    {
	        System.out.println("Your call was invalid: programm [int NumberOfItems] [int RadomNumberRangeMax]");
	    }
		
		
		int unitNumber = Integer.parseInt(args[0]);
		ArrayList<Point> PointList= new ArrayList<Point>(); //create List
		Random rnd = new Random();
		rnd.setSeed(System.currentTimeMillis()); //make a good random
		for(int i = 0; i < unitNumber; i++)
		{    
			PointList.add(new Point(Math.abs(rnd.nextInt() % Integer.parseInt(args[1])),
					Math.abs(rnd.nextInt() % Integer.parseInt(args[1]) ))); 
			//put random Point in List
			//points are positive
		}
		
		System.out.println("Created List");
		System.out.println(PointList.toString());
		bubbleSort(PointList, 'x');
		System.out.println("Sorted List by x");
		System.out.println(PointList.toString());
		System.out.println("Sorted List by y");
		bubbleSort(PointList, 'y');
		System.out.println(PointList.toString());		;
		System.out.println("Divide and Conquer result is: " + DACDiver(PointList).toString()); //call the Algorithm
		System.out.println();
		System.out.println("Brute force result");
		System.out.println(bruteForce(PointList).toString());		
		System.out.println();
		System.out.println("run again for differnt numbers");
		}

}
