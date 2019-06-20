
public class Pair {
	Point point1;
    Point point2;
    double distance = 0;
    
    public Pair(){}
 
    public Pair(Point point1, Point point2){
      this.point1 = point1;
      this.point2 = point2;
      updateDistance();
    }
 
    public Pair(Pair pr) {
    	this.point1 = pr.point1;
        this.point2 = pr.point2;
        updateDistance();
	}

	public void updateDistance(){  
    	double xDistance = point2.x - point1.x;
	     double yDistance = point2.y - point1.y;
	     this.distance = Math.hypot(xDistance, yDistance); 
    }
    
    public void updatePair(Point point1, Point point2) {
    	this.point1 = point1;
        this.point2 = point2;
        updateDistance();
    }
    
    public void setPairData(Point point1, Point point2, double distance) {
    	this.point1 = point1;
    	this.point2 = point2;
    	this.distance = distance;
    }
    
    public String toString() {
    	return "[" + point1 + ", " + point2 + " ds: "+ distance+ "]";
    }

}
