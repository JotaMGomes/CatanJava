package com.jlmg.Entity;

import java.util.ArrayList;

import com.jlmg.Boundary.IMouseHandler;
import com.jlmg.Controller.GameCT;
import com.jlmg.util.GameState;

public class Vertex extends Spot implements IMouseHandler {

	private int level;
	private Integer index;
	private ArrayList<Integer> lstCell = new ArrayList<Integer>();
	private ArrayList<Integer> lstEdge = new ArrayList<Integer>();
	
	public Vertex(int index) {
		this.level = 0;
		this.index = index;
		addMouseClickEvent();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void addCell(Integer cellNum) {
		lstCell.add(cellNum);
	}	

	public void addEdge(Integer edgeNum) {
		lstEdge.add(edgeNum);
	}
	
	public Integer getEdge(Integer index) {
		return lstEdge.get(index);
	}
	
	public boolean hasEdge(Integer edgeNum) {
		return lstEdge.contains(edgeNum);
	}
	
	public ArrayList<Integer> getEdgeLst() {
		return lstEdge;
	}
	
	@Override
	public void handleSpotOnClick() {
		
		System.out.println("Vertex " + index);
		
		if ((GameCT.gameStep == GameState.START0 || GameCT.gameStep == GameState.START1) && level == 0) {
			setPlayerNum(GameCT.currPlayer);
			vCircle.setFill(GameCT.arPlayer[GameCT.currPlayer].getvColor());
			level++;
        
			//show edges from vertex
			GameCT.showEdgesFromVertex(index, true);
		}
	}
}
