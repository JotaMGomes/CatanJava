package com.jlmg.Entity;

import java.util.ArrayList;

import com.jlmg.Boundary.IMouseHandler;
import com.jlmg.Controller.GameCT;
import com.jlmg.util.GameState;

public class Edge extends Spot implements IMouseHandler {
	
	public Edge(int index) {
		this.index = index;
		addMouseClickEvent();
	}
	
	private Integer index;
	ArrayList<Integer> lstCell = new ArrayList<Integer>();
	ArrayList<Integer> lstVertex = new ArrayList<Integer>();
	
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
     
 	@Override
 	public void handleSpotOnClick() {
        System.out.println("Edge " + index); 
        if (GameCT.gameStep == GameState.START0 || GameCT.gameStep == GameState.START1) {
	           setPlayerNum(GameCT.currPlayer);
	           vCircle.setFill(GameCT.arPlayer[GameCT.currPlayer].getvColor());
	           vCircle.setDisable(true);
	           
	           //Hide all free Edges
	           GameCT.hideAllFreeEdges();
        }
 	}

}
