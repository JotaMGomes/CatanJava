package com.jlmg.Entity;

import com.jlmg.Boundary.SpotGUI;

/**
 * Defines a spot where can be put a village, road or the thief
 * @author Jose Luiz Gomes
 *
 */
public class Spot extends SpotGUI{
	
	private int playerNum;  // the player number
	private Integer index;  // index of the vertex
	
	/**
	 * Initializes a new spot
	 */
	public Spot() {
		playerNum = -1;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
