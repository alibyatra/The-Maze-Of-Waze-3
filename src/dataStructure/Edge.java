package dataStructure;

import java.io.Serializable;

public class Edge implements edge_data, Serializable{

	private int src, dest, tag;
	private double weigth;
	private String info;
	
	public Edge() {
		src = 0;
		dest = 0;
		tag = 0;
		weigth = 0.0;
		info = "";
	}
	/**
	 * Full constructor.
	 * @param src - The id of the source node of this edge.
	 * @param dest - The id of the destination node of this edge.
	 * @param tag - temporal data (aka color: e,g, white, gray, black).
	 * @param weigth - the weight of this edge (positive value).
	 * @param info -the remark (meta data) associated with this edge.
	 */
	public Edge(int src, int dest, int tag, double weigth, String info) {
		this.src = src;
		this.dest = dest;
		this.tag = tag;
		this.weigth = weigth;
		this.info = info;
	}
	
	public Edge(Edge other) {
		this.src = other.src;
		this.dest = other.dest;
		this.tag = other.tag;
		this.weigth = other.weigth;
		this.info = other.info;
	}
	
	
	public int getSrc() {
		return src;
	}

	
	public int getDest() {
		return dest;
	}

	
	public double getWeight() {
		return weigth;
	}

	
	public String getInfo() {
		return info;
	}

	
	public void setInfo(String s) {
			info = s;
	}

	
	public int getTag() {
		return tag;
	}

	
	public void setTag(int t) {
			tag = t;
	}

}