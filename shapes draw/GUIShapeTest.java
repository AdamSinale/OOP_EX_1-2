package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

//Adam Sin-322453689, Tomer Shor-325511541
class GUIShapeTest {
	Point2D p1=new Point2D(10,10);
	Point2D p2=new Point2D(100,100);
	Point2D p3=new Point2D(100,50);
	Circle2D c= new Circle2D(p2,10);
	Segment2D s= new Segment2D(p1,p3);
	Rect2D r= new Rect2D(p1,p2);
	Triangle2D t= new Triangle2D(p1,p2,p3);
	GUIShape gui= new GUIShape(null,false,Color.pink,1);
	
	@Test
	/*
	 * sets a shape for every GUI shape and checks
	 * if the shape set is the shape intended
	 */
	void setShapeTest() {
		assertEquals(gui.getShape(),null);
		gui.setShape(c);
		assertEquals(gui.getShape(),c);
		gui.setShape(s);
		assertEquals(gui.getShape(),s);
		gui.setShape(r);
		assertEquals(gui.getShape(),r);
		gui.setShape(t);
		assertEquals(gui.getShape(),t);
	}

}
