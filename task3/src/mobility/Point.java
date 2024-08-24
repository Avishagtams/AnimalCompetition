package mobility;

import graphics.ICloneable;

/**
 * The Class Point implements IClonable  X axis integer negative value Y axis is the integer and non-negative value
 *
 */
public class Point implements ICloneable  {
	private int x;
	private int y;
	/**
	 * constructor
	 * @param x
	 * @param y
	 */
	public Point(int x,int y){
		if(!setX(x))
			this.x=0;
		if(!setY(y))
			this.y=0;
	}
	/**
	 * copy constructor
	 * @param point
	 */

	public Point(Point point){
		this.x=point.x;
		this.y=point.y;
	}
	/**
	 * The function setX
	 * @param x
	 * @return true if x chance else return false
	 */
	public  boolean setX(int x) {
		if(x>=0) {
			this.x=x;
			return true;
		}
		return false;

	}
	/**
	 * The function setY
	 * @param y
	 * @return true if y chance else return false
	 */
	public  boolean setY(int y) {
		if(y>=0) {
			this.y=y;
			return true;
		}
		return false;
	}
	/**
	 * The function getX
	 * @return x
	 */
	public int getX() {return x;}
	/**
	 * The function getY
	 * @return y
	 */
	public int getY() {return y;}
	/**
	 * The function equal
	 * @param point
	 * @return true if the points is equals else return false
	 */
	public boolean equal(Point point) {
		if(point.x==x && point.y==y)
			return true;
		return false;
	}
	/**
	 * The function toString 
	 * @return a string of the point in format(x,y)
	 */
	public String toString() {
		return "("+this.x+","+this.y+")\n";
	}


}
