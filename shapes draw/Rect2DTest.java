package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Adam Sin-322453689, Tomer Shor-325511541
class Rect2DTest {
	private Point2D _p1= new Point2D(10,10);
	private Point2D _p2= new Point2D(100,100);
	private Rect2D r=new Rect2D(_p1,_p2);
	private Rect2D rS=new Rect2D(_p2,_p1);

	@Test
	/*
	 * checks if the boolean value returned of a point inside is true
	 * and a point outside is false
	 */
	void containsTest() {
		Point2D pIn=new Point2D(50,50);
		Point2D pTL=new Point2D(9,11);
		Point2D pTR=new Point2D(101,11);
		Point2D pTU=new Point2D(11,101);
		Point2D pTD=new Point2D(11,9);
		boolean isIn1= r.contains(pIn);
		boolean isIn2= r.contains(pTL);
		boolean isIn3= r.contains(pTR);
		boolean isIn4= r.contains(pTU);
		boolean isIn5= r.contains(pTD);
		assertEquals(isIn1, true);
		assertEquals(isIn2, false);
		assertEquals(isIn3, false);
		assertEquals(isIn4, false);
		assertEquals(isIn5, false);
	}

	@Test
	/*
	 * check if the area given equals the area of the rectangle
	 */
	void areaTest() {
		double area1= r.area();
		double area2= rS.area();
		assertEquals(area1, 8100);
		assertEquals(area2, 8100);
	}

	@Test
	/*
	 * check if the perimeter given equals the perimeter of the rectangle
	 */
	void perimeterTest() {
		double area1= r.perimeter();
		double area2= rS.perimeter();
		assertEquals(area1, 360);
		assertEquals(area2, 360);
	}

	@Test
	/*
	 * checks if a moved rectangle is identical to the rectangle that 
	 * is supposed to be created after moving it by a vector(which is a point)
	 */
	void moveTest() {
		Point2D by=new Point2D(5,5);
		Rect2D check=new Rect2D(r);
		check.move(by);
		Rect2D moved=new Rect2D(_p1.add(by),_p2.add(by));
		boolean fpEqual= check.getP1().equals(moved.getP1());
		boolean spEqual= check.getP3().equals(moved.getP3());
		assertEquals(fpEqual && spEqual, true);
	}
	
	@Test
	/*
	 * checks if the new copy of the rectangle is the same
	 * with the one it is a copy of.
	 */
	void copyTest() {
		Rect2D copy=(Rect2D) r.copy();
		boolean pEqual1= copy.getP1().equals(r.getP1());
		boolean pEqual2= copy.getP2().equals(r.getP2());
		boolean pEqual3= copy.getP3().equals(r.getP3());
		boolean pEqual4= copy.getP4().equals(r.getP4());
		assertEquals(pEqual1 && pEqual2 && pEqual3 && pEqual4, true);
	}
	
	@Test
	/*
	 *  checks if a scaled rectangle is identical to the rectangle that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point outside the rectangle
	 */
	void scale_90OutTest() {
		Rect2D checkS=new Rect2D(r);
		checkS.scale(new Point2D(0,0),0.9);
		Rect2D scaledS=new Rect2D(new Point2D(9,9),new Point2D(90,90));
		boolean pEqualS1= checkS.getP1().equals(scaledS.getP1());
		boolean pEqualS2= checkS.getP2().equals(scaledS.getP2());
		boolean pEqualS3= checkS.getP3().equals(scaledS.getP3());
		boolean pEqualS4= checkS.getP4().equals(scaledS.getP4());
		assertEquals(pEqualS1 && pEqualS2 && pEqualS3 && pEqualS4, true);
	}
	@Test
	/*
	 *  checks if a scaled rectangle is identical to the rectangle that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point inside the rectangle
	 */
	void scale_90InTest() {
		Rect2D checkSIn=new Rect2D(r);
		checkSIn.scale(new Point2D(55,55),0.9);
		Rect2D scaledSIn=new Rect2D(new Point2D(14.5,14.5),new Point2D(95.5,95.5));
		boolean pEqualSIn1= checkSIn.getP1().equals(scaledSIn.getP1());
		boolean pEqualSIn2= checkSIn.getP2().equals(scaledSIn.getP2());
		boolean pEqualSIn3= checkSIn.getP3().equals(scaledSIn.getP3());
		boolean pEqualSIn4= checkSIn.getP4().equals(scaledSIn.getP4());
		assertEquals(pEqualSIn1 && pEqualSIn2 && pEqualSIn3 && pEqualSIn4, true);
	}
	@Test
	/*
	 *  checks if a scaled rectangle is identical to the rectangle that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point outside the rectangle
	 */
	void scale_110OutTest() {
		Rect2D checkB=new Rect2D(r);
		checkB.scale(new Point2D(0,0),1.1);
		Rect2D scaledB=new Rect2D(new Point2D(11,11),new Point2D(110,110));
		boolean pEqualB1= checkB.getP1().equals(scaledB.getP1());
		boolean pEqualB2= checkB.getP2().equals(scaledB.getP2());
		boolean pEqualB3= checkB.getP3().equals(scaledB.getP3());
		boolean pEqualB4= checkB.getP4().equals(scaledB.getP4());
		assertEquals(pEqualB1 && pEqualB2 && pEqualB3 && pEqualB4, true);
	}
	@Test
	/*
	 *  checks if a scaled rectangle is identical to the rectangle that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point inside the rectangle
	 */
	void scale_110InTest() {
		Rect2D checkBIn=new Rect2D(r);
		checkBIn.scale(new Point2D(55,55),1.1);
		Rect2D scaledBIn=new Rect2D(new Point2D(5.5,5.5),new Point2D(104.5,104.5));
		boolean pEqualBIn1= checkBIn.getP1().equals(scaledBIn.getP1());
		boolean pEqualBIn2= checkBIn.getP2().equals(scaledBIn.getP2());
		boolean pEqualBIn3= checkBIn.getP3().equals(scaledBIn.getP3());
		boolean pEqualBIn4= checkBIn.getP4().equals(scaledBIn.getP4());
		assertEquals(pEqualBIn1 && pEqualBIn2 && pEqualBIn3 && pEqualBIn4, true);
	}
	
	@Test
	/*
	 * checks if a rotated rectangle is identical to the rectangle that 
	 *  is supposed to be created after rotating it with by 45 degrees
	 *  around another point inside the rectangle.
	 */
	void rotateTest() {
		double angle=45/180.0*Math.PI;
		Point2D p1= new Point2D(55,55-Math.sqrt(2)*45);
		Point2D p2= new Point2D(55-Math.sqrt(2)*45,55);
		Point2D p3= new Point2D(55,55+Math.sqrt(2)*45);
		Point2D p4= new Point2D(Math.sqrt(2)*45+55,55);
		Point2D[] points = {p1,p2,p3,p4};
		Polygon2D check=new Polygon2D(points);
		Rect2D copy=(Rect2D) r.copy();
		copy.rotate(new Point2D(55,55), angle);
		Polygon2D rotated=new Polygon2D(copy.getPoints());
		boolean same=true;
		for(int i=0;i<4;i++)
			if(!rotated.getPoint(i).close2equals(check.getPoint(i)))
				same=false;
		assertEquals(same, true);		
	}
	
	@Test
	/*
	 * checks if every point of the rectangle is equal to every point
	 * it is supposed to get by the function. 
	 */
	void getPointsTest() {
		Point2D p3=new Point2D(_p1.x(),_p2.y());
		Point2D p4=new Point2D(_p2.x(),_p1.y());
		Point2D[] rect={_p1,p3,_p2,p4};
		Point2D[] check= r.getPoints();
		boolean same=true;
		for(int i=0;i<4;i++)
			if(!check[i].equals(rect[i]))
				same=false;
		assertEquals(same, true);
		
		Point2D[] rectOp={_p2,p4,_p1,p3};
		Point2D[] checkOp= rS.getPoints();
		same=true;
		for(int i=0;i<4;i++)
			if(!checkOp[i].equals(rectOp[i]))
				same=false;
		assertEquals(same, true);
	}
}
