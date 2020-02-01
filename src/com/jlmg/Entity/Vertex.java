package com.jlmg.Entity;

import java.util.ArrayList;

import com.jlmg.Boundary.IMouseHandler;
import com.jlmg.Controller.GameCT;
import com.jlmg.util.GameState;

/**
 * Defines a land vertex
 * @author Jose Luiz Gomes
 *
 */
public class Vertex extends Spot implements IMouseHandler {

	private int level;      // village or city
	// array with cells to which the vertex belongs
	private ArrayList<Integer> lstCell = new ArrayList<Integer>();
	// array with edges to which the vertex belongs
	private ArrayList<Integer> lstEdge = new ArrayList<Integer>();
	
	/**
	 * Initializes a new vertex
	 * @param index: The vertex index
	 */
	public Vertex(int index) {
		this.level = 0;
		setIndex(index);
		
		// add a handler to mouse click event
		addMouseClickEvent();
	}

	/**
	 * Getters and Setters
	 */
	
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
	
	/**
	 * Handler for mouse click event
	 */
	@Override
	public void handleSpotOnClick() {
		System.out.println("Vertex " + getIndex());
		
		/* if the game state is START0 or START1 (setting first villages and roads)
		 * and the spot is clear, then create a village
		 */
		if ((GameCT.gameStep == GameState.START0 || GameCT.gameStep == GameState.START1) && level == 0) {
			// set player number to this spot
			setPlayerNum(GameCT.currPlayer);
			// set the player color
			vCircle.setFill(GameCT.arPlayer[GameCT.currPlayer].getvColor());
			// put a village
			level++;
			// display edges from this vertex
			GameCT.showEdgesFromVertex(getIndex(), true);
			
			// deal cards to player
			if (GameCT.gameStep == GameState.START1)
				GameCT.dealCardsStage1(lstCell);
		}
	}
}
