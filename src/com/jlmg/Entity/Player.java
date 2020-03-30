package com.jlmg.Entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.jlmg.Boundary.IPlayerHandler;
import com.jlmg.Boundary.PlayerGUI;
import com.jlmg.Controller.GameCT;
import com.jlmg.util.CellType;

import javafx.scene.paint.Color;

/**
 * Defines a player
 * @author Jose Luiz Gomes
 *
 */
public class Player extends PlayerGUI implements IPlayerHandler{
	
	private Color vColor;  // player color
	/* number of points, 
	 * max number of consecutive roads, 
	 * maximum number of played army cards
	 */
	private int numPoints, maxRoad, maxArmy, totalCardsToDiscard; 
	// array of development cards
	private ArrayList<DevCard> lstDevCard = new ArrayList<DevCard>();
	// hash map of resources cards
	private HashMap<CellType, Integer> hmResCard = new HashMap<CellType, Integer>();
	// hash map of trade cards
	private HashMap<CellType, Integer> hmTradeCard = new HashMap<CellType, Integer>();

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
		
		hmTradeCard.put(CellType.BRICK, 0);
		hmTradeCard.put(CellType.WOOD, 0);
		hmTradeCard.put(CellType.WHEAT, 0);
		hmTradeCard.put(CellType.SHEEP, 0);
		hmTradeCard.put(CellType.ORE, 0);
		
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
	
	public int getTotalCardsToDiscard() {
		return totalCardsToDiscard;
	}

	public void setTotalCardsToDiscard(int totalCardsToDiscard) {
		this.totalCardsToDiscard = totalCardsToDiscard;
	}

	/**
	 * Total resource cards
	 * @return: current number of resource cards
	 */
	public int totalCards() {
		
		// define local counter
		int vTotalCards=0;
		
		// loop through cards types
		for(CellType card : hmResCard.keySet()) {
			
			// add total cards of that type
			vTotalCards += hmResCard.get(card);
		}
		
		// return local counter
		return vTotalCards;
	}
	
	/**
	 * btn Roll dices handler
	 */
	@Override
	public void btnDiceOnClick() {
		// start a new player turn
		GameCT.diceAction();
	}
	
	/**
	 * btn Done handler
	 */
	@Override
	public void btnDoneOnClick() {
		// finish the player turn
		GameCT.finishTurn();
	}
	
	/**
	 * btn New Development Card
	 */
	@Override
	public void btnNewDevOnClick() {
		
		// get a new development card
		DevCard myDevCard = GameCT.getNewDevCard();
		if (myDevCard != null) {
			lstDevCard.add(myDevCard);
			
			// remove 1 WHEAT + 1 SHEEP + 1 ORE
			hmResCard.put(CellType.WHEAT, hmResCard.get(CellType.WHEAT) - 1);
			hmResCard.put(CellType.SHEEP, hmResCard.get(CellType.SHEEP) - 1);
			hmResCard.put(CellType.ORE, hmResCard.get(CellType.ORE) - 1);
			
			// update resources GUI
			updateResources(hmResCard);
			
		}
		
		// Update development GUI
		updateDevBtnGUI();
	}
	
	/**
	 * btn Trade Card
	 */
	@Override
	public void btnTradeOnClick(CellType card, int btnValue) {
		
		if (getTotalTradeValues() >= totalCardsToDiscard) {
			return;
		}
		
		System.out.println(card + " " + btnValue);
		
		hmTradeCard.put(card, hmTradeCard.get(card)+btnValue);
		
		if (hmTradeCard.get(card) < 0) {
			hmTradeCard.put(card, 0);
			return;
		} else if (hmTradeCard.get(card) > hmResCard.get(card)){
			hmTradeCard.put(card,hmResCard.get(card));
			return;
		}
		
		updateTradeCardValues(hmTradeCard);
	}
	
	/** 
	 * update player GUI, development buttons
	 */
	public void updateDevBtnGUI() {
		
		// disable all development card buttons
		disableDevBtns();
		
		// enable card buttons
		Integer knight=0, victory=0, readBuild=0, monopoly=0, yearsPlenty=0;
		
		for (DevCard dcard : lstDevCard) {
			switch (dcard.getType()) {
			case KNIGHT:
				knight++;
				updateBtnDevCard(dcard.getType(), knight);
				break;
			case VICTORY:
				victory++;
				updateBtnDevCard(dcard.getType(), victory);
				break;
			case ROAD_BUILD:
				readBuild++;
				updateBtnDevCard(dcard.getType(), readBuild);
				break;
			case MONOPOLY:
				monopoly++;
				updateBtnDevCard(dcard.getType(), monopoly);;
				break;
			case YEARS_PLENTY:
				yearsPlenty++;
				updateBtnDevCard(dcard.getType(), yearsPlenty);
				break;
			}
		}
		
	}
	
	/**
	 * update player resources
	 */
	public void updatePlayerResources() {
		// update the player resources display
		updateResources(hmResCard);
	}
	
	/**
	 * enable trade button if player has at least one resource card
	 */
	public void verifyTradeBtn() {
		disableBtnTrade(hmResCard.get(CellType.BRICK) +
			    hmResCard.get(CellType.WOOD) +
			    hmResCard.get(CellType.WHEAT) +
			    hmResCard.get(CellType.SHEEP) +
			    hmResCard.get(CellType.ORE) == 0);
	}
	
	/**
	 * enable/disable new objects buttons
	 */
	public void verifyNewBtns() {
		/*
		 * enable road if the player has:
		 * 1 BRICK + 1 WOOD
		 */
		disableBtnRoad(!(hmResCard.get(CellType.BRICK) > 0 && hmResCard.get(CellType.WOOD) > 0));
		
		/*
		 * enable village if the player has:
		 * 1 BRICK + 1 WOOD + 1 WHEAT + 1 SHEEP
		 */
		disableBtnVillage(!(hmResCard.get(CellType.BRICK) > 0 && hmResCard.get(CellType.WOOD) > 0
				&& hmResCard.get(CellType.WHEAT) > 0 && hmResCard.get(CellType.SHEEP) > 0));
		
		/*
		 * enable city if the player has:
		 * 2 WHEAT + 3 ORE
		 */
		disableBtnCity(!(hmResCard.get(CellType.WHEAT) > 1 && hmResCard.get(CellType.ORE) > 2));
		
		/* 
		 * enable development if the player has:
		 * 1 WHEAT + 1 SHEEP + 1 ORE
		 */
		disableBtnNewDevCard(!(hmResCard.get(CellType.WHEAT) > 0 && hmResCard.get(CellType.SHEEP) > 0
				&& hmResCard.get(CellType.ORE) > 0));
	}
	
	/**
	 * clear all values from hmTradeCard
	 */
	public void clearTradeValues() {
		
		// clear all values from hmTradeCard
		for(CellType card : hmTradeCard.keySet()) {
			hmTradeCard.put(card, 0);
		}
		
		updateTradeCardValues(hmTradeCard);
	}
	
	private int getTotalTradeValues() {
		
		int i = 0;
		for(int value : hmTradeCard.values()) {
			i += value;
		}
		
		return i;
	}
	
}
