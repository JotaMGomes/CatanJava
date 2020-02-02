package com.jlmg.Entity;

import com.jlmg.util.DevCardType;

/**
 * Defines a development card
 * @author Jose Luiz Gomes
 *
 */
public class DevCard {

	private DevCardType type; // type of the dev card
	private boolean isAvailable;   // flag to identify if the card was already used
	
	/**
	 * Initializes a new development card
	 * @param type: type of the card
	 */
	public DevCard (DevCardType type) {
		this.type = type;
		// set card as available
		this.isAvailable = true;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public DevCardType getType() {
		return type;
	}
	
	public boolean isAvailable() {
		return this.isAvailable;
	}
	
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
}
