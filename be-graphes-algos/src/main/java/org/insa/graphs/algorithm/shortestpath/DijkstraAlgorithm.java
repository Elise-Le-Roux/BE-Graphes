package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.Label;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

import java.util.List;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        
        // Retrieve the graph.
        Graph graph = data.getGraph();
        
        final int nbNodes = graph.size();
        //List<Node> nodes = graph.getNodes();
        
        // Initialize array of distances.
        double[] distances = new double[nbNodes];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[data.getOrigin().getId()] = 0;
        
        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];
        
        // Binary Heap
        BinaryHeap<Label> Heap = new BinaryHeap<Label>();
        
        // Initialization
        Label[] labels = new Label[nbNodes];
        for(int i = 0; i < nbNodes; i++) {
        	labels[i] = new Label(i);
        }
        labels[data.getOrigin().getId()].setCost(0);
        Heap.insert(labels[data.getOrigin().getId()]);
        
        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        int vertices_marked = 0; // number of vertices marked
        Label min;
        int index_new;
        int index_old;
        
        // Algorithm
        boolean found = false;
        while(vertices_marked < nbNodes && !Heap.isEmpty() ) { //&& !found
        	found = true;
        	min = Heap.deleteMin();
        	min.setMark(true);
        	vertices_marked++;
        	index_new = min.getCurrentVertex();
        	for(Arc arc : graph.getNodes().get(index_new).getSuccessors()) {
        		
        		// Small test to check allowed roads...
                if (!data.isAllowed(arc)) {
                    continue;
                }
        		
        		index_old = arc.getDestination().getId();
        		
        		// Retrieve weight of the arc.
                double w = data.getCost(arc);
                double oldDistance = distances[index_old];
                double newDistance = distances[index_new] + w;
                
                if (Double.isInfinite(oldDistance) && Double.isFinite(newDistance)) {
                    notifyNodeReached(arc.getDestination());
                }
        		
        		if(!labels[index_old].getMark()) {
        			if(newDistance < oldDistance) {
        				found = false;
        				distances[index_old] = distances[index_new] + w;
        				try { 
        					Heap.remove(labels[index_old]);
        				}
        				catch (ElementNotFoundException e) {
        					
        				}
        				finally {
        					labels[index_old].setCost(distances[index_old]);
        					labels[index_old].setFather(arc);
        					Heap.insert(labels[index_old]);
        					predecessorArcs[index_old] = arc;
        				}
        			}
        		}
        	}
        }
        
        // Destination has no predecessor, the solution is infeasible...
        if (predecessorArcs[data.getDestination().getId()] == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = predecessorArcs[data.getDestination().getId()];
            while (arc != null) {
                arcs.add(arc);
                arc = predecessorArcs[arc.getOrigin().getId()];
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }
        
        return solution;
    }

}
