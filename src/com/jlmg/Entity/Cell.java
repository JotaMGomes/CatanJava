package com.jlmg.Entity;

import com.jlmg.util.CellType;

/**
 * Defines a land on the board game
 * @author Jose Luiz Gomes
 *
 */
public class Cell extends Spot {
	
	private CellType vCT;     // The land type
	private int[] arVertex;   // Array with land vertices
	private int[] arEdge;     // Array with land edges
	private boolean isThief;
	
	/**
	 * Initializes a new land 
	 * @param n: The land id
	 * @param ct: The land type
	 */
	public Cell(Integer n, CellType ct) {
		setIndex(n);
		this.vCT = ct;
		this.isThief = false;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public CellType getCellType() {
		return this.vCT;
	}
	
	public String getStrNumber() {
		return getIndex().toString();
	}
	
	public void setArVertex(int[] arrayV) {
		this.arVertex = arrayV;
	}
	
	public int[] getArVertex() {
		return this.arVertex;
	}
	
	public int getVertex(int index) {
		return this.arVertex[index];
	}
	
	public void setArEdge(int[] arrayV) {
		this.arEdge = arrayV;
	}
	
	public int[] getArEdge() {
		return this.arEdge;
	}
	
	public int getEdge(int index) {
		return this.arEdge[index];
	}	
	
	public boolean isThief() {
		return isThief;
	}

	public void setThief(boolean isThief) {
		this.isThief = isThief;
	}
	
}
