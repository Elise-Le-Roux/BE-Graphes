package org.insa.graphs.algorithm;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
	private Node current_vertex;
	private boolean mark;
	private double cost;
	private Arc father;
	
	public Label(Node current_vertex) {
		this.current_vertex = current_vertex;
		this.mark = false;
		this.cost = Double.POSITIVE_INFINITY;
		this.father = null;
	}
	
	public Node getCurrentVertex() {
		return this.current_vertex;
	}
	
	public boolean getMark() {
		return this.mark;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public Arc getFather() {
		return this.father;
	}
	
	public double getTotalCost() {
		return this.cost;
	}
	
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setFather(Arc father) {
		this.father = father;
	}
	
    @Override
    public int compareTo(Label other) {
        return Double.compare(getTotalCost(), other.getTotalCost());
    }
}
