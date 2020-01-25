package com.jlmg.Entity;

import com.jlmg.util.DevCardType;

/**
 * Defines a development card
 * @author Jose Luiz Gomes
 *
 */
public class DevCard {

	private DevCardType type; // type of the dev card
	private boolean isUsed;   // flag to identify if the card was already used
	
	/**
	 * Initializes a new dev card
	 * @param type: type of the card
	 */
	public DevCard (DevCardType type) {
		this.type = type;
		// set card as not used
		this.isUsed = false;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public DevCardType getType() {
		return type;
	}
	
	public boolean isUsed() {
		return this.isUsed;
	}
	
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
}
