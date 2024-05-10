
package Exe.Ex4.geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class Segment2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;
	
	public Segment2D(Point2D p1, Point2D p2) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
	}
	public Segment2D(Segment2D s) {
		this._p1 = new Point2D(s.getP1());
		this._p2 = new Point2D(s.getP2());
	}
	public Point2D getP1() {return this._p1;}
	public Point2D getP2() {return this._p2;}
	 @Override
	public String toString() {return _p1.toString()+", "+_p2.toString();}
	 
	@Override
	/**
	 * This method gets a point and returns "true" if the points is inside the
	 * polygon the method is applied on, and "false" if it isn't.
	 * By checking if the point's distance from the points summed equals
	 * to the distance between the points or not.
	 * @param ot
	 * @return
	 */
	public boolean contains(Point2D ot) {
		double dis1= ot.distance(_p1);
		double dis2= ot.distance(_p2);
		double dis3= _p1.distance(_p2);
		return dis1+dis2<=dis3+0.001 && dis1+dis2>=dis3-0.001;
	}

	@Override
	/**
	 * This method finds the area of the segment its applied 
	 * on which is 0 for every segment
	 * @return
	 */
	public double area() {
		return 0;
	}

	@Override
	/**
	 * This method finds the perimeter of the segment its applied 
	 * on which is twice its length for every segment.
	 * @return
	 */
	public double perimeter() {
		return 2*_p1.distance(_p2);
	}

	@Override	
	/**
	 * This method gets a point it has to move every
	 * point by and moves each point of the 
	 * segment its applied on by that point.
	 * @param vec
	 */
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
	}

	@Override
	/**
	 * This method returns a new identical segment to the one
	 * the method was applied on.
	 * @return
	 */
	public GeoShapeable copy() {
		return new Segment2D(_p1, _p2);
	}

	@Override
	/**
	 * This method gets a point and a ratio and brings every point
	 * of the segment further or closer to the point given by the ratio given.
	 * @param center,ratio
	 */
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
	}

	@Override
	/**
	 * This method gets a point and an angle and rotates every point
	 * of the segment around the point given by the degrees of the 
	 * angle given.
	 * @param center,angleDegrees
	 */
	public void rotate(Point2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
	}

	@Override
	/**
	 * This method returns An array of points of the segment.
	 * @return
	 */
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] =new Point2D(getP1());
		ans[1] =new Point2D(getP2());		
		return ans;
	}
	
}