package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class Rect2D implements GeoShapeable {
	private Point2D _pR1;
	private Point2D _pR2;
	private Point2D _pR3;
	private Point2D _pR4;
	
	public Rect2D(Point2D p1, Point2D p2) {
		this._pR1 = new Point2D(p1);
		this._pR2 = new Point2D(p1.x(),p2.y());
		this._pR3 = new Point2D(p2);
		this._pR4 = new Point2D(p2.x(),p1.y());
	}
	public Rect2D(Rect2D r) {
		this._pR1 = new Point2D(r.getP1());
		this._pR2 = new Point2D(r.getP1().x(),r.getP3().y());
		this._pR3 = new Point2D(r.getP3());
		this._pR4 = new Point2D(r.getP3().x(),r.getP1().y());
	}
	public Point2D getP1() {return this._pR1;}
	public Point2D getP2() {
		this._pR2 = new Point2D(_pR1.x(),_pR3.y());
		return this._pR2;
	}
	public Point2D getP3() {return this._pR3;}
	public Point2D getP4() {
		this._pR4 = new Point2D(_pR3.x(),_pR1.y());
		return this._pR4;
	}
	 @Override
	public String toString() {return _pR1.toString()+", "+_pR2.toString()+", "+_pR3.toString()+", "+_pR4.toString();}
	 
	@Override	
	/**
	 * This method gets a point and returns "true" if the points is inside the
	 * polygon the method is applied on, and "false" if it isn't.
	 * By checking how many times the point moving to the right 
	 * touches the rectangle's sides. if 0 or 2 times, its outside. If once then
	 * the point is inside.
	 * @param ot
	 * @return
	 */
	public boolean contains(Point2D ot) {
		int count=0,i;
		if(ot.equals(_pR1) || ot.equals(_pR2) || ot.equals(_pR3) || ot.equals(_pR4))
			return true;
		for(i=0;i<3;i++) {
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
	 * This method finds the area of the rectangle its applied 
	 * on by computing 2 adjacent sides and multiplying them;
	 * (The distance between 2 following points)
	 * @return
	 */
	public double area() {
		double width =  _pR1.distance(_pR2);
		double height =  _pR1.distance(_pR4);
		return width*height;
	}

	@Override	
	/**
	 * This method finds the perimeter of the rectangle its applied 
	 * on by computing 2 adjacent sides,summing them, and multiplying by 2;
	 * (The distance between 2 following points)
	 * @return
	 */
	public double perimeter() {
		double width = _pR1.distance(_pR2);
		double height = _pR1.distance(_pR4);
		return 2*(width+height);
	}

	@Override	
	/**
	 * This method gets a point it has to move every
	 * point by and moves each point of the 
	 * rectangle its applied on by that point.
	 * @param vec
	 */
	public void move(Point2D vec) {
		_pR1.move(vec);
		_pR2.move(vec);
		_pR3.move(vec);
		_pR4.move(vec);
	}

	@Override
	/**
	 * This method returns a new identical rectangle to the one
	 * the method was applied on.
	 * @return
	 */
	public GeoShapeable copy() {
		return new Rect2D(_pR1, _pR3);
	}

	@Override
	/**
	 * This method gets a point and a ratio and brings every point
	 * of the rectangle further or closer to the point given by the ratio given.
	 * @param center,ratio
	 */
	public void scale(Point2D center, double ratio) {
		_pR1.scale(center, ratio);
		_pR2.scale(center, ratio);
		_pR3.scale(center, ratio);
		_pR4.scale(center, ratio);
	}
	
	@Override
	/**
	 * This method gets a point and an angle and rotates every point
	 * around the point given.
	 * @param center,angleDegrees
	 */
	public void rotate(Point2D center, double angleDegrees) {
		//////////add your code below ///////////
		_pR1.rotate(center,angleDegrees);
		_pR2.rotate(center,angleDegrees);
		_pR3.rotate(center,angleDegrees);
		_pR4.rotate(center,angleDegrees);
		//////////////////////////////////////////
	}

	@Override
	/**
	 * This method returns An array of points of the rectangle.
	 * @return
	 */
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[4];
		ans[0] =new Point2D(this._pR1);
		ans[1] =new Point2D(this._pR2);
		ans[2] =new Point2D(this._pR3);		
		ans[3] =new Point2D(this._pR4);
		return ans;
	}

}