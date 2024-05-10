package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Adam Sin-322453689, Tomer Shor-325511541
class Segment2DTest {
	private Point2D _p1= new Point2D(10,10);
	private Point2D _p2= new Point2D(50,100);
	private Segment2D s=new Segment2D(_p1,_p2);
	private Segment2D sO=new Segment2D(_p2,_p1);

	@Test
	/*
	 * checks if the boolean value returned of a point on the segment is true
	 * and a point not is false
	 */
	void containsTest() {
		Segment2D sWithThird=new Segment2D(new Point2D(70,10),new Point2D(80,40));
		boolean isOn= sWithThird.contains(new Point2D(229/3.0,29));
		assertEquals(isOn, true);
		
		Point2D pIn=new Point2D(20,32.5);
		Point2D outR=new Point2D(80,167.5);
		Point2D pTL=new Point2D(19,32.5);
		Point2D pTR=new Point2D(21,32.5);
		Point2D pTU=new Point2D(20,33);
		Point2D pTD=new Point2D(20,32);
		assertEquals(s.contains(pIn), true);
		assertEquals(s.contains(pTL), false);
		assertEquals(s.contains(pTR), false);
		assertEquals(s.contains(pTU), false);
		assertEquals(s.contains(pTD), false);
		assertEquals(s.contains(outR), false);
		
		Point2D p1= new Point2D(10,10);
		Point2D p2= new Point2D(10,100);
		Point2D p3= new Point2D(10,50);
		Segment2D vertical=new Segment2D(p1,p2);
		assertEquals(vertical.contains(p3), true);
		
		p1= new Point2D(100,10);
		p2= new Point2D(10,10);
		p3= new Point2D(50,10);
		vertical=new Segment2D(p1,p2);
		assertEquals(vertical.contains(p3), true);
	}

	@Test
	/*
	 * check if the area given equals the area of the segment
	 */
	void areaTest() {
		double area1= s.area();
		double area2= sO.area();
		assertEquals(area1, 0);
		assertEquals(area2, 0);
	}
	
	@Test
	/*
	 * check if the perimeter given equals the perimeter of the segment
	 */
	void perimeterTest() {
		double area1= s.perimeter();
		double area2= sO.perimeter();
		assertEquals(area1, 2*Math.sqrt(9700));
		assertEquals(area2, 2*Math.sqrt(9700));
	}
	@Test
	/*
	 * checks if a moved segment is identical to the segment that 
	 * is supposed to be created after moving it by a vector(which is a point)
	 */
	void moveTest() {
		Point2D by=new Point2D(5,5);
		Segment2D check=new Segment2D(s);
		check.move(by);
		Segment2D moved=new Segment2D(_p1.add(by),_p2.add(by));
		boolean fpEqual= check.getP1().equals(moved.getP1());
		boolean spEqual= check.getP2().equals(moved.getP2());
		assertEquals(fpEqual && spEqual, true);
	}
	
	@Test
	/*
	 * checks if the new copy of the segment is the same
	 * with the one it is a copy of.
	 */
	void copyTest() {
		Segment2D copy=new Segment2D(s);
		boolean fpEqual= copy.getP1().equals(s.getP1());
		boolean spEqual= copy.getP2().equals(s.getP2());
		assertEquals(fpEqual && spEqual, true);
	}
	
	@Test
	/*
	 *  checks if a scaled segment is identical to the segment that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point not on the segment
	 */
	void scale_90OutTest() {
		Segment2D checkS=new Segment2D(s);
		checkS.scale(new Point2D(0,0),0.9);
		Segment2D scaledS=new Segment2D(new Point2D(9,9),new Point2D(45,90));
		boolean fpEqualS= checkS.getP1().equals(scaledS.getP1());
		boolean spEqualS= checkS.getP2().equals(scaledS.getP2());
		assertEquals(fpEqualS && spEqualS, true);
	}	
	@Test
	/*
	 *  checks if a scaled segment is identical to the segment that 
	 *  is supposed to be created after scaling it with 0.9 ratio
	 *  and a point on the segment
	 */
	void scale_90InTest() {	
		Segment2D checkSIn=new Segment2D(s);
		checkSIn.scale(new Point2D(30,55),0.9);
		Segment2D scaledSIn=new Segment2D(new Point2D(12,14.5),new Point2D(48,95.5));
		boolean fpEqualSIn= checkSIn.getP1().equals(scaledSIn.getP1());
		boolean spEqualSIn= checkSIn.getP2().equals(scaledSIn.getP2());
		assertEquals(fpEqualSIn && spEqualSIn, true);
	}
	@Test
	/*
	 *  checks if a scaled segment is identical to the segment that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point not on the segment
	 */
	void scale_110OutTest() {
		Segment2D checkB=new Segment2D(s);
		checkB.scale(new Point2D(0,0),1.1);
		Segment2D scaledB=new Segment2D(new Point2D(11,11),new Point2D(55,110));
		boolean fpEqualB= checkB.getP1().equals(scaledB.getP1());
		boolean spEqualB= checkB.getP2().equals(scaledB.getP2());
		assertEquals(fpEqualB && spEqualB, true);
	}
	@Test
	/*
	 *  checks if a scaled segment is identical to the segment that 
	 *  is supposed to be created after scaling it with 1.1 ratio
	 *  and a point on the segment
	 */
	void scale_110InTest() {
		Segment2D checkBIn=new Segment2D(s);
		checkBIn.scale(new Point2D(30,55),1.1);
		Segment2D scaledBIn=new Segment2D(new Point2D(8,5.5),new Point2D(52,104.5));
		boolean fpEqualBIn= checkBIn.getP1().equals(scaledBIn.getP1());
		boolean spEqualBIn= checkBIn.getP2().equals(scaledBIn.getP2());
		assertEquals(fpEqualBIn && spEqualBIn, true);
	}
	
	@Test
	void rotateTest() {
		/*
		 * checks if a rotated segment is identical to the segment that 
		 *  is supposed to be created after rotating it with by 23.96... degrees
		 *  around another point on the segment.
		 */
		double angleRad=90/180.0*Math.PI-Math.atan(45/20.0);
		Point2D p1= new Point2D(30,55-Math.sqrt(97)*5);
		Point2D p2= new Point2D(30,55+Math.sqrt(97)*5);
		Segment2D check=new Segment2D(p1,p2);
		Segment2D rotated=(Segment2D) s.copy();
		rotated.rotate(new Point2D(30,55), angleRad);
		boolean same=true;
		for(int i=0;i<2;i++)
			if(!rotated.getPoints()[i].close2equals(check.getPoints()[i]))
				same=false;
		assertEquals(same, true);
	}
	
	@Test
	/*
	 * checks if every point of the segment is equal to every point
	 * it is supposed to get by the function. 
	 */
	void getPointsTest() {
		Point2D[] check= s.getPoints();
		Point2D[] checkOp= sO.getPoints();
		assertEquals(check[0].equals(_p1) && check[1].equals(_p2), true);
		assertEquals(checkOp[0].equals(_p2) && checkOp[1].equals(_p1), true);
	}
}