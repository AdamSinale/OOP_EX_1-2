package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

//Adam Sin-322453689, Tomer Shor-325511541
class ShapeCollectionTest {
	private ShapeCollection _shapesCol= new ShapeCollection();
	private Point2D p1 = new Point2D(10,10);
	private Point2D p2 = new Point2D(100,100);
	private Point2D p3 = new Point2D(100,10);
	private Triangle2D t = new Triangle2D(p1,p2,p3);
	private Rect2D r = new Rect2D(p1,p2);
	private Circle2D c1 = new Circle2D(p1,p1.distance(p2));
	private Circle2D c2 = new Circle2D(p2,p2.distance(p3));
	private GUIShape c1G=new GUIShape(c1,true, Color.PINK, 3);
	private GUIShape tG=new GUIShape(t,true, Color.PINK, 4);
	private GUIShape rG=new GUIShape(r,false, Color.pink, 3);
	private GUIShape c2G=new GUIShape(c2,true, Color.GREEN, 3);
	
	@BeforeEach
	/*
	 * Before every test, adds the 4 GUI shapes to the collection
	 */
	void addToCol() {
		_shapesCol.removeAll();
		_shapesCol.add(c1G);
		_shapesCol.add(tG);
		_shapesCol.add(rG);
		_shapesCol.add(c2G);
		
	}
	
	@Test
	/*
	 * Checks where the shape removed is the shape intended,
	 * and checks if the new collection is the one intended.
	 */
	void removeElementAtTest() {
		GUI_Shapeable r=_shapesCol.removeElementAt(2);
		GUI_Shapeable checkRemoved= rG;
		assertEquals(checkRemoved,r);

		ShapeCollection checkCol= new ShapeCollection();
		checkCol.add(c1G);
		checkCol.add(tG);
		checkCol.add(c2G);
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), checkCol.size());i++)
			if(!_shapesCol.get(i).equals(checkCol.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}

	@Test
	/*
	 * checks if the new collection is the one before with 
	 * the new GUI shape at the place given.
	 */
	void addAtTest() {
		_shapesCol.addAt(rG,1);
		ShapeCollection checkCol= new ShapeCollection();
		checkCol.add(c1G);
		checkCol.add(rG);
		checkCol.add(tG);
		checkCol.add(rG);
		checkCol.add(c2G);
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), checkCol.size());i++)
			if(!_shapesCol.get(i).equals(checkCol.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	
	@Test
	/*
	 * checks if the new copy of the collection is the same
	 * with the one it is a copy of.
	 */
	void copyTest() {
		ShapeCollectionable checkCol= _shapesCol.copy();
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), checkCol.size());i++)
			if(!_shapesCol.get(i).equals(checkCol.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	
	@Test
	/*
	 * checks if the sorted collection is sorted from the smallest
	 * area GUI shape to the biggest
	 */
	void sortByAreaTest() {
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Area);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(tG);  // 4050
		check.add(rG);  // 8100
		check.add(c2G); // 25446.900494077323
		check.add(c1G); // 50893.80098815465
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if in a collection with equal areas(sorted) the collection
	 * stays the same
	 */
	void sortByAreaEqualsTest() {
		Triangle2D t2 = new Triangle2D(p1,new Point2D(100,190),p3);
		GUIShape t2G=new GUIShape(t2,false, Color.pink, 0);
		Rect2D r2 = new Rect2D(p1,new Point2D(110,91));
		GUIShape r2G=new GUIShape(r2,false, Color.pink, 0);
		Triangle2D t3 = new Triangle2D(p1,new Point2D(-80,100),p2);
		GUIShape t3G=new GUIShape(t3,false, Color.pink, 0);
		_shapesCol.removeAll();
		_shapesCol.add(t2G); // 8100
		_shapesCol.add(rG);  // 8100
		_shapesCol.add(r2G); // 8100
		_shapesCol.add(t3G); // 8100
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Area);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(t2G); // 8100
		check.add(rG);  // 8100
		check.add(r2G); // 8100
		check.add(t3G);  // 8100
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if the sorted collection is sorted from the biggest
	 * area GUI shape to the smallest
	 */
	void sortByAntiAreaTest() {
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(c1G); // 50893.80098815465
		check.add(c2G); // 25446.900494077323
		check.add(rG);  // 8100
		check.add(tG);  // 4050
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}

	@Test
	/*
	 * checks if the sorted collection is sorted from the smallest
	 * perimeter GUI shape to the biggest
	 */
	void sortByPerimeterTest() {
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Perimeter);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(tG);  // 307.27922061357856
		check.add(rG);  // 360
		check.add(c2G); // 565.4866776461628
		check.add(c1G); // 799.7189288685059
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if in a collection with equal perimeters(sorted) the collection
	 * stays the same
	 */
	void sortByPerimeterEqualsTest() {
		Triangle2D t2 = new Triangle2D(p1,new Point2D(16,18),new Point2D(16,10));
		GUIShape t2G=new GUIShape(t2,false, Color.pink, 0);
		Rect2D r2 = new Rect2D(p2,new Point2D(95,93));
		GUIShape r2G=new GUIShape(r2,false, Color.pink, 0);
		Rect2D r3 = new Rect2D(p1,new Point2D(4,4));
		GUIShape r3G=new GUIShape(r3,false, Color.pink, 0);
		Segment2D s2 = new Segment2D(p2,new Point2D(112,100));
		GUIShape s2G=new GUIShape(s2,false, Color.pink, 0);
		_shapesCol.removeAll();
		_shapesCol.add(t2G); // 18
		_shapesCol.add(r2G); // 18
		_shapesCol.add(r3G); // 18
		_shapesCol.add(s2G); // 18
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Perimeter);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(t2G); // 18
		check.add(r2G); // 18
		check.add(r3G); // 18
		check.add(s2G); // 18
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if the sorted collection is sorted from the biggest
	 * perimeter GUI shape to the smallest
	 */
	void sortByAntiPerimeterTest() {
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(c1G); // 799.7189288685059
		check.add(c2G); // 565.4866776461628
		check.add(rG);  // 360
		check.add(tG);  // 307.27922061357856
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}

	@Test
	/*
	 * checks if the sorted collection is sorted from the smallest
	 * tag GUI shape to the biggest
	 */
	void sortByTagTest() {
		c2G.setTag(1);
		c1G.setTag(2);
		rG.setTag(3);
		tG.setTag(4);
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Tag);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(c2G); // 1
		check.add(c1G); // 2
		check.add(rG);  // 3
		check.add(tG);  // 4
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if in a collection with the same tags(sorted) the collection
	 * stays the same
	 */
	void sortByTagEqualsTest() {
		Triangle2D t2 = new Triangle2D(p1,new Point2D(16,18),new Point2D(16,10));
		GUIShape t2G=new GUIShape(t2,false, Color.pink, 0);
		Rect2D r2 = new Rect2D(p2,new Point2D(95,93));
		GUIShape r2G=new GUIShape(r2,false, Color.pink, 0);
		Rect2D r3 = new Rect2D(p1,new Point2D(4,4));
		GUIShape r3G=new GUIShape(r3,false, Color.pink, 0);
		Segment2D s2 = new Segment2D(p2,new Point2D(112,100));
		GUIShape s2G=new GUIShape(s2,false, Color.pink, 0);
		_shapesCol.removeAll();
		_shapesCol.add(t2G); // 0
		_shapesCol.add(r2G); // 0
		_shapesCol.add(r3G); // 0
		_shapesCol.add(s2G); // 0
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Tag);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(t2G); // 0
		check.add(r2G); // 0
		check.add(r3G); // 0
		check.add(s2G); // 0
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if the sorted collection is sorted from the biggest
	 * tag GUI shape to the smallest
	 */
	void sortByAntiTagTest() {
		c2G.setTag(1);
		c1G.setTag(2);
		rG.setTag(3);
		tG.setTag(4);
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(tG);  // 4
		check.add(rG);  // 3
		check.add(c1G); // 2
		check.add(c2G); // 1
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	
	@Test
	/*
	 * checks if the sorted collection is sorted from the String lowest on the alphabet
	 * to the highest one
	 */
	void sortByToStringTest() {
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_toString);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();boolean same=true;
		check.add(c2G); // GUIShape,-16711936,true,3,Circle2D,100.0,100.0, 90.0
		check.add(rG);  // GUIShape,-20561,false,3,Rect2D,10.0,10.0, 10.0,100.0, 100.0,100.0, 100.0,10.0
		check.add(c1G); // GUIShape,-20561,true,3,Circle2D,10.0,10.0, 127.27922061357856
		check.add(tG);  // GUIShape,-20561,true,4,Triangle2D,10.0,10.0, 100.0,100.0, 100.0,10.0
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if in a collection with equal strings(sorted) the collection
	 * stays the same
	 */
	void sortByToStringEqualsTest() {
		GUI_Shapeable c3G=c2G.copy();
		GUI_Shapeable c4G=c2G.copy();
		GUI_Shapeable c5G=c2G.copy();
		_shapesCol.removeAll();
		_shapesCol.add(c3G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		_shapesCol.add(c4G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		_shapesCol.add(c2G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		_shapesCol.add(c5G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_toString);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(c3G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		check.add(c4G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		check.add(c2G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		check.add(c5G); // Shape- 100.0,100.0, 90.0, filled? false, color- java.awt.Color[r=255,g=175,b=175], tag- 1, is selected? false
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	@Test
	/*
	 * checks if the sorted collection is sorted from the String highest on the alphabet
	 * to the lowest one
	 */
	void sortByAntiToStringTest() {
		ShapeComp comp= new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
		_shapesCol.sort(comp);
		ShapeCollection check= new ShapeCollection();
		check.add(tG);  // GUIShape,-20561,true,4,Triangle2D,10.0,10.0, 100.0,100.0, 100.0,10.0
		check.add(c1G); // GUIShape,-20561,true,3,Circle2D,10.0,10.0, 127.27922061357856
		check.add(rG);  // GUIShape,-20561,false,3,Rect2D,10.0,10.0, 10.0,100.0, 100.0,100.0, 100.0,10.0
		check.add(c2G); // GUIShape,-16711936,true,3,Circle2D,100.0,100.0, 90.0
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++)
			if(!_shapesCol.get(i).equals(check.get(i))) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
	
	@Test
	/*
	 * if the collection as no GUI shapes then it's size is 0,
	 * if so, then all the shapes were removed.
	 */
	void removeAllTest() {
		_shapesCol.removeAll();
		assertEquals(_shapesCol.size(),0);
	}
	
	@Before
	void makePolyAndSegment(){
		Segment2D s2 = new Segment2D(p2,new Point2D(112,100));
		GUIShape s2G=new GUIShape(s2,false, Color.YELLOW, 0);
		Point2D _p1= new Point2D(70,0);
		Point2D _p2= new Point2D(80,10);
		Point2D _p3= new Point2D(70,10);
		Point2D _p4= new Point2D(80,40);
		Point2D _p5= new Point2D(50,20);
		Point2D _p6= new Point2D(0,30);
		Point2D _p7= new Point2D(30,10);
		Point2D _p8= new Point2D(20,0);
		Point2D[] _points= {_p1,_p2,_p3,_p4,_p5,_p6,_p7,_p8};
		Polygon2D poly=new Polygon2D(_points);
		GUIShape polyG=new GUIShape(poly,true, Color.BLUE, 0);
		_shapesCol.add(s2G);
		_shapesCol.add(polyG);
	}
	@Test
	/*
	 * saves the shape collection to the "shapes" file, empties the collection
	 * and creates a new exact one by reading(load) the file.
	 */
	void save_loadTest() {
		_shapesCol.save("shapes");
		ShapeCollection check= new ShapeCollection();
		check.load("shapes");
		boolean same=true;
		for(int i=0;i<Math.max(_shapesCol.size(), check.size());i++) {
			Point2D[] d1=_shapesCol.get(i).getShape().getPoints();
			Point2D[] d2=check.get(i).getShape().getPoints();
			for(int j=0;j<Math.max(_shapesCol.get(i).getShape().getPoints().length, check.get(i).getShape().getPoints().length);j++) {
				if(!_shapesCol.get(i).getShape().getPoints()[j].equals(check.get(i).getShape().getPoints()[j]))
					same=false;
			}
			if(!_shapesCol.get(i).getColor().equals(check.get(i).getColor()))
				same=false;	
			if(_shapesCol.get(i).getTag()!=check.get(i).getTag())
				same=false;	
			if(_shapesCol.get(i).isFilled()!=check.get(i).isFilled())
				same=false;
			if(!same)
				break;
			}
		assertEquals(same,true);
	}

	@Test
	/*
	 * checks if the rectangle that blocks the collection is the one intended.
	 */
	void getBoundingBoxTest() {
		Rect2D check= new Rect2D(new Point2D(10-Math.sqrt(16200),10-Math.sqrt(16200)),new Point2D(190,190));
		Rect2D bounds= _shapesCol.getBoundingBox();
		boolean same=true;
		for(int i=0;i<Math.max(bounds.getPoints().length,check.getPoints().length);i++)
			if(!bounds.getPoints()[i].equals(check.getPoints()[i])) {
				same=false;
				break;
			}
		assertEquals(same,true);
	}
}
