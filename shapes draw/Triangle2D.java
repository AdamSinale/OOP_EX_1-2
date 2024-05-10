package Exe.Ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class Triangle2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
		this._p3 = new Point2D(p3);
	}
	public Triangle2D(Triangle2D t) {
		this._p1 = new Point2D(t.getP1());
		this._p2 = new Point2D(t.getP2());
		this._p3 = new Point2D(t.getP3());
	}
	public Point2D getP1() {return this._p1;}
	public Point2D getP2() {return this._p2;}
	public Point2D getP3() {return this._p3;}
	 @Override
	public String toString() {return _p1.toString()+", "+_p2.toString()+", "+_p3.toString();}
	 
	@Override
	/**
	 * This method gets a point and returns "true" if the points is inside the
	 * polygon the method is applied on, and "false" if it isn't.
	 * By checking if the sum of the area of the triangles from every 2 points 
	 * of the triangle to the given point is equal to the triangles area or not.
	 * If it is, then true, else, false.
	 * @param ot
	 * @return
	 */
	public boolean contains(Point2D ot) {
		Triangle2D part1= new Triangle2D(_p1,_p2, ot);
		Triangle2D part2= new Triangle2D(_p1,_p3, ot);
		Triangle2D part3= new Triangle2D(_p2,_p3, ot);
		return part1.area()+part2.area()+part3.area()==this.area();
	}

	@Override
	/**
	 * This method finds the area of the polygon its applied 
	 * on using a formula to compute a triangle's area.
	 * @return
	 */
	public double area() {
		return 0.5*Math.abs(_p1.x()*(_p3.y()-_p2.y())+_p2.x()*(_p1.y()-_p3.y())+_p3.x()*(_p2.y()-_p1.y()));
	}

	@Override
	/**
	 * This method finds the perimeter of the triangle its applied 
	 * on by summing every side of the triangle
	 * (The distance between 2 following points)
	 * @return
	 */
	public double perimeter() {
		return _p1.distance(_p2)+_p2.distance(_p3)+_p3.distance(_p1);
	}

	@Override
	/**
	 * This method gets a point it has to move every
	 * point by and moves each point of the 
	 * triangle its applied on by that point.
	 * @param vec
	 */
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	/**
	 * This method returns a new identical triangle to the one
	 * the method was applied on.
	 * @return
	 */
	public GeoShapeable copy() {
		return new Triangle2D(_p1,_p2,_p3);
	}

	@Override
	/**
	 * This method gets a point and a ratio and brings every point
	 * of the triangle further or closer to the point given by the ratio given.
	 * @param center,ratio
	 */
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	@Override
	/**
	 * This method gets a point and an angle and rotates every point
	 * of the triangle around the point given by the degrees of the 
	 * angle given.
	 * @param center, angleDegrees
	 */
	public void rotate(Point2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
	}

	@Override
	/**
	 * This method returns An array of points of the triangle.
	 * @return
	 */
	public Point2D[] getPoints() {
		Point2D[] ans= new Point2D[3];
		ans[0]= new Point2D(getP1());
		ans[1]= new Point2D(getP2());
		ans[2]= new Point2D(getP3());
		return ans;
	}
	
}