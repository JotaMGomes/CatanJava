package com.jlmg.Entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.jlmg.Boundary.IMouseHandler;
import com.jlmg.Boundary.PlayerGUI;
import com.jlmg.Controller.GameCT;
import com.jlmg.util.CellType;

import javafx.scene.paint.Color;

/**
 * Defines a player
 * @author Jose Luiz Gomes
 *
 */
public class Player extends PlayerGUI implements IMouseHandler{
	
	private Color vColor;  // player color
	/* number of points, 
	 * max number of consecutive roads, 
	 * maximum number of played army cards
	 */
	private int numPoints, maxRoad, maxArmy; 
	// array of development cards
	private ArrayList<DevCard> lstDevCard = new ArrayList<DevCard>();
	// hash map of resources cards
	private HashMap<CellType, Integer> hmResCard = new HashMap<CellType, Integer>();

	/**
	 * Initializes a new player
	 * @param vColor: player color
	 * @param index:  player index
	 */
	public Player(Color vColor, Integer index) {
		super(index, vColor);
		this.vColor = vColor;
		this.numPoints = 0;
		this.maxRoad = 0;
		this.maxArmy = 0;
		hmResCard.put(CellType.BRICK, 0);
		hmResCard.put(CellType.WOOD, 0);
		hmResCard.put(CellType.WHEAT, 0);
		hmResCard.put(CellType.SHEEP, 0);
		hmResCard.put(CellType.ORE, 0);
	}
	
	/**
	 * Getters and Setters
	 */

	public Color getvColor() {
		return vColor;
	}

	public void setvColor(Color vColor) {
		this.vColor = vColor;
	}

	public int getNumPoints() {
		return numPoints;
	}

	public void addPoints(int numPoints) {
		this.numPoints = this.numPoints + numPoints;
	}

	public int getMaxRow() {
		return maxRoad;
	}

	public void setMaxRow(int maxRow) {
		this.maxRoad = maxRow;
	}

	public int getMaxArm() {
		return maxArmy;
	}

	public void setMaxArm(int maxArm) {
		this.maxArmy = maxArm;
	}
	
	public void addDevCard(DevCard card) {
		lstDevCard.add(card);
	}
	
	public void addResCard(CellType card) {
		hmResCard.put(card, hmResCard.get(card)+1);
	}
	
	public void subResCard(CellType card) {
		hmResCard.put(card, hmResCard.get(card)-1);
	}
	
	/**
	 * mouse handler
	 */
	@Override
	public void handleSpotOnClick() {
		// start a new player turn
		GameCT.startTurn();
	}
	
	/**
	 * update player resources
	 */
	public void updatePlayerResources() {
		// update the player resources display
		updateResources(hmResCard);
	}
	
}
