package com.jlmg.util;

import javafx.scene.paint.Color;

/**
 * Cell type enum
 * @author Jose Luiz Gomes
 * 
 * Color and index
 *
 */
public enum CellType {
	
	// colors
	BRICK(Color.FIREBRICK, 0), 
	WOOD(Color.DARKOLIVEGREEN, 1), 
	WHEAT(Color.YELLOW, 2), 
	SHEEP(Color.GREENYELLOW, 3), 
	ORE(Color.DIMGRAY, 4), 
	DESERT(Color.SANDYBROWN, 5);
	
	private final Color vColor; // color
	private Integer index;      // index
	
	/**
	 * Initializes new object
	 * @param vColor : color
	 * @param index  : index
	 */
	private CellType(final Color vColor, Integer index) {
		this.vColor = vColor;
		this.index = index;
	}
	
	/**
	 * Getters
	 */
	
	public Color getColor() {
		return this.vColor;
	}
	
	public Integer getIndex() {
		return this.index;
	}
}
