
package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D point in the plane.
 * Ex4: you should implement the scale and the rotation methods (see below).
 * @author boaz.benmoshe
 */


//Adam Sin-322453689, Tomer Shor-325511541
public class Point2D{
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point2D ORIGIN = new Point2D(0,0);
    private double _x,_y;
    public Point2D(double x,double y) {
    	_x=x; _y=y;
    }
    public Point2D(Point2D p) {
       this(p.x(), p.y());
    }
    public Point2D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
            throw(e);
        }
    }
    public double x() {return _x;}
    public double y() {return _y;}
 
    public int ix() {return (int)_x;}
    public int iy() {return (int)_y;}
  
    public Point2D add(Point2D p) {
    	Point2D a = new Point2D(p.x()+x(),p.y()+y());
    	return a;
    }
    public String toString()
    {
        return _x+","+_y;
    }

    public double distance()
    {
        return this.distance(ORIGIN);
    }
    public double distance(Point2D p2)
    {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double t = (dx*dx+dy*dy);
        return Math.sqrt(t);
    }
    @Override
    public boolean equals(Object p)
    {
        if(p==null || !(p instanceof Point2D)) {return false;}
        Point2D p2 = (Point2D)p;
        return ( (_x==p2._x) && (_y==p2._y));
    }
    public boolean close2equals(Point2D p2, double eps)
    {
        return ( this.distance(p2) < eps );
    }
    public boolean close2equals(Point2D p2)
    {
        return close2equals(p2, Ex4_Const.EPS);
    }
    /**
     * This method returns the vector between this point and the target point. The vector is represented as a Point2D.
     * @param target
     * @return
     */
    public Point2D vector(Point2D target) {
    	double dx = target.x() - this.x();
    	double dy = target.y() - this.y();
    	return new Point2D(dx,dy);
    }
	
	public void move(Point2D vec) {
		this._x += vec.x();
		this._y += vec.y();
	}
	/**
	 * This method gets a point and a ratio and brings the point
	 * the method is applied on further or closer to the point given
	 * by the ratio given.
	 * @param cen, ratio
	 * @return
	 */
	public void scale(Point2D cen, double ratio) {
		//////////add your code below ///////////
		Point2D vec= new Point2D((cen.x()-_x)/10, (cen.y()-_y)/10);
		if (ratio==1.1)
			vec = new Point2D(-1*vec.x(),-1*vec.y());
		this._x += vec.x();
		this._y += vec.y();
		/////////////////////////////////////////
	}
	/**
	 * This method gets a point and an angle and rotates the point
	 * the method is applied around the point given by the degrees of the 
	 * angle given.
	 * @param cen, angleDegrees
	 */
	public void rotate(Point2D cen, double angleDegrees) {
		//////////add your code below ///////////
		double r=cen.distance(new Point2D(_x,_y));
		double beta=Math.atan((_y-cen.y())/(_x-cen.x()));
		if(cen.x()>_x) {
			beta=Math.PI+beta;
		}
		this._x = cen.x()+r*Math.cos(angleDegrees+beta);
		this._y = cen.y()+r*Math.sin(angleDegrees+beta);
		/////////////////////////////////////////
	}
   
}