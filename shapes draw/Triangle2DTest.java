package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Adam Sin-322453689, Tomer Shor-325511541
class Triangle2DTest {

	private Point2D _p1= new Point2D(10,30);
	private Point2D _p2= new Point2D(30,10);
	private Point2D _p3= new Point2D(70,100);
	private Triangle2D t=new Triangle2D(_p1,_p2,_p3);

	@Test
	/*
	 * checks if the boolean value returned of a point inside is true
	 * and a point outside is false
	 */
	void containsTest() {
		boolean in1= t.contains(new Point2D(30,40));   // inside the triangle
		boolean in2= t.contains(new Point2D(50,55));   // on the p2-->p3 side of the triangle
		boolean in3= t.contains(new Point2D(190/7.0,50));// on the p1-->p3 side of the triangle
		boolean in4= t.contains(new Point2D(20,20));   // on the p1-->p2 side of the triangle
		boolean out1= t.contains(new Point2D(50.00001,55)); 
		boolean out2= t.contains(new Point2D(190/7.0,50.00001)); 
		boolean out3= t.contains(new Point2D(19.9999,20)); 
		assertEquals(in1, true);
		assertEquals(in2, true);
		assertEquals(in3, true);
		assertEquals(in4, true);
		assertEquals(out1, false);
		assertEquals(out2, false);
		assertEquals(out3, false);
	}

	@Test
	/*
	 * check if the area given equals the area of the triangle
	 */
	void areaTest() {
		double area1= t.area();
		assertEquals(area1, 1300);
	}
	
	@Test
	/*
	 * check if the perimeter given equals the area of the triangle
	 */
	void perimeterTest() {
		double area1= t.perimeter();
		assertEquals(area1, Math.sqrt(8500)+Math.sqrt(9700)+Math.sqrt(800));
	}
	
	@Test
	/*
	 * checks if a moved triangle is identical to the triangle that 
	 * is supposed to be created after moving it by a vector(which is a point)
	 */
	void moveTest() {
		Point2D by=new Point2D(5,5);
		Triangle2D check=new Triangle2D(t);
		check.move(by);
		Triangle2D moved=new Triangle2D(_p1.add(by),_p2.add(by),_p3.add(by));
		boolean fpEqual= check.getP1().equals(moved.getP1());
		boolean spEqual= check.getP2().equals(moved.getP2());
		boolean tpEqual= check.getP3().equals(moved.getP3());
		assertEquals(fpEqual && spEqual && tpEqual, true);
	}
	
	@Test
	/*
	 * checks if the new copy of the triangle is the same
	 * with the one it is a copy of.
	 */
	void copyTest() {
		Triangle2D copy=new Triangle2D(t);
		boolean fpEqual= copy.getP1().equals(t.getP1());
		boolean spEqual= copy.getP2().equals(t.getP2());
		boolean tpEqual= copy.getP3().equals(t.getP3());
		assertEquals(fpEqual && spEqual && tpEqual, true);
	}
	
	@Test
	/*
	 *  checks if a scaled triangle is identical to the triangle that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point outside the triangle
	 */
	void scale_90OutTest() {
		Triangle2D checkS=new Triangle2D(t);
		checkS.scale(new Point2D(0,0),0.9);
		Triangle2D scaledS=new Triangle2D(new Point2D(9,27),new Point2D(27,9),new Point2D(63,90));
		boolean fpEqualS= checkS.getP1().equals(scaledS.getP1());
		boolean spEqualS= checkS.getP2().equals(scaledS.getP2());
		boolean tpEqualS= checkS.getP3().equals(scaledS.getP3());
		assertEquals(fpEqualS && spEqualS && tpEqualS, true);
	}
	@Test
	/*
	 *  checks if a scaled triangle is identical to the triangle that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point inside the triangle
	 */
	void scale_90InTest() {
		Triangle2D checkS=new Triangle2D(t);
		checkS.scale(new Point2D(30,40),0.9);
		Triangle2D scaledS=new Triangle2D(new Point2D(12,31),new Point2D(30,13),new Point2D(66,94));
		boolean fpEqualS= checkS.getP1().equals(scaledS.getP1());
		boolean spEqualS= checkS.getP2().equals(scaledS.getP2());
		boolean tpEqualS= checkS.getP3().equals(scaledS.getP3());
		assertEquals(fpEqualS && spEqualS && tpEqualS, true);
	}
	@Test
	/*
	 *  checks if a scaled triangle is identical to the triangle that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point outside the triangle
	 */
	void scale_110OutTest() {
		Triangle2D checkB=new Triangle2D(t);
		checkB.scale(new Point2D(0,0),1.1);
		Triangle2D scaledB=new Triangle2D(new Point2D(11,33),new Point2D(33,11),new Point2D(77,110));
		boolean fpEqualB= checkB.getP1().equals(scaledB.getP1());
		boolean spEqualB= checkB.getP2().equals(scaledB.getP2());
		boolean tpEqualB= checkB.getP3().equals(scaledB.getP3());
		assertEquals(fpEqualB && spEqualB && tpEqualB, true);
	}
	@Test
	/*
	 *  checks if a scaled triangle is identical to the triangle that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point inside the triangle
	 */
	void scale_110InTest() {	
		Triangle2D checkB=new Triangle2D(t);
		checkB.scale(new Point2D(30,40),1.1);
		Triangle2D scaledB=new Triangle2D(new Point2D(8,29),new Point2D(30,7),new Point2D(74,106));
		boolean fpEqualB= checkB.getP1().equals(scaledB.getP1());
		boolean spEqualB= checkB.getP2().equals(scaledB.getP2());
		boolean tpEqualB= checkB.getP3().equals(scaledB.getP3());
		assertEquals(fpEqualB && spEqualB && tpEqualB, true);
	}
		
	@Test
	/*
	 * checks if a rotated triangle is identical to the triangle that 
	 *  is supposed to be created after rotating it with by 45 degrees
	 *  around another point inside the triangle.
	 */
	void rotateTest() {
		double angleRad=(45/180.0)*Math.PI;
		Point2D p1= new Point2D(Math.sqrt(1000)*Math.cos(angleRad+Math.atan(3)),Math.sqrt(1000)*Math.sin(angleRad+Math.atan(3)));
		Point2D p2= new Point2D(Math.sqrt(1000)*Math.cos(angleRad+Math.atan(1/3.0)),Math.sqrt(1000)*Math.sin(angleRad+Math.atan(1/3.0)));
		Point2D p3= new Point2D(Math.sqrt(14900)*Math.cos(angleRad+Math.atan(10/7.0)),Math.sqrt(14900)*Math.sin(angleRad+Math.atan(10/7.0)));
		Triangle2D check=new Triangle2D(p1,p2,p3);
		Triangle2D rotated=(Triangle2D) t.copy();
		rotated.rotate(new Point2D(0,0), angleRad);
		boolean same=true;
		for(int i=0;i<3;i++)
			if(!rotated.getPoints()[i].equals(check.getPoints()[i]))
				same=false;
		assertEquals(same, true);
	}
		
	@Test
	/*
	 * checks if every point of the triangle is equal to every point
	 * it is supposed to get by the function. 
	 */
	void getPointsTest() {
		Point2D[] check= t.getPoints();
		assertEquals(check[0].equals(_p1) && check[1].equals(_p2) && check[2].equals(_p3), true);
	}
	
}

