package Exe.Ex4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	/**
	 * This method gets an index, removes the element from
	 * the shapeCollection and returns it.
	 * @param i
	 * @return
	 */
	public GUI_Shapeable removeElementAt(int i) {
		//////////add your code below ///////////
		if(i>=_shapes.size())
			return null;
		GUI_Shapeable removed = _shapes.get(i);
		this._shapes.remove(i);
		return removed;
		//////////////////////////////////////////
	}

	@Override
	/**
	 * This method gets an index and a GUI_Shapeable(shape on the GUI)
	 * and adds the shape to the shapeCollection at the place of the index 
	 * given, the points at the index and higher move to the next index.
	 * @param s,i
	 */
	public void addAt(GUI_Shapeable s, int i) {
		//////////add your code below ///////////
		if(i<_shapes.size() && s!=null && s.getShape()!=null) {
			this._shapes.add(i,s);
		}
		//////////////////////////////////////////
	}
	@Override
	/**
	 * This method gets a GUI_Shapeable(shape on the GUI) and
	 * adds the shape to the shapeCollection to the last place.
	 * @param s
	 */
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	
	@Override	
	/**
	 * This method returns a new identical shapeCollection
	 * to the one the method was applied on.
	 */
	public ShapeCollectionable copy() {
		//////////add your code below ///////////
		ShapeCollectionable copy= new ShapeCollection();;
		for(int i=0;i<this.size();i++)
			copy.add(_shapes.get(i));
		return copy;
		//////////////////////////////////////////
	}
	@Override
	/**
	 * This method gets a comparator, and sorts the GUI shapes
	 * in it by the order given by the comparator. if the comparator 
	 * returns a positive number, the method switches every two shapes
	 * and is done when it over the whole collection without switching
	 * between any shapes.
	 * @param comp
	 */
	public void sort(Comparator<GUI_Shapeable> comp) {
		//////////add your code below ///////////
		boolean sorted=true;
		while (sorted) {
		sorted=false;
			for(int i=0;i<_shapes.size()-1;i++) {
				if(comp.compare(_shapes.get(i), _shapes.get(i+1))>0) {
					GUI_Shapeable first=_shapes.remove(i);
					GUI_Shapeable second=_shapes.remove(i);
					_shapes.add(i,second);
					_shapes.add(i+1,first);
					sorted=true;
				}
			}
		}
		//////////////////////////////////////////
	}

	@Override
	/**
	 * This method removes all of the GUI shapes of the collection it was applied on.
	 */
	public void removeAll() {
		//////////add your code below ///////////
		_shapes.removeAll(_shapes);
		//////////////////////////////////////////
	}

	@Override
	/*
	 * This method gets a file and writes the ToString of every GUI
	 * shape in the file
	 */
	public void save(String file) {
		//////////add your code below ///////////
		try {
			FileWriter fw = new FileWriter(file);
			PrintWriter outs = new PrintWriter(file);
			for(int i=0;i<_shapes.size();i++) 
				outs.println(_shapes.get(i));
			outs.close();
			fw.close();
		}
		catch(IOException ex) {
			System.out.println("Error writing file\n" + ex);
		}
		//////////////////////////////////////////
	}

	@Override
	/*
	 * This method gets a file reads every line(a GUI shape) and
	 * creates a new GUI shape with the same features and adds it
	 * to a new collection
	 */
	public void load(String file) {
		////////// add your code below ///////////
		removeAll();
		try {
			FileReader fr=new FileReader(file);
			BufferedReader br= new BufferedReader(fr);
			String shapeString= br.readLine();
			//splits the first shape's ToString to its features as strings
			String[] features= shapeString.split(",",6);
			// Now we need to change every feature from string to it's type:
			// finds the color of the first shape
			Color color= new Color(Integer.parseInt(features[1]));
			// finds if the first shape is filled or not
			boolean filled;
			if(features[2].charAt(0)=='f') { filled= false; }
			else { filled= true; }
			// finds the tag of the first shape
			int tag = Integer.parseInt(features[3]);
			// Now for the shapes points:
			// Splits the points string to numbers of the type string.
			//(the even indexes are the X value and the odds ones are the Y value)
			String[] pointsS= features[5].split(",");
			//For a circle, the pointsS array has 3 values(x of the center,y of the center, radius)
			Point2D center= new Point2D(Double.parseDouble(pointsS[0]),Double.parseDouble(pointsS[1]));
			GeoShapeable shape= new Circle2D(center,Double.parseDouble(pointsS[2]));
			//For the other shapes, there are only points with (x,y) each so its an even array.
			Point2D[] points=new Point2D[pointsS.length/2];
			if(!features[4].equals("Circle2D")) {
				int j=0;
				for(int i=0;i<points.length;i=i+2)
					//converts the string x,y,x,y... array to a points array
					points[i]= new Point2D(Double.parseDouble(pointsS[i]),Double.parseDouble(pointsS[i+1]));
				j++;
			}
			//For a rectangle, there will be only 2 points
			if(features[4].equals("Rect2D")) {
				shape= new Rect2D(points[0],points[2]);
			}
			//For a segment, there will be only 2 points
			if(features[4].equals("Segment2D")) {
				shape= new Segment2D(points[0],points[1]);
			}
			//For a triangle, there will be only 2 points
			if(features[4].equals("Triangle2D")) {
				shape= new Triangle2D(points[0],points[1],points[2]);
			}
			//For a polygon, we send the array
			if(features[4].equals("Polygon2D")) {
				shape= new Polygon2D(points);
			}
			//Now we can create the first GUI shape from the file.
			GUI_Shapeable guis= new GUIShape(shape,filled,color,tag);
			_shapes.add(guis);
			//Repeats the same process for he other GUI shapes by order
			while(shapeString!=null) {
				shapeString=br.readLine();
				if(shapeString!=null) {
					features= shapeString.split(",",6);
					color= new Color(Integer.parseInt(features[1]));
					if(features[2].charAt(0)=='f') { filled= false; }
					else { filled= true; }
					tag = Integer.parseInt(features[3]);
					pointsS= features[5].split(",");
					if(features[4].equals("Circle2D")) {
						center= new Point2D(Double.parseDouble(pointsS[0]),Double.parseDouble(pointsS[1]));
						shape= new Circle2D(center,Double.parseDouble(pointsS[2]));
					}
					else {
						points=new Point2D[pointsS.length/2];
						int j=0;
						for(int i=0;j<points.length;i=i+2) {
							points[j]= new Point2D(Double.parseDouble(pointsS[i]),Double.parseDouble(pointsS[i+1]));
							j++;
						}
					}
					if(features[4].equals("Rect2D")) {
					shape= new Rect2D(points[0],points[2]);
					}
					if(features[4].equals("Segment2D")) {
						shape= new Segment2D(points[0],points[1]);
					}
					if(features[4].equals("Triangle2D")) {
						shape= new Triangle2D(points[0],points[1],points[2]);
					}
					if(features[4].equals("Polygon2D")) {
						shape= new Polygon2D(points);
					}
					guis= new GUIShape(shape,filled,color,tag);
					_shapes.add(guis);
				}
			}
			br.close();
		}
		catch(IOException ex) {
			System.out.println("Error reading file\n" + ex);
			System.exit(2);
		}
		//////////////////////////////////////////
	}
	@Override
	/**
	 * This method returns the minimal rectangle that blocks all of the shapes of the collection
	 * by finding the smallest and biggest Xs and Ys and creating the minimal and maximal
	 * points that contain the whole collection and creates a rectangle with them.
	 * @return
	 */
	public Rect2D getBoundingBox() {
		//////////add your code below ///////////
		double minX,minY,maxX,maxY;
		if(_shapes.get(0).getShape() instanceof Circle2D) {
			Circle2D c=(Circle2D) _shapes.get(0).getShape();
			double r=c.getRadius();
			minX=c.getPoints()[0].x()-r;
			minY=c.getPoints()[0].y()-r;
			maxX=c.getPoints()[0].x()+r;
			maxY=c.getPoints()[0].y()+r;
		}
		else {
			Point2D point=_shapes.get(0).getShape().getPoints()[0];
			maxX=point.x();
			minX=point.x();
			maxY=point.y();
			minY=point.y();
			for(int j=1;j<_shapes.get(0).getShape().getPoints().length;j++) {
				point=_shapes.get(0).getShape().getPoints()[j];
				if(point.x()>maxX)
					maxX=point.x();
				if(point.x()<minX)
					minX=point.x();
				if(point.y()>maxY)
					maxY=point.y();
				if(point.y()<minY)
					minY=point.y();
			}
		}
		for(int i=1;i<this.size();i++) {
			if(_shapes.get(i).getShape() instanceof Circle2D) {
				Circle2D c=(Circle2D) _shapes.get(i).getShape();
				double r=c.getRadius();
				if(minX>c.getPoints()[0].x()-r)
					minX=c.getPoints()[0].x()-r;
				if(minY>c.getPoints()[0].y()-r)
					minY=c.getPoints()[0].y()-r;
				if(maxX<c.getPoints()[0].x()+r)
					maxX=c.getPoints()[0].x()+r;
				if(maxY<c.getPoints()[0].y()+r)
					maxY=c.getPoints()[0].y()+r;
			}
			else {
				for(int j=0;j<_shapes.get(i).getShape().getPoints().length;j++) {
					Point2D point=_shapes.get(i).getShape().getPoints()[j];
					if(point.x()>maxX)
						maxX=point.x();
					if(point.x()<minX)
						minX=point.x();
					if(point.y()>maxY)
						maxY=point.y();
					if(point.y()<minY)
						minY=point.y();
				}
			}
		}
		Rect2D ans = new Rect2D(new Point2D(minX,minY),new Point2D(maxX,maxY));
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
}