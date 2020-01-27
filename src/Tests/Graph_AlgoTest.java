package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.Node;
import dataStructure.node_data;

public class Graph_AlgoTest {
	static graph g=new DGraph();
	static graph_algorithms graph_alg=new Graph_Algo();
	@BeforeAll
	static void initialize()
	{
		
		g.addNode(new Node());
		g.addNode(new Node());
		g.addNode(new Node());
		g.addNode(new Node());
		g.addNode(new Node());
		g.addNode(new Node());
		g.connect(1, 2, 5);
		g.connect(2, 5, 8);
		g.connect(3, 1, 9);
		g.connect(3, 2, 10);
		g.connect(3, 4, 5);
		g.connect(4, 2, 2);
		g.connect(5, 4, 6);
		g.connect(5, 6, 7);
		g.connect(6, 4,12);
		g.connect(6, 5, 4);
		graph_alg.init(g);
	}
		@BeforeEach
		void setUp() throws Exception {
		}

		@AfterEach
		void tearDown() throws Exception {
		}

		@Test
		void testGraph_Algo() {
			try {
				
				
			}catch(Exception e)
			{
				
			}
			
		}

		@Test
		void testInitGraph() {
			try {
				
				graph_algorithms test=new Graph_Algo();
				test.init(g);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		@Test
		void testInitString() {
			 graph_alg.save("test.txt");
			 Graph_Algo afterInit=new Graph_Algo();
		      afterInit.init("test.txt");
		      assertEquals(graph_alg.isConnected(),afterInit.isConnected());
		}

		
		@Test
		void testIsConnected() {
			
			assertEquals(false, graph_alg.isConnected());
			g.connect(6, 3	, 4);
			assertEquals(false, graph_alg.isConnected());
		}

		@Test
		void testShortestPathDist() {
			
			double path=graph_alg.shortestPathDist(3, 5);
			System.out.println(path);
			assertEquals(15	, path);
			path=graph_alg.shortestPathDist(1, 6);
			assertEquals(20, path);
			
		}

		@Test
		void testShortestPath() {
			boolean flag=true;
			List<node_data> keysroad=new LinkedList<node_data>();
			keysroad=graph_alg.shortestPath(3, 5);
			List<Integer> path=new LinkedList<Integer>() ;
			path.add(3);
			path.add(4);
			path.add(2);
			path.add(5);
			for (int i=0;i<keysroad.size();i++)
			{
				if(keysroad.get(i).getKey()!=path.get(i))
					flag=false;
			}
			assertEquals(path.size(), keysroad.size());
			assertEquals(true, flag);
			
		}
		

		@Test
		void testTSP() {
			boolean flag=true;
			List<Integer> targets=new LinkedList<Integer>();
			targets.add(3);
			targets.add(1);
			targets.add(6);
			targets.add(5);

			List<node_data> path=new LinkedList<node_data>();
			path.addAll(graph_alg.TSP(targets));
			List<node_data> ans=new LinkedList<node_data>();
			ans.add(g.getNode(3));
			ans.add(g.getNode(1));
			ans.add(g.getNode(2));
			ans.add(g.getNode(5));
			ans.add(g.getNode(6));

			
			for (int i=0;i<path.size();i++)
			{
				if(path.get(i)!=path.get(i))
					flag=false;
			}
			assertEquals(path.size(), ans.size());
			assertEquals(true, flag);
		}

		@Test
		void testCopy() {	
			graph copy_graph=graph_alg.copy();
			 Graph_Algo algoCopy=new Graph_Algo();
		        algoCopy.init(copy_graph);
		        graph equalTograph=algoCopy.copy();
			assertEquals(copy_graph.nodeSize(), equalTograph.nodeSize());
		}

	}

