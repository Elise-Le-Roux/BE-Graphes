package org.insa.graphs.algorithm;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
	private int current_vertex;
	private boolean mark;
	private double cost;
	private Arc father;
	
	public Label(int current_vertex) {
		this.current_vertex = current_vertex;
		this.mark = false;
		this.cost = Double.POSITIVE_INFINITY;
		this.father = null;
	}
	
	public Label(int current_vertex, boolean mark, double cost, Arc father) {
		this.current_vertex = current_vertex;
		this.mark = mark;
		this.cost = cost;
		this.father = father;
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
	
	public int getCurrentVertex() {
		return this.current_vertex;
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
        return Double.compare(getCost(), other.getCost());
    }
}
