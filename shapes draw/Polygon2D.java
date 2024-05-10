package Exe.Ex4.geo;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class Polygon2D implements GeoShapeable{
	private Point2D[] _p;
	
	public Polygon2D(Point2D[] p) {
		_p=p;
	}
	public Polygon2D(Polygon2D poly) {
		_p=poly.getPoints();
	}
	public Point2D getPoint(int i) {return this._p[i];}
	@Override
	public String toString() {
		String str="";
		int i;
		for(i=0;i<_p.length-1;i++)
			str+=getPoint(i)+",";
		str+=getPoint(i);
		return str;
	}
	 
	@Override
	/**
	 * This method gets a point and returns "true" if the points is inside the
	 * polygon the method is applied on, and "false" if it isn't.
	 * @param ot
	 * @return
	 */
	public boolean contains(Point2D ot) {
		int count=0,i;
		for(i=0;i<this.getPoints().length-1;i++) {
			Segment2D side=new Segment2D(this.getPoints()[i],this.getPoints()[i+1]);
			double m=(side.getP1().y()-side.getP2().y())/(side.getP1().x()-side.getP2().x());
			if(m!=0) {
				double x= (ot.y()-side.getP1().y())/m+side.getP1().x();
				if(side.contains(new Point2D(x,ot.y())) && ot.x()<=x)
					count++;
			}
			else
				if(side.getP2().y()==ot.y() && side.getP2().x()>ot.x())
					count++;
		}
		Segment2D side=new Segment2D(this.getPoints()[i],this.getPoints()[0]);
		double m=(side.getP1().y()-side.getP2().y())/(side.getP1().x()-side.getP2().x());
		if(m!=0) {
			double x= (ot.y()-side.getP1().y())/m+side.getP1().x();
			if(side.contains(new Point2D(x,ot.y())) && ot.x()<=x)
				count++;
		}
		else
			if(side.getP2().y()==ot.y() && side.getP2().x()>ot.x())
				count++;
		return count%2==1;
	}
	
	@Override
	/**
	 * This method finds the area of the polygon its applied 
	 * on using the Gauss Formula.
	 * @return
	 */
	public double area() {
		double ans = 0 ;
		int i;
		for(i =0; i<_p.length-1;i++){
			ans+=_p[i].x()*_p[i+1].y();
			ans-=_p[i+1].x()*_p[i].y();
		}
		ans+=_p[i].x()*_p[0].y();
		ans-=_p[0].x()*_p[i].y();
		ans=Math.abs(ans)/2.0;
		return ans;
	}
	@Override
	/**
	 * This method finds the perimeter of the polygon its applied 
	 * on by summing every side of the polygon
	 * (The distance between 2 following points)
	 * @return
	 */
	public double perimeter() {
		double perimeter=0;
		int i;
		for(i=0;i<this.getPoints().length-1;i++)
				perimeter+=this.getPoints()[i].distance(this.getPoints()[i+1]);
		perimeter+=this.getPoints()[i].distance(this.getPoints()[0]);
		return perimeter;
	}

	@Override
	/**
	 * This method gets a point it has to move every
	 * point by and moves each point of the 
	 * polygon its applied on by that point.
	 * @param vec
	 */
	public void move(Point2D vec) {
		for(int i=0;i<this.getPoints().length;i++)
			this.getPoint(i).move(vec);
	}

	@Override
	/**
	 * This method returns a new identical polygon to the one
	 * the method was applied on.
	 * @return 
	 */
	public GeoShapeable copy() {
		return new Polygon2D(_p);
	}

	@Override
	/**
	 * This method gets a point and a ratio and brings every point
	 * of the polygon further or closer to the point given by the ratio given.
	 * @param center,ratio
	 */
	public void scale(Point2D center, double ratio) {
		for(int i=0;i<this.getPoints().length;i++) {
			_p[i].scale(center,ratio);
		}
	}

	@Override
	/**
	 * This method gets a point and an angle and rotates every point
	 * of the circle around the point given by the degrees of the 
	 * angle given.
	 * @param center, angleDegrees
	 */
	public void rotate(Point2D center, double angleDegrees) {
		for(int i=0;i<this.getPoints().length;i++) {
			_p[i].rotate(center,angleDegrees);
		}
	}

	@Override
	/**
	 * This method returns the array of points of the polygon.
	 * @return
	 */
	public Point2D[] getPoints() {
		return _p;
	}
	
}