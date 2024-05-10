package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFileChooser;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class Ex4 implements Ex4_GUI{
	private int tag=0;
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs,_bc;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1,_p2;
	private  ArrayList<Point2D> pointsList= new ArrayList<Point2D>();
	
	private  static Ex4 _winEx4 = null;
	
	private Ex4() { init(null); }
	//This method gets a collection of shapes and sets the current colletion to the new one
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) { _winEx4 = new Ex4(); }
		return _winEx4;
	}
	//this method summons a method that draws the shapes for every shape in the collection
	public void drawShapes() {
		StdDraw_Ex4.clear();
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable sh = _shapes.get(i);
				
				drawShape(sh);
			}
			if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	//This method get a GUI shape and draws it on the GUI 
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		//If the shape is a circle it had one point and a radius and a different way of drawing it
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) { StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad); }
			else { StdDraw_Ex4.circle(cen.x(), cen.y(), rad); }
		}
		
		else {
			//If the shape is not a circle it makes an array of all the X's of the shape's points and another for the Y's
			// draws them all the same ways as it draws a polygon
			double[] x=new double[gs.getPoints().length];
			double[] y=new double[gs.getPoints().length];
			for(int i=0;i<gs.getPoints().length;i++) {
				x[i]=gs.getPoints()[i].x();
				y[i]=gs.getPoints()[i].y();
			}
			if(gs instanceof Segment2D)
				StdDraw_Ex4.polygon(x,y);
			else {
				if(isFill) { StdDraw_Ex4.filledPolygon(x,y); }
				else { StdDraw_Ex4.polygon(x,y); }
			}
		}
	}
	//Sets a new color to all the selected shapes in the collection
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) { s.setColor(c); }
		}
	}
	//Sets if the shape is filled or not for all the selected shapes in the collection
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) { s.setFilled(_fill); }
		}
	}
	
	//For functions that don't need points pressed for functioning
	public void actionPerformed(String p) {
		_mode = p;
		//if a color button was pressed, sets the color to be the one who was pressed
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		//if a fill or empty button was pressed, sets the shape to be filled or not.
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		//if the "clear" button was clicked, removes all GUI shapes from the collection
		if(p.equals("Clear")) {_shapes.removeAll();}
		//summons "remove"
		if(p.equals("Remove")) {remove();}
		//summons "all"
		if(p.equals("All")) {all();}
		//summons "anti"
		if(p.equals("Anti")) {anti();}
		//summons "none"
		if(p.equals("None")) {none();}
		//summons "getInfo"
		if(p.equals("Info")) {
			String info =getInfo();
			System.out.println(info);
		}
		//the following sorting ways make a comparator by the way pressed and summons the sorting method.
		if(p.equals("ByArea")) {
			int flag=Ex4_Const.Sort_By_Area;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		if(p.equals("ByAntiArea")) {
			int flag=Ex4_Const.Sort_By_Anti_Area;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		if(p.equals("ByPerimeter")) {
			int flag=Ex4_Const.Sort_By_Perimeter;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		if(p.equals("ByAntiPerimeter")) {
			int flag=Ex4_Const.Sort_By_Anti_Perimeter;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		if(p.equals("ByToString")) {
			int flag=Ex4_Const.Sort_By_toString;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		if(p.equals("ByAntiToString")) {
			int flag=Ex4_Const.Sort_By_Anti_toString;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		if(p.equals("ByTag")) {
			int flag=Ex4_Const.Sort_By_Tag;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		if(p.equals("ByAntiTag")) {
			int flag=Ex4_Const.Sort_By_Anti_Tag;
			ShapeComp comp= new ShapeComp(flag);
			_shapes.sort(comp);
			}
		//allows to save the shapes in the GUI to a file to be opened again after the program is closed and summoning "save"
		if(p.equals("Save")) {
			JFileChooser file= new JFileChooser();
			int temp = file.showSaveDialog(null);
			if(temp==JFileChooser.APPROVE_OPTION) {
				String fileName=file.getSelectedFile().getPath();
				_shapes.save(fileName);
			}
		}
		//allows to open the file saved with GUI shapes as it was saved by and summoning "load
		if(p.equals("Load")) {
			JFileChooser file= new JFileChooser();
			int temp = file.showSaveDialog(null);
			if(temp==JFileChooser.APPROVE_OPTION) {
				String fileName=file.getSelectedFile().getPath();
			_shapes.load(fileName);
			}
		}
	
		drawShapes();	
	}
	
	//gets the points clicked for drowing the shapes
	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		//these shapes only need 2 shapes so done after the second point is inputed
		if(_mode.equals("Circle") || _mode.equals("Segment") || _mode.equals("Rect")) {
			if(_gs==null) {_p1 = new Point2D(p);}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(tag);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				tag++;
			}
		}
		//this shape only need 3 shapes so done after the third point is inputed
		if(_mode.equals("Triangle")) {
			if(_gs==null) {_p1 = new Point2D(p);}
			else {
				if(_p2==null)
					_p2= new Point2D(p);
				else {
					_gs.setColor(_color);
					_gs.setFilled(_fill);
					_gs.setTag(tag);
					_shapes.add(_gs);
					_gs = null;
					_p1 = null;
					_p2 = null;
					tag++;
				}
			}
		}
		//this shape will keep getting points untill right click is pressed
		if(_mode.equals("Polygon")) {
			_p1 = new Point2D(p);
			pointsList.add(_p1);
			_bc=_gs;
		}
		
		//These functions need 2 points 
		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				//creates a vector every selected shape will be moved by
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		if(_mode.equals("Copy")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				//creates a vector every selected shape will be moved by
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}
		if(_mode.equals("Rotate")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				//finds the angle between the two given points
				double infront= p.y()-_p1.y();
				double angle=Math.asin(infront/_p1.distance(p));
				if(p.x()<_p1.x()) { angle=Math.PI-angle; }
				rotate(angle);
				_p1 = null;
			}
		}
		//this functions need one point, scale scales every selected shape by the ratio between him and the shapes
		if(_mode.equals("Scale_90%")) {
			_p1 = new Point2D(p.x(), p.y());
			scale(0.9);
			_p1 = null;
		}
		if(_mode.equals("Scale_110%")) {
			_p1 = new Point2D(p.x(), p.y());
			scale(1.1);
			_p1 = null;
		}
		//summons the "select" method
		if(_mode.equals("Point")) {select(p);}
		
		drawShapes();
	}
	//if shapes were pressed while mode is "point", set the shapes to selected
	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p))
				s.setSelected(!s.isSelected());
		}
	}
	//moves every selected shape and draws it by the vector created 
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected())
				g.move(_p1);
		}
	}
	//makes a new same shape for every selected shape and draws it by the vector created 
	private void copy() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i).copy();
			if(_shapes.get(i).isSelected()) {
				s.getShape().move(_p1);
				_shapes.add(s);
			}
		}
	}
	//scales every selected shape by the ratio given
	private void scale(double ratio) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected())
				g.scale(_p1, ratio);
		}
	}
	//rotates every selected shape by the degrees given
	private void rotate(double angel) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected())
				g.rotate(_p1,angel);
		}
	}
	//removes all the selected GUI shapes from the collection
	private void remove() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected()) {
				_shapes.removeElementAt(i);
				i--;
			}
		}
	}
	//selects all GUI shapes in the collection
	private void all() {
		for(int i=0;i<_shapes.size();i++) {
			_shapes.get(i).setSelected(true);
		}
	}
	//selects all unselected shapes, and sets all selected ones to unselected
	private void anti() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected())
				_shapes.get(i).setSelected(false);
			else
				_shapes.get(i).setSelected(true);
		}
	}
	//sets all GUI shapes in the collection as not selected
	private void none() {
		for(int i=0;i<_shapes.size();i++) {
			_shapes.get(i).setSelected(false);
		}
	}

	//If clicked, stops collecting new points for a polygon and draws it, or deleted the progress for the rest of the shapes.
	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
			if(_mode.equals("Polygon")) {
				if(_bc!=null) {
					_bc.setColor(_color);
					_bc.setFilled(_fill);
					_bc.setTag(tag);
					_shapes.add(_bc);
					_bc = null;
					tag++;
				}
			}
			_gs = null;
			_p1 = null;
			_p2 = null;
			pointsList.removeAll(pointsList);
			drawShapes();
	
	}
	//creates a new shapes on the GUI showing what the shape will look like after being drown
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
		//	System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1,y1);
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}
			if(_mode.equals("Segment")) {
				gs = new Segment2D(_p1,p);
			}
			if(_mode.equals("Rect")) {
				gs = new Rect2D(_p1,p);
			}
			if(_mode.equals("Triangle")) {
				if(_p2==null)
					gs = new Segment2D(_p1,p);
				else
					gs = new Triangle2D(_p1,_p2,p);
			}
			if(_mode.equals("Polygon")) {
				pointsList.add(p);
				Object[] objects= pointsList.toArray();
				pointsList.remove(pointsList.size()-1);
				Point2D[] points=new Point2D[objects.length];
				for(int i=0;i<Math.max(points.length,objects.length);i++)
					points[i]=(Point2D) objects[i];
				gs = new Polygon2D(points);
			}
			if(gs!=null)
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	/**
	 * returns the main GUI shape collection
	 * @return
	 */
	public ShapeCollectionable getShape_Collection() {
		return this._shapes;
	}
	@Override
	/**
	 * summons the show function with the size given for the GUI
	 */
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	/**
	 * returns the info of each GUI shape in the collection
	 * @return
	 */
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}