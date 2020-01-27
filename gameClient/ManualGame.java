package gameClient;

import java.util.Collection;

import com.sun.glass.events.KeyEvent;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import elements.Robot;
import utils.Point3D;
import utils.StdDraw;

public class ManualGame {
	private static int robPoint = 0;
	private DGraph d = new DGraph();
	double EPSILON = 0.0005;
	private game_service game = Game_Server.getServer(Json_Updates.mu); // you have [0,23] games.
	private String[] RobotsImg = {"pics\\mario.png","pics\\luigi.png","pics\\peach.png"};

/**
 * A constructor that gets MyGameGUI object as an argument and set the parameters bt it.
 * @param my
 */
	public ManualGame(MyGameGUI my) {
		this.d = my.getDgraph();
		this.game = my.getGame();
	}

	/**
	 * Method thats allow the player to locate the robots before the game starts,
	 * by clicking on the screen.
	 */
	public void addManualRobots() {
		int i=0;
		int num = d.getNumRobot();
		while(i<num) {
			if(StdDraw.isMousePressed()) {
				StdDraw.isMousePressed = false;

				Collection<node_data> point = d.getV();

				for (node_data nodes : point) {

					double x = nodes.getLocation().x();
					double y = nodes.getLocation().y();

					if((StdDraw.mouseX()-EPSILON <= x)&&(x<= StdDraw.mouseX()+EPSILON) 
							&& (StdDraw.mouseY()-EPSILON<=y)&&(y<=StdDraw.mouseY()+EPSILON)) {

						StdDraw.picture(x, y, RobotsImg[i] , 0.001, 0.001);
						Point3D p = new Point3D(x,y);
						Robot r = new Robot(1, nodes.getKey(), -1, i, p);
						game.addRobot(r.getSrc());
						d.addRobot(r);
						i++;
					}
				}
			}		
		}
	}
	/**
	 * Method to allow the player to control a specific robot,
	 * using the keyboard.
	 */
	public void chooseRobot() {
		int rCount = d.getNumRobot();
		if(StdDraw.isKeyPressed(KeyEvent.VK_1)) {
			robPoint = 0;
		}
		else if((rCount > 1) && (StdDraw.isKeyPressed(KeyEvent.VK_2))) {
			robPoint = 1;
		}
		else if((rCount > 2) && (StdDraw.isKeyPressed(KeyEvent.VK_3))) {
			robPoint = 2;
		}
		int newDest = checkIfNext(d.robotList.get(robPoint).getSrc());
		game.chooseNextEdge(d.robotList.get(robPoint).getId(), newDest);
		game.move();
	}
/**
 * This method checks if the player clicked on a valid node,
 * a valid node is a node that is a neighbor of the current node.
 * @param src - the current node.
 * @return
 */
	private int checkIfNext(int src) {
		int dest=-2;
		if(StdDraw.isMousePressed()) {
			StdDraw.isMousePressed = false;

			for (edge_data edge : (d.getE(src))) {

				double x = d.getNode(edge.getDest()).getLocation().x();
				double y = d.getNode(edge.getDest()).getLocation().y();

				if((StdDraw.mouseX()-EPSILON <= x)&&(x<= StdDraw.mouseX()+EPSILON) 
						&& (StdDraw.mouseY()-EPSILON<=y)&&(y<=StdDraw.mouseY()+EPSILON)) {

					dest = d.getNode(edge.getDest()).getKey();
				}
			}
		}
		return dest;
	}
}
