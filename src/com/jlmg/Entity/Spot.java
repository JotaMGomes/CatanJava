package com.jlmg.Entity;

import com.jlmg.Boundary.SpotGUI;

public class Spot extends SpotGUI{
	
	private int playerNum;
	
	public Spot() {
		playerNum = -1;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

}
