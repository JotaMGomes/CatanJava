package com.jlmg.Entity;

/**
 * Defines a dice
 * @author Jose Luiz Gomes
 *
 */
public class Dice {
	
	private Integer value;           // dice value
	private final int maxValue = 6;  // size of the dice
	
	/**
	 * Initializes a new dice
	 */
	public Dice() {
		this.value = 1;
	}

	/**
	 * Rolls the dice and set a value
	 */
	public void rollDice() {
		this.value = (int)(Math.random() * maxValue + 1);
	}

	/**
	 * Return dice value
	 * @return: dice value
	 */
	public Integer getValue() {
		return this.value;
	}
	
}