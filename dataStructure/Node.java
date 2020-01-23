package dataStructure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import utils.Point3D;

public class Node implements node_data, Serializable{
	public HashMap<Integer, edge_data> neighbours = new HashMap<>();
	
	private int key, tag;
	private Point3D location;
	private Double weight;
	private String info;


	//****************constructors******************

	public Node() {
		key = 0;
		tag = 0;
		weight =0.0;
		location = null;
		info = "";
	}
	
	
	public Node(int key,Point3D location, double Weight, String info, int tag) {
		this.info = info;
		this.weight = Weight;
		this.location = location;
		this.tag = tag;
		this.key = key;
	}
	
	
	public Node(Node other) {
		this.key = other.key;
		this.weight = other.weight;
		this.location = other.location;
		this.info = other.info;
		this.tag = other.tag;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Point3D getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point3D p) {
		location = p;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		weight = w;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		info = s;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		tag = t;
	}
	/**
	 * An iterator that loop on the neighbours of a node.
	 * @return the iterator.
	 */
	public Iterator<edge_data> edgeitr() {
		return neighbours.values().iterator();
	}
	/**
	 * This method print the key of the node.
	 */
	public String toString() {
		return this.key+"";
	}
}