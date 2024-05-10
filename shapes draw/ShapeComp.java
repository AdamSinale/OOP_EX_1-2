package Exe.Ex4.geo;

import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
//Adam Sin-322453689, Tomer Shor-325511541
public class ShapeComp implements Comparator<GUI_Shapeable>{
	//////////add your code below ///////////
	
	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
		
	}
	@Override
	/**
	 * This method gets to GUI shapes and returns a positive number if they are 
	 * not sorted by the way the flag requires and a negative number else wise
	 * @param o1,o2
	 * @return
	 */
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		//////////add your code below ///////////
		if(_flag == Ex4_Const.Sort_By_Anti_toString) {
			ans = o2.toString().compareTo(o1.toString());
		}
		if(_flag == Ex4_Const.Sort_By_Area) {
			ans = o1.getShape().area()<=o2.getShape().area()? -1 : 1;
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Area) {
			ans = o1.getShape().area()>=o2.getShape().area()? -1 : 1;
		}
		if(_flag == Ex4_Const.Sort_By_Perimeter) {
			ans = o1.getShape().perimeter()<=o2.getShape().perimeter()? -1 : 1;
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			ans = o1.getShape().perimeter()>=o2.getShape().perimeter()? -1 : 1;
		}
		if(_flag == Ex4_Const.Sort_By_Tag) {
			ans = o1.getTag()<=o2.getTag()? -1 : 1;
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
			ans = o1.getTag()>=o2.getTag()? -1 : 1;
		}
		//////////////////////////////////////////
		return ans;
	}

}