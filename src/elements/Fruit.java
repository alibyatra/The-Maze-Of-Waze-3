package elements;

import utils.Point3D;

public class Fruit {

	private double value;
	private int type;
	private Point3D pos;
	private int src;
	private int dest;

	
	public Fruit(double v, int t, Point3D p) {
		value = v;
		type = t;
		pos =p;
		src = 0;
		dest = 0;
	}
	/**
	 * @return the src of the fruit.
	 */
	public int getSrc() {
		return src;
	}
	/**
	 * set the src of the fruit.
	 * @param src
	 */
	public void setSrc(int src) {
		this.src = src;
	}
	/**
	 * @return the dest of the fruit.
	 */
	public int getDest() {
		return dest;
	}
	/**
	 * set the dest of the fruit.
	 * @param dest
	 */
	public void setDest(int dest) {
		this.dest = dest;
	}

	/**
	 * @return the value of the fruit.
	 */
	public double getValue() {
		return value;
	}
	/**
	 * set the value of the fruit.
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * @return the type of the fruit.
	 */
	public int getType() {
		return type;
	}
	/**
	 * set the type of the fruit.
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the position of the fruit.
	 */
	public Point3D getPos() {
		return pos;
	}
	/**
	 * set the position of the fruit.
	 * @param pos
	 */
	public void setPos(Point3D pos) {
		this.pos = pos;
	}
}

