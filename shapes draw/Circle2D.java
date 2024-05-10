package Exe.Ex4.geo;

/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class Circle2D implements GeoShapeable{
	private Point2D _center;
	private double _radius;
	
	public Circle2D(Point2D cen, double rad) {
		this._center = new Point2D(cen);
		this._radius = rad;
	}
	public double getRadius() {return this._radius;}
	public Point2D getCenter() {return this._center;}
	 @Override
	public String toString() {return _center.toString()+", "+_radius;}
	@Override
	public boolean contains(Point2D ot) {
		double dist = ot.distance(this._center);
		return dist<=this._radius;
	}
	
	@Override
	public double area() {
		double ans = Math.PI * Math.pow(this._radius, 2);
		return ans;
	}
	@Override
	public double perimeter() {
		double ans = Math.PI * 2 * this._radius;
		return ans;
	}
	@Override
	public void move(Point2D vec) {
		_center.move(vec);
	}
	@Override
	public GeoShapeable copy() {
		return new Circle2D(_center, _radius);
	}
	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] =new Point2D(this._center);
		ans[1] = new Point2D(ans[0].x(), ans[0].y()+this._radius);
		return ans;
	}
	@Override
	/**
	 * This method gets a point and a ratio and brings the center
	 * of the circle further or closer to the point given by the ratio given.
	 * @param center ratio
	 */
	public void scale(Point2D center, double ratio) {
		if (ratio==0.9)
			_radius=_radius*0.9;
		else 
			_radius=_radius*1.1;
		_center.scale(center, ratio);
	}
	@Override
	/**
	 * This method gets a point and an angle and rotates the center
	 * of the circle around the point given by the degrees of the 
	 * angle given.
	 * @param center, angleDegrees
	 */
	public void rotate(Point2D center, double angleDegrees) {
			_center.rotate(center, angleDegrees);
	}
}