package com.jlmg.Entity;

import com.jlmg.util.CellType;

public class Cell extends Spot {
	
	private Integer number;
	private CellType vCT;
	private int[] arVertex;
	private int[] arEdge;
	
	public Cell(Integer n, CellType ct) {
		this.number = n;
		this.vCT = ct;
	}
	
	public CellType getCellType() {
		return this.vCT;
	}
	
	public String getStrNumber() {
		return this.number.toString();
	}
	
	public Integer getNumber() {
		return this.number;
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
}
