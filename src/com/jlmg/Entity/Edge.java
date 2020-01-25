package com.jlmg.Entity;

import java.util.ArrayList;

import com.jlmg.Boundary.IMouseHandler;
import com.jlmg.Controller.GameCT;
import com.jlmg.util.GameState;

/**
 * Defines a land edge
 * @author Jose Luiz Gomes
 *
 */
public class Edge extends Spot implements IMouseHandler {
	
	// array with cells to which the edge belongs
	ArrayList<Integer> lstCell = new ArrayList<Integer>();
	// array with vertices to which the edge belongs
	ArrayList<Integer> lstVertex = new ArrayList<Integer>();

	/**
	 * Initializes a new edge
	 * @param index
	 */
	public Edge(int index) {
		setIndex(index);
		
		// add a handler to mouse click event
		addMouseClickEvent();
	}
	
	/**
	 * Getters and Setters
	 */
	
	public void addCell(Integer cellNum) {
		lstCell.add(cellNum);
	}
	
	public void addVertex(Integer vertexNum) {
		lstVertex.add(vertexNum);
	}
	
	public boolean hasVertex(Integer vertexNum) {
		return lstVertex.contains(vertexNum);
	}
	
	public Integer getVertex(Integer index) {
		return lstVertex.get(index);
	}
	
	public ArrayList<Integer> getVertexLst() {
		return lstVertex;
	}
     
	/**
	 * Handler for mouse click event
	 */
 	@Override
 	public void handleSpotOnClick() {
        System.out.println("Edge " + getIndex()); 
        
        /* if the game state is START0 or START1 (setting first villages and roads)
		 * and the spot is clear, then create a road
		 */
        if (GameCT.gameStep == GameState.START0 || GameCT.gameStep == GameState.START1) {
        	// set player number to this spot
			setPlayerNum(GameCT.currPlayer);
			// set the player color
	        vCircle.setFill(GameCT.arPlayer[GameCT.currPlayer].getvColor());
	        // disable the spot
	        vCircle.setDisable(true);
	        //Hide all free Edges
	        GameCT.hideAllFreeEdges();
        }
 	}

}
