package Exe.Ex4.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

//Adam Sin-322453689, Tomer Shor-325511541
class Ex4Test {
	private Point2D p1=new Point2D(10,10);
	private Point2D p2=new Point2D(100,100);
	private Point2D p3=new Point2D(100,50);
	private Point2D p4=new Point2D(130,130);
	private Point2D p5=new Point2D(110,20);
	private Point2D p6=new Point2D(60,30);
	private Point2D[] points= {p1,p2,p3,p4,p5,p6};
	private Polygon2D poly=new Polygon2D(points);
	private GUIShape polyG=new GUIShape(poly,true,Color.BLACK,1);
	private Triangle2D triangle=new Triangle2D(p1,p2,p3);
	private GUIShape triangleG=new GUIShape(triangle,true,Color.PINK,2);
	private Rect2D rect=new Rect2D(p1,p2);
	private GUIShape rectG=new GUIShape(rect,false,Color.BLUE,3);
	private Segment2D segment=new Segment2D(p1,p3);
	private GUIShape segmentG=new GUIShape(segment,true,Color.RED,4);
	private Circle2D circle=new Circle2D(p1,10);
	private GUIShape circleG=new GUIShape(circle,false,Color.BLACK,5);
	Ex4 test=Ex4.getInstance();
	
	@BeforeEach
	void shapesInCollection(){
		test.getShape_Collection().removeAll();
		test.getShape_Collection().add(polyG);
		test.getShape_Collection().add(triangleG);
		test.getShape_Collection().add(rectG);
		test.getShape_Collection().add(segmentG);
		test.getShape_Collection().add(circleG);
	}
	@Test
	/*
	 * checks if init does set the shape collections features
	 * to the ones given
	 */
	void initTest() {
		ShapeCollectionable shapes= new ShapeCollection();
		test.init(shapes);
		assertEquals(test.getShape_Collection().size(),0);
		shapes.add(polyG);
		test.init(shapes);
		assertEquals(test.getShape_Collection().get(0),polyG);
	}
	@Test
	/*
	 * checks if the shapes of the GUI shape collection were 
	 * copied to the new collection
	 */
	void getShape_CollectionTest() {
		ShapeCollectionable shapes= test.getShape_Collection();
		assertEquals(shapes.get(0),test.getShape_Collection().get(0));
		assertEquals(shapes.get(1),test.getShape_Collection().get(1));
		assertEquals(shapes.get(2),test.getShape_Collection().get(2));
		assertEquals(shapes.get(3),test.getShape_Collection().get(3));
		assertEquals(shapes.get(4),test.getShape_Collection().get(4));
		assertEquals(shapes.size(),5);
	}
	@Test
	void getInfoTest() {
		String str=polyG.toString()+"\n";
		str+=triangleG.toString()+"\n";
		str+=rectG.toString()+"\n";
		str+=segmentG.toString()+"\n";
		str+=circleG.toString()+"\n";
		assertEquals(test.getInfo(),str);
	}
}
