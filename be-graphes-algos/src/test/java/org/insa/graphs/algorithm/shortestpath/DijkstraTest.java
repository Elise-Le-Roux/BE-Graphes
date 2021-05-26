package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {
	
	// Graphs used for test
	private static Graph graphCarreDense, graphHauteGaronne;
	
	// Solutions
	private static ShortestPathSolution solDijkstra_Carre0, solDijkstra_HG0, solDijkstra_HG1, solDijkstra_HG2, solDijkstra_HG3, solDijkstra_HG4,
	solDijkstra_HG5, solDijkstra_HG6;
	
	private static ShortestPathSolution solAstar_Carre0, solAstar_HG0, solAstar_HG1, solAstar_HG2, solAstar_HG3, solAstar_HG4,
	solAstar_HG5, solAstar_HG6;
	
	private static ShortestPathSolution solBF_HG0, solBF_HG1, solBF_HG2, solBF_HG3, solBF_HG4,
	solBF_HG5, solBF_HG6;
	
	@BeforeClass
    public static void initAll() throws IOException {
		String mapDirectory = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/";
		String mapDirectory2 = "/home/lerouxde/3MIC/Maps/";
		
		// Loading Carre Dense graph
		String mapCarreDense = mapDirectory + "carre-dense.mapgr";
		GraphReader readerCarreDense = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarreDense))));
		Graph graphCarreDense = readerCarreDense.read();
		
		// Loading Haute-Garonne graph
		String mapHauteGaronne = mapDirectory + "haute-garonne.mapgr";
		GraphReader readerHauteGaronne = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapHauteGaronne))));
		Graph graphHauteGaronne = readerHauteGaronne.read();
		
		// Filters
		List<ArcInspector> Filters = ArcInspectorFactory.getAllFilters();
		
		// Random generator
		Random random = new Random();
		
		//************** Carre dense **************/
		
		Node origin1 = graphCarreDense.getNodes().get(random.nextInt(graphCarreDense.size()));
		Node destination1 = graphCarreDense.getNodes().get(random.nextInt(graphCarreDense.size()));
		
		// 0 - All roads allowed and length
		ShortestPathData data_Carre0 = new ShortestPathData(graphCarreDense, origin1, destination1, Filters.get(0));
		DijkstraAlgorithm dijkstra_Carre0 = new DijkstraAlgorithm(data_Carre0); 
		solDijkstra_Carre0 = dijkstra_Carre0.doRun(); 
		AStarAlgorithm Astar_Carre0 = new AStarAlgorithm(data_Carre0);
		solAstar_Carre0 = Astar_Carre0.doRun();
		
		//************* Haute-Garonne *************/
		Node origins[] = new Node[6];
	    Node destinations[] = new Node[6];
		for(int i=0; i<6; i++) {
			origins[i] = graphHauteGaronne.getNodes().get(random.nextInt(graphHauteGaronne.size()));
			destinations[i] = graphHauteGaronne.getNodes().get(random.nextInt(graphHauteGaronne.size()));
		}
		
		// 0 - All roads allowed and length
		ShortestPathData data_HG0 = new ShortestPathData(graphHauteGaronne, origins[0], destinations[0], Filters.get(0));
		DijkstraAlgorithm dijkstra_HG0 = new DijkstraAlgorithm(data_HG0) ; 
		solDijkstra_HG0 = dijkstra_HG0.doRun() ;
		AStarAlgorithm Astar_HG0 = new AStarAlgorithm(data_HG0);
		solAstar_HG0 = Astar_HG0.doRun();
		BellmanFordAlgorithm BF_HG0 = new BellmanFordAlgorithm(data_HG0);
		solBF_HG0 = BF_HG0.doRun();
		
		// 1 - Cars and length
		ShortestPathData data_HG1 = new ShortestPathData(graphHauteGaronne, origins[1], destinations[1], Filters.get(1));
		DijkstraAlgorithm dijkstra_HG1 = new DijkstraAlgorithm(data_HG1) ; 
		solDijkstra_HG1 = dijkstra_HG1.doRun() ;
		AStarAlgorithm Astar_HG1 = new AStarAlgorithm(data_HG1);
		solAstar_HG1 = Astar_HG1.doRun();
		BellmanFordAlgorithm BF_HG1 = new BellmanFordAlgorithm(data_HG1);
		solBF_HG1 = BF_HG1.doRun();
		
		// 2 - All roads allowed and time
		ShortestPathData data_HG2 = new ShortestPathData(graphHauteGaronne, origins[2], destinations[2], Filters.get(2));
		DijkstraAlgorithm dijkstra_HG2 = new DijkstraAlgorithm(data_HG2) ; 
		solDijkstra_HG2 = dijkstra_HG2.doRun() ;
		AStarAlgorithm Astar_HG2 = new AStarAlgorithm(data_HG2);
		solAstar_HG2 = Astar_HG2.doRun();
		BellmanFordAlgorithm BF_HG2 = new BellmanFordAlgorithm(data_HG2);
		solBF_HG2 = BF_HG2.doRun();
		
		// 3 - Cars and time
		ShortestPathData data_HG3 = new ShortestPathData(graphHauteGaronne, origins[3], destinations[3], Filters.get(3));
		DijkstraAlgorithm dijkstra_HG3 = new DijkstraAlgorithm(data_HG3) ; 
		solDijkstra_HG3 = dijkstra_HG3.doRun() ;
		AStarAlgorithm Astar_HG3 = new AStarAlgorithm(data_HG3);
		solAstar_HG3 = Astar_HG3.doRun();
		BellmanFordAlgorithm BF_HG3 = new BellmanFordAlgorithm(data_HG3);
		solBF_HG3 = BF_HG3.doRun();
		
		// 4- Non-private roads for pedestrian and bicycle and time
		ShortestPathData data_HG4 = new ShortestPathData(graphHauteGaronne, origins[4], destinations[4], Filters.get(4));
		DijkstraAlgorithm dijkstra_HG4 = new DijkstraAlgorithm(data_HG4) ; 
		solDijkstra_HG4 = dijkstra_HG4.doRun() ;
		AStarAlgorithm Astar_HG4 = new AStarAlgorithm(data_HG4);
		solAstar_HG4 = Astar_HG4.doRun();
		BellmanFordAlgorithm BF_HG4 = new BellmanFordAlgorithm(data_HG4);
		solBF_HG4 = BF_HG4.doRun();
		
		// 5- Origin = Destination, All roads allowed and length
		ShortestPathData data_HG5 = new ShortestPathData(graphHauteGaronne, origins[5], origins[5], Filters.get(0));
		DijkstraAlgorithm dijkstra_HG5 = new DijkstraAlgorithm(data_HG5) ; 
		solDijkstra_HG5 = dijkstra_HG5.doRun() ;
		AStarAlgorithm Astar_HG5 = new AStarAlgorithm(data_HG5);
		solAstar_HG5 = Astar_HG5.doRun();
		BellmanFordAlgorithm BF_HG5 = new BellmanFordAlgorithm(data_HG5);
		solBF_HG5 = BF_HG5.doRun();
		
		// 6- Non existing Path
		ShortestPathData data_HG6 = new ShortestPathData(graphHauteGaronne, graphHauteGaronne.get(116900), graphHauteGaronne.get(71307), Filters.get(0));
		DijkstraAlgorithm dijkstra_HG6 = new DijkstraAlgorithm(data_HG6) ; 
		solDijkstra_HG6 = dijkstra_HG6.doRun() ;
		AStarAlgorithm Astar_HG6 = new AStarAlgorithm(data_HG6);
		solAstar_HG6 = Astar_HG6.doRun();
		BellmanFordAlgorithm BF_HG6 = new BellmanFordAlgorithm(data_HG6);
		solBF_HG6 = BF_HG6.doRun(); 
	}
	
	@Test
    public void testIsValid() {
		if(solDijkstra_Carre0.isFeasible()) {
			assertTrue(solDijkstra_Carre0.getPath().isValid());
		}
		if(solDijkstra_HG0.isFeasible()) {
			assertTrue(solDijkstra_HG0.getPath().isValid());
		}
		if(solDijkstra_HG1.isFeasible()) {
			assertTrue(solDijkstra_HG1.getPath().isValid());
		}
		if(solDijkstra_HG2.isFeasible()) {
			assertTrue(solDijkstra_HG2.getPath().isValid());
		}
		if(solDijkstra_HG3.isFeasible()) {
			assertTrue(solDijkstra_HG3.getPath().isValid());
		}
		if(solDijkstra_HG4.isFeasible()) {
			assertTrue(solDijkstra_HG4.getPath().isValid());
		}
		if(solDijkstra_HG5.isFeasible()) {
			assertTrue(solDijkstra_HG5.getPath().isValid());
		}
		
		
		if(solAstar_Carre0.isFeasible()) {
			assertTrue(solDijkstra_Carre0.getPath().isValid());
		}
		if(solAstar_HG0.isFeasible()) {
			assertTrue(solDijkstra_HG0.getPath().isValid());
		}
		if(solAstar_HG1.isFeasible()) {
			assertTrue(solDijkstra_HG1.getPath().isValid());
		}
		if(solAstar_HG2.isFeasible()) {
			assertTrue(solDijkstra_HG2.getPath().isValid());
		}
		if(solAstar_HG3.isFeasible()) {
			assertTrue(solDijkstra_HG3.getPath().isValid());
		}
		if(solAstar_HG4.isFeasible()) {
			assertTrue(solDijkstra_HG4.getPath().isValid());
		}
		if(solAstar_HG5.isFeasible()) {
			assertTrue(solDijkstra_HG5.getPath().isValid());
		} 
	}
	
	// Check the solution is not empty when origin=destination
	@Test
	public void testIsEmpty() {
		assertFalse(solDijkstra_HG5.getPath().isEmpty());
		assertFalse(solAstar_HG5.getPath().isEmpty());
	}
	
	// Check that the solution is infeasible when there is no solution
	@Test
	public void testInfeasible() {
		assertFalse(solDijkstra_HG6.isFeasible());
		assertFalse(solAstar_HG6.isFeasible());
	}
	
	// Compares the results of Dijkstra, Astar and BellmanFord
	@Test
    public void testAlgosComparison() {
		if(solDijkstra_Carre0.isFeasible()) {
			assertEquals(solDijkstra_Carre0.getPath().getLength(), solAstar_Carre0.getPath().getLength(), 0.1);
		}
		if(solBF_HG0.isFeasible()) {
			assertEquals(solDijkstra_HG0.getPath().getLength(), solBF_HG0.getPath().getLength(), 0.1);
			assertEquals(solAstar_HG0.getPath().getLength(), solBF_HG0.getPath().getLength(), 0.1);
		}
		if(solBF_HG1.isFeasible()) {
			assertEquals(solDijkstra_HG1.getPath().getLength(), solBF_HG1.getPath().getLength(), 0.1);
			assertEquals(solAstar_HG1.getPath().getLength(), solBF_HG1.getPath().getLength(), 0.1);
		}
		if(solBF_HG2.isFeasible()) {
			assertEquals(solDijkstra_HG2.getPath().getLength(), solBF_HG2.getPath().getLength(), 0.1);
			assertEquals(solAstar_HG2.getPath().getLength(), solBF_HG2.getPath().getLength(), 0.1);
		}
		if(solBF_HG3.isFeasible()) {
			assertEquals(solDijkstra_HG3.getPath().getLength(), solBF_HG3.getPath().getLength(), 0.1);
			assertEquals(solAstar_HG3.getPath().getLength(), solBF_HG3.getPath().getLength(), 0.1);
		}
		if(solBF_HG4.isFeasible()) {
			assertEquals(solDijkstra_HG4.getPath().getLength(), solBF_HG4.getPath().getLength(), 0.1);
			assertEquals(solAstar_HG4.getPath().getLength(), solBF_HG4.getPath().getLength(), 0.1);
		}
	}
}
