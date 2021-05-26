package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.Label;
import org.insa.graphs.algorithm.LabelStar;
import org.insa.graphs.model.Graph;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    protected Label[] initLabels(int nbNodes, Graph graph, ShortestPathData data) {
    	LabelStar[] labels = new LabelStar[nbNodes];
    	if(data.getMode() == Mode.LENGTH) {
    		for(int i = 0; i < nbNodes; i++) {
            	labels[i] = new LabelStar(graph.get(i), data.getDestination());
            }
    	}
    	else {
    		for(int i = 0; i < nbNodes; i++) {
            	labels[i] = new LabelStar(graph.get(i), data.getDestination(), data.getGraph().getGraphInformation().getMaximumSpeed()/3.6);
            }
    	}
        return labels;
    }
}
