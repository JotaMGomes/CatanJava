package com.jlmg.Entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.jlmg.Boundary.IMouseHandler;
import com.jlmg.Boundary.PlayerGUI;
import com.jlmg.Controller.GameCT;
import com.jlmg.util.CellType;

import javafx.scene.paint.Color;

public class Player extends PlayerGUI implements IMouseHandler{
	
	private Color vColor;
	private int numPoints, maxRow, maxArm;
	private ArrayList<DevCard> lstDevCard = new ArrayList<DevCard>();
	private HashMap<CellType, Integer> hmResCard = new HashMap<CellType, Integer>();

	
	
	//public c wPlayer;
	
	public Player(Color vColor, Integer index) {
		super(index, vColor);
		this.vColor = vColor;
		this.numPoints = 0;
		this.maxRow = 0;
		this.maxArm = 0;
		hmResCard.put(CellType.BRICK, 0);
		hmResCard.put(CellType.WOOD, 0);
		hmResCard.put(CellType.WHEAT, 0);
		hmResCard.put(CellType.SHEEP, 0);
		hmResCard.put(CellType.ORE, 0);
	}

	/**
	 * get player color
	 * @return player color
	 */
	public Color getvColor() {
		return vColor;
	}

	/**
	 * set player color
	 * @param vColor
	 */
	public void setvColor(Color vColor) {
		this.vColor = vColor;
	}

	/**
	 * get player points
	 * @return player points
	 */
	public int getNumPoints() {
		return numPoints;
	}

	/**
	 * add points to player
	 * @param numPoints
	 */
	public void addPoints(int numPoints) {
		this.numPoints = this.numPoints + numPoints;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxArm() {
		return maxArm;
	}

	public void setMaxArm(int maxArm) {
		this.maxArm = maxArm;
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
		GameCT.startTurn();
	}
	
	/**
	 * update player resources
	 */
	public void updatePlayerResources() {
		updateResources(hmResCard);
	}
	
}
