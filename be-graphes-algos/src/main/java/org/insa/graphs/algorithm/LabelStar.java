package org.insa.graphs.algorithm;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{
	
	private double heuristic;
	
	// Length mode
	public LabelStar(Node current_vertex, Node destination) {
		super(current_vertex);
		this.heuristic = current_vertex.getPoint().distanceTo(destination.getPoint());
	}
	
	// Time mode
	public LabelStar(Node current_vertex, Node destination, double speed) {
		super(current_vertex);
		this.heuristic = current_vertex.getPoint().distanceTo(destination.getPoint()) / speed;
	}
	
	public double getHeuristic() {
		return this.heuristic;
	}
	
	@Override
	public double getTotalCost() {
		return this.getCost() + this.heuristic;
	}
}
