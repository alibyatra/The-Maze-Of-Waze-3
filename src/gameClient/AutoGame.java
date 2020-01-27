package gameClient;

import dataStructure.DGraph;
import elements.Fruit;
import dataStructure.edge_data;
import dataStructure.node_data;
import elements.Fruit_Comperator;
import elements.Robot;

import java.util.ArrayList;
import java.util.List;

import Server.Game_Server;
import Server.game_service;

public class AutoGame {

	private MyGameGUI my = new MyGameGUI();
	private Fruit_Comperator fc = new Fruit_Comperator();
	double EPSILON = 0.00000001;
	private game_service game = Game_Server.getServer(Json_Updates.mu);
	private DGraph d = new DGraph();

	public AutoGame() {
		this.d = new DGraph();
		this.my = new MyGameGUI();
	}
	public AutoGame(MyGameGUI my) {
		// TODO Auto-generated constructor stub
		this.my = my;
		this.d = my.getDgraph();
		this.game = my.getGame();
	}

	public void setGui(MyGameGUI g) {
		my = g;
	}

	public MyGameGUI getGUI() {
		return my;
	}

	public game_service getGame() {
		return game;
	}

	public void setGame(game_service game) {
		this.game = game;
	}

	public DGraph getD() {
		return d;
	}

	public void setD(DGraph d) {
		this.d = d;
	}


	public void allFruitToEdges(List<Fruit> list) {
		for(Fruit f : list) {
			FruitToEdge(f);
		}
	}

	public void FruitToEdge(Fruit f) {
		for(edge_data edge : d.allEdges) {
			int src=-2;
			int dest = -2;
			if((f.getType()==-1)) {//Banana
				if(edge.getSrc()>edge.getDest()) {
					src = edge.getSrc();
					dest = edge.getDest();
				}else {
					dest = edge.getSrc();
					src = edge.getDest();
				}
			}
			else{ //Apple
				if(edge.getSrc()<edge.getDest()) {
					src = edge.getSrc();
					dest = edge.getDest();
				}else {
					dest = edge.getSrc();
					src = edge.getDest();
				}
			}
			double disNodes = d.getNode(src).getLocation().distance2D(d.getNode(dest).getLocation());
			double dis2f = d.getNode(src).getLocation().distance2D(f.getPos());
			double dis4f = d.getNode(dest).getLocation().distance2D(f.getPos());

			if(((disNodes - (dis2f+dis4f))<=EPSILON) && ((disNodes - (dis2f+dis4f))>=(EPSILON*-1))){
				f.setSrc(src);
				f.setDest(dest);
			}		
		}
	}


	public void addAutoRobot(){
		int src =0;
		int dest =0;
		allFruitToEdges(d.fruitList);
		d.fruitList.sort(fc);
		for(int i=0; i< d.getNumRobot();i++) {

			src = d.fruitList.get(i).getSrc();
			dest = d.fruitList.get(i).getDest();
			game.addRobot(src);
			d.addRobot(new Robot(1, src, dest, i,d.getNode(src).getLocation()));

		}
	}

	private Fruit closestFruit(Robot r, List<Fruit> list) {
		list.sort(fc);
		Fruit closest = list.get(0);
		double temp =0;
		double tmpFruit = Double.MAX_VALUE;
		for(int i=0; i<list.size()-1;i++) {
			temp = my.getAlgo().shortestPathDist(r.getSrc(), list.get(i).getSrc());

			if(temp<tmpFruit){

				tmpFruit = temp;

				if(!(list.contains(closest)))
					list.add(closest);

				closest = list.get(i);
				list.remove(list.get(i));
			}
		}
		return closest;
	}

	private List<Fruit> leftZone(List<Fruit> list){
		ArrayList<Fruit> left = new ArrayList<Fruit>();
		for(Fruit f: list) {
			if(f.getPos().x()<35.200160479418884) 
				left.add(f);	
		}
		return left;
	}

	private List<Fruit> rightZone(List<Fruit> list){
		ArrayList<Fruit> right = new ArrayList<Fruit>();
		for(Fruit f: list) {
			if(f.getPos().x()>=35.200160479418884) 
				right.add(f);	
		}
		return right;
	}

	public void AutoNextNode(List<Robot> list) {
		Fruit closest = null;
		ArrayList<Fruit> left = (ArrayList<Fruit>) leftZone(d.fruitList);
		ArrayList<Fruit> right = (ArrayList<Fruit>) rightZone(d.fruitList);

		int nextEdge = 0;
		ArrayList<node_data> nodeList = new ArrayList<node_data>();
		for(Robot r : list) {
			if(list.size()>1) {
				if(r.getId()==1) {
					if(!left.isEmpty())
						closest= closestFruit(r, left);
				}
				else if(r.getId()==2) {
					if(!right.isEmpty())
						closest= closestFruit(r, right);
				}
				else
					closest = closestFruit(r,d.fruitList);
			}
			else
				closest = closestFruit(r,d.fruitList);

			double fromFruit = r.getPos().distance2D(closest.getPos());
			if(fromFruit<0.0013) 
				MyGameGUI.sleepTime = 60; //near the fruit

			else
				MyGameGUI.sleepTime = 105;

			if(r.getSrc()==closest.getSrc()) {
				game.chooseNextEdge(r.getId(), closest.getDest());
			}
			else {
				nodeList = (ArrayList<node_data>) my.getAlgo().shortestPath(r.getSrc(), closest.getSrc());	
				nextEdge = nodeList.get(1).getKey();
				game.chooseNextEdge(r.getId(), nextEdge);
			}
		}
	}
}
