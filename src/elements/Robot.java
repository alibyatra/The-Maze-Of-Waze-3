package elements;

import utils.Point3D;

public class Robot {

	private int speed, src, dest, id;
	private double value;
	private Point3D pos;

	public Robot(int speed, int src, int dest, int id, Point3D pos) {
		this.speed = speed;
		this.src = src;
		this.dest = dest;
		this.id = id;
		this.value = 0;
		this.pos = pos;
	}
	/**
	 * @return the speed of the robot.
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * set the speed of the robot.
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * @return the src of the robot.
	 */
	public int getSrc() {
		return src;
	}
	/**
	 * set the src of the robot.
	 * @param src
	 */
	public void setSrc(int src) {
		this.src = src;
	}
	/**
	 * @return the dest of the robot.
	 */
	public int getDest() {
		return dest;
	}
	/**
	 * set the dest of the robot.
	 * @param dest
	 */
	public void setDest(int dest) {
		this.dest = dest;
	}
	/**
	 * get the id of the robot.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * set the id of the robot.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get the robot value.
	 * @return value.
	 */
	public double getValue() {
		return value;
	}
	/**
	 * set the robot value.
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * get the robot position.
	 * @return position.
	 */
	public Point3D getPos() {
		return pos;
	}
	/**
	 * set the robot position.
	 * @param pos
	 */
	public void setPos(Point3D pos) {
		this.pos = pos;
	}
}
