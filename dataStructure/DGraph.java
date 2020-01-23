package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import elements.Fruit;
import elements.Robot;

public class DGraph implements graph, Serializable
{
	public int MC=0;
	public int EdC=0;
	private int numRobot = 0;
	public HashMap<Integer, node_data> nodesMap = new HashMap<>();
	public HashMap<Integer, HashMap<Integer,edge_data>> edgesMap = new HashMap<Integer, HashMap<Integer,edge_data>>();
	public ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
	public ArrayList<Robot> robotList = new ArrayList<Robot>();
	
	@Override
	public node_data getNode(int key) {
		if (this.nodesMap.get(key)==null) 
		{ 
			return null; 
		}
		return this.nodesMap.get(key); 
	}

	@Override
	public edge_data getEdge(int src, int dest) 
	{
		if (this.edgesMap.get(src)!=null) 
		{
			if (this.edgesMap.get(src).get(dest) != null) 
			{
				return (edge_data)(this.edgesMap.get(src).get(dest)); 
			}
		}
		return null;
	}

	@Override
	public void addNode(node_data n) 
	{
		int key=n.getKey();
		this.nodesMap.put(key, (Node)n);
		this.MC++;
	}

	@Override
	public void connect(int src, int dest, double w) 
	{
		if((nodesMap.get(src)==null) || (nodesMap.get(dest)==null)) 
		{
			throw new RuntimeException("Eror, unsuccessfully connect");
		}
		if(((Node)nodesMap.get(src)).neighbours.containsKey(dest)) 
		{
			throw new RuntimeException("couldn't create another one");
		}
		Edge e = new Edge(src, dest, 0, w , null);
		EdC++;
		((Node)nodesMap.get(src)).neighbours.put(dest, e);
		MC++;
			}
	
	@Override
	public Collection<node_data> getV() 
	{
		if (this.nodesMap.isEmpty()) 
		{ 
			return null; 
		}
		return this.nodesMap.values(); 
	}

	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		if (this.edgesMap.isEmpty()) 
		{ 
			return null; 
		}
		if (this.edgesMap.get(node_id)==null) 
		{ 
			return null; 
		}
		return this.edgesMap.get(node_id).values(); 
	}

	@Override
	public node_data removeNode(int key) {
		if(nodesMap.containsKey(key)) {

			EdC-=((Node)nodesMap.get(key)).neighbours.values().size();

			((Node)nodesMap.get(key)).neighbours.clear();
			Iterator<Integer> it = nodesMap.keySet().iterator();
			while(it.hasNext()) 
			{
				removeEdge(it.next(), key);
			}	
				MC++;
			return nodesMap.remove(key);
		}
		else
			return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(((Node)nodesMap.get(src)).neighbours.containsKey(dest)) {
			EdC--;
			MC++;
			return ((Node)nodesMap.get(src)).neighbours.remove(dest);
		}
		else
			return null;
	}
	
	public void addFruit(Fruit f) 
	{
		fruitList.add(f);
	}
	
	public void addRobot(Robot r) 
	{
		robotList.add(r);
	}

	public int getNumRobot() 
	{
		return numRobot;
	}

	public void setNumRobot(int numRobot) 
	{
		this.numRobot = numRobot;
	}
	
	public Iterator<node_data> nodeitr() 
	{
		return nodesMap.values().iterator();
	}
	@Override
	public int nodeSize() 
	{
		return this.nodesMap.size();
	}

	@Override
	public int edgeSize() 
	{
		return EdC;
	}

	@Override
	public int getMC() 
	{
		return MC;
	}

}
