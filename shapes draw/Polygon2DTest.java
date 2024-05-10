package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Adam Sin-322453689, Tomer Shor-325511541
class Polygon2DTest {
	private Point2D _p1= new Point2D(70,0);
	private Point2D _p2= new Point2D(80,10);
	private Point2D _p3= new Point2D(70,10);
	private Point2D _p4= new Point2D(80,40);
	private Point2D _p5= new Point2D(50,20);
	private Point2D _p6= new Point2D(0,30);
	private Point2D _p7= new Point2D(30,10);
	private Point2D _p8= new Point2D(20,0);
	private Point2D[] _points= {_p1,_p2,_p3,_p4,_p5,_p6,_p7,_p8};
	private Polygon2D poly=new Polygon2D(_points);
	
	
	@Test
	/*
	 * checks if the boolean value returned of a point inside is true
	 * and a point outside is false
	 */
	void containsTest() {
		boolean out1= poly.contains(new Point2D(0,29));
		boolean in= poly.contains(new Point2D(45,21));
		assertEquals(out1,false);
		assertEquals(in,true);
	}
	
	@Test
	/*
	 * check if the area given equals the area of the polygon
	 */
	void areaTest() {
		double area=1400;
		double check=poly.area();
		assertEquals(check,area);
	}
	
	@Test
	/*
	 * check if the perimeter given equals the perimeter of the polygon
	 */
	void perimeterTest() {
		double perimeter= _p1.distance(_p2)+_p2.distance(_p3)+_p3.distance(_p4)+_p4.distance(_p5)+_p5.distance(_p6)+_p6.distance(_p7)+_p7.distance(_p8)+_p8.distance(_p1);
		assertEquals(poly.perimeter(),perimeter);
	}
	
	@Test
	/*
	 * checks if a moved polygon is identical to the polygon that 
	 * is supposed to be created after moving it by a vector(which is a point)
	 */
	void moveTest() {
		Point2D[] movedA= new Point2D[_points.length];
		for(int i=0;i<_points.length;i++) {
			movedA[i]= new Point2D(_points[i].x()+5,_points[i].y()+5);
		}
		Polygon2D moved=new Polygon2D(movedA);
		Point2D by=new Point2D(5,5);
		Polygon2D check=(Polygon2D)poly.copy();
		check.move(by);
		boolean same=true;
		for(int i=0;i<Math.max(moved.getPoints().length,check.getPoints().length);i++)
			if(!(moved.getPoint(i).equals(check.getPoint(i)))) {
				same=false;
				break;
			}
		assertEquals(same, true);
	}
	
	@Test
	/*
	 * checks if the new copy of the polygon is the same
	 * with the one it is a copy of.
	 */
	void copyTest() {
		Polygon2D copy=new Polygon2D(poly);
		boolean same=true;
		for(int i=0;i<Math.max(poly.getPoints().length,copy.getPoints().length);i++)
			if(!(poly.getPoint(i).equals(copy.getPoint(i)))) {
				same=false;
				break;
			}
			assertEquals(same, true);
	}
	
	@Test
	/*
	 *  checks if a scaled polygon is identical to the polygon that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point outside the polygon
	 */
	void scale_90OutTest() {
		Point2D[] scaledSA= new Point2D[_points.length];
		for(int i=0;i<_points.length;i++) {
			scaledSA[i]= _points[i];
			scaledSA[i].scale(new Point2D(0,0), 0.9);
		}
		Polygon2D scaledS=new Polygon2D(scaledSA);
		Polygon2D checkS=(Polygon2D)poly.copy();
		checkS.scale(new Point2D(0,0),0.9);
		boolean same=true;
		for(int i=0;i<Math.max(scaledS.getPoints().length,checkS.getPoints().length);i++)
			if(!(scaledS.getPoint(i).equals(checkS.getPoint(i)))) {
				same=false;
				break;
			}
		assertEquals(same, true);
	}
	@Test
	/*
	 *  checks if a scaled polygon is identical to the polygon that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point inside the polygon
	 */
	void scale_90InTest() {
		Point2D[] scaledSA= new Point2D[_points.length];
		for(int i=0;i<_points.length;i++) {
			scaledSA[i]= _points[i];
			scaledSA[i].scale(new Point2D(50,10), 0.9);
		}
		Polygon2D scaledS=new Polygon2D(scaledSA);
		Polygon2D checkS=(Polygon2D)poly.copy();
		checkS.scale(new Point2D(50,10),0.9);
		boolean same=true;
		for(int i=0;i<Math.max(scaledS.getPoints().length,checkS.getPoints().length);i++)
			if(!(scaledS.getPoint(i).equals(checkS.getPoint(i)))) {
				same=false;
				break;
			}
		assertEquals(same, true);
	}
	@Test
	/*
	 *  checks if a scaled polygon is identical to the polygon that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point outside the polygon
	 */
	void scale_110OutTest() {
		Point2D[] scaledSA= new Point2D[_points.length];
		for(int i=0;i<_points.length;i++) {
			scaledSA[i]= _points[i];
			scaledSA[i].scale(new Point2D(0,0), 1.1);
		}
		Polygon2D scaledS=new Polygon2D(scaledSA);
		Polygon2D checkS=(Polygon2D)poly.copy();
		checkS.scale(new Point2D(0,0),1.1);
		boolean same=true;
		for(int i=0;i<Math.max(scaledS.getPoints().length,checkS.getPoints().length);i++)
			if(!(scaledS.getPoint(i).equals(checkS.getPoint(i)))) {
				same=false;
				break;
			}
		assertEquals(same, true);
	}
	@Test
	/*
	 *  checks if a scaled polygon is identical to the polygon that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point inside the polygon
	 */
	void scale_110InTest() {
		Point2D[] scaledSA= new Point2D[_points.length];
		for(int i=0;i<_points.length;i++) {
			scaledSA[i]= _points[i];
			scaledSA[i].scale(new Point2D(50,10), 1.1);
		}
		Polygon2D scaledS=new Polygon2D(scaledSA);
		Polygon2D checkS=(Polygon2D)poly.copy();
		checkS.scale(new Point2D(50,10),1.1);
		boolean same=true;
		for(int i=0;i<Math.max(scaledS.getPoints().length,checkS.getPoints().length);i++)
			if(!(scaledS.getPoint(i).equals(checkS.getPoint(i)))) {
				same=false;
				break;
			}
		assertEquals(same, true);
	}
	
	@Test
	/*
	 * checks if a rotated polygon is identical to the polygon that 
	 *  is supposed to be created after rotating it with by 45 degrees
	 *  around another point inside the polygon.
	 */
	void rotateTest() {
		double angle=45/180.0*Math.PI;
		Point2D p1= new Point2D(50+Math.sqrt(500)*Math.cos(angle+Math.atan(-1/2.0)),10+Math.sqrt(500)*Math.sin(angle+Math.atan(-1/2.0)));
		Point2D p2= new Point2D(50+Math.sqrt(450),10+Math.sqrt(450));
		Point2D p3= new Point2D(50+Math.sqrt(200),10+Math.sqrt(200));
		Point2D p4= new Point2D(50+Math.sqrt(1800)*Math.cos(angle+Math.atan(1)),10+Math.sqrt(1800)*Math.sin(angle+Math.atan(1)));
		Point2D p5= new Point2D(50+(-5)*Math.sqrt(2),10+5*Math.sqrt(2));
		Point2D p6= new Point2D(50+Math.sqrt(2900)*Math.cos(Math.PI-Math.atan(2/5.0)+angle),10+Math.sqrt(2900)*Math.sin(Math.PI-Math.atan(2/5.0)+angle));
		Point2D p7= new Point2D(50+(-10)*Math.sqrt(2),10+(-10)*Math.sqrt(2));
		Point2D p8= new Point2D(50+Math.sqrt(1000)*Math.cos(angle+Math.atan(1/3.0)+Math.PI),10+Math.sqrt(1000)*Math.sin(angle+Math.atan(1/3.0)+Math.PI));
		Point2D[] points = {p1,p2,p3,p4,p5,p6,p7,p8};
		Polygon2D check=new Polygon2D(points);
		Polygon2D rotated= (Polygon2D) poly.copy();
		rotated.rotate(new Point2D(50,10), angle);
		boolean same=true;
		for(int i=0;i<Math.max(rotated.getPoints().length,check.getPoints().length);i++)
			if(!rotated.getPoint(i).close2equals(check.getPoint(i))) {
				same=false;
				break;
			}
		assertEquals(same, true);		
	}
	
	@Test
	/*
	 * checks if every point of the polygon is equal to every point
	 * it is supposed to get by the function. 
	 */
	void getPointsTest() {
		assertEquals(poly.getPoints(),_points);
	}

}
