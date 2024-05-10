package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Adam Sin-322453689, Tomer Shor-325511541
class Point2DTest {
	private Point2D _p1= new Point2D(70,0);
	private Point2D _p2= new Point2D(80,10);
	private Point2D _p3= new Point2D(80,40);
	private Point2D _p4= new Point2D(50,20);
	private Point2D _p5= new Point2D(0,30);
	private Point2D _p6= new Point2D(30,10);
	private Point2D _p7= new Point2D(20,0);
	private Point2D[] _points= {_p1,_p2,_p3,_p4,_p5,_p6,_p7};
	
	@Test
	/*
	 *  checks if a scaled point is identical to the point that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and another point
	 */
	void scale_90Test() {
		Point2D p1= new Point2D(68,1);
		Point2D p2= new Point2D(77,10);
		Point2D p3= new Point2D(77,37);
		Point2D p4= new Point2D(50,19);
		Point2D p5= new Point2D(5,28);
		Point2D p6= new Point2D(32,10);
		Point2D p7= new Point2D(23,1);
		Point2D[] checkS = {p1,p2,p3,p4,p5,p6,p7};
		Point2D[] scaledSA= new Point2D[_points.length];
		for(int i=0;i<_points.length;i++) {
			scaledSA[i]= new Point2D(_points[i]);
			scaledSA[i].scale(new Point2D(50,10), 0.9);
		}
		boolean same=true;
		for(int i=0;i<Math.max(scaledSA.length,checkS.length);i++)
			if(!(scaledSA[i].equals(checkS[i]))) {
				same=false;
				break;
			}
		assertEquals(same, true);
	}
	
	@Test
	/*
	 *  checks if a scaled point is identical to the point that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and another point
	 */
	void scale_110Test() {
		Point2D p1= new Point2D(72,-1);
		Point2D p2= new Point2D(83,10);
		Point2D p3= new Point2D(83,43);
		Point2D p4= new Point2D(50,21);
		Point2D p5= new Point2D(-5,32);
		Point2D p6= new Point2D(28,10);
		Point2D p7= new Point2D(17,-1);
		Point2D[] checkS = {p1,p2,p3,p4,p5,p6,p7};
		Point2D[] scaledSA= new Point2D[_points.length];
		for(int i=0;i<_points.length;i++) {
			scaledSA[i]= new Point2D(_points[i]);
			scaledSA[i].scale(new Point2D(50,10), 1.1);
		}
		boolean same=true;
		for(int i=0;i<Math.max(scaledSA.length,checkS.length);i++)
			if(!(scaledSA[i].equals(checkS[i]))) {
				same=false;
				break;
			}
		assertEquals(same, true);
	}
	
	@Test
	/*
	 * checks if a rotated point is identical to the point that 
	 *  is supposed to be created after rotating it with by 45 degrees
	 *  around another point
	 */
	void rotateTest() {
		double angle=45/180.0*Math.PI;
		Point2D center=new Point2D(50,10);
		
		Point2D p1= new Point2D(50+Math.sqrt(500)*Math.cos(angle+Math.atan(-1/2.0)),10+Math.sqrt(500)*Math.sin(angle+Math.atan(-1/2.0)));
		Point2D p2= new Point2D(50+Math.sqrt(450),10+Math.sqrt(450));
		Point2D p3= new Point2D(50+Math.sqrt(1800)*Math.cos(angle+Math.atan(1)),10+Math.sqrt(1800)*Math.sin(angle+Math.atan(1)));
		Point2D p4= new Point2D(50+(-5)*Math.sqrt(2),10+5*Math.sqrt(2));
		Point2D p5= new Point2D(50+Math.sqrt(2900)*Math.cos(Math.PI-Math.atan(2/5.0)+angle),10+Math.sqrt(2900)*Math.sin(Math.PI-Math.atan(2/5.0)+angle));
		Point2D p6= new Point2D(50+(-10)*Math.sqrt(2),10+(-10)*Math.sqrt(2));
		Point2D p7= new Point2D(50+Math.sqrt(1000)*Math.cos(angle+Math.atan(1/3.0)+Math.PI),10+Math.sqrt(1000)*Math.sin(angle+Math.atan(1/3.0)+Math.PI));
		Point2D[] check = {p1,p2,p3,p4,p5,p6,p7};
		
		Point2D[] _pointsRotated= {_p1,_p2,_p3,_p4,_p5,_p6,_p7};
		for(int i=0;i<_pointsRotated.length;i++)
			_pointsRotated[i].rotate(center, angle);
		
		boolean same=true;
		for(int i=0;i<Math.max(_pointsRotated.length,check.length);i++)
			if(!_pointsRotated[i].close2equals(check[i])) {
				same=false;
				break;
			}
		assertEquals(same, true);		
	}
}


